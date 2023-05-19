package test.app

import androidx.compose.runtime.State
import com.example.core.message.EventEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import test.app.domain.model.ui.EventItem
import test.app.domain.repo.LocalRepository
import test.app.ui.home.EventViewModel
import test.app.ui.home.UiStates


@OptIn(ExperimentalCoroutinesApi::class)
class EventViewModelTest {

    private val mockLocalRepository = mockk<LocalRepository>()
    private lateinit var testSubject: EventViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        testSubject = EventViewModel(mockLocalRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshEvents should update uiState with list of events when successful`() = runTest {

        coEvery { mockLocalRepository.refreshEvents() } returns  true
        coEvery { mockLocalRepository.getAllEvents() } returns  mockEventList

        testSubject.refreshEvents()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListEvents(mockEventList.map { EventItem(it.name, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `refreshEvents should update uiState with error message when unsuccessful`() = runTest {

        coEvery { mockLocalRepository.refreshEvents() } returns  false

        testSubject.refreshEvents()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.EventsUpdate("Failed to refresh, please try again"), testSubject.uiState.value)
    }

    @Test
    fun `eventSearch should update uiState with list of events when search result is not empty`() = runTest {

        coEvery { mockLocalRepository.refreshEvents() } returns  true
        coEvery { mockLocalRepository.getAllEvents() } returns  mockEventList
        coEvery { mockLocalRepository.getEvents("search") } returns  mockEventList

        testSubject.eventSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListEvents(mockEventList.map { EventItem(it.name, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `eventSearch should update uiState with no matching items message when search result is empty`() = runTest {

        coEvery { mockLocalRepository.refreshEvents() } returns  true
        coEvery { mockLocalRepository.getAllEvents() } returns  emptyList<EventEntity>()
        coEvery { mockLocalRepository.getEvents("search") } returns  emptyList<EventEntity>()

        testSubject.eventSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState: State<UiStates> = testSubject.uiState
        assertEquals(UiStates.EventsUpdate("No matching items found"), uiState.value)
    }

    private val mockEventList = listOf(
        EventEntity(0,"Queen of me 1", "en-us-1", "url_1"),
        EventEntity(1,"Queen of me 2", "en-us-2", "url_2")
    )
}
