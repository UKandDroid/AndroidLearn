package test.app

import androidx.compose.runtime.State
import com.example.core.message.EventEntity
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
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import test.app.domain.model.ui.EventItem
import test.app.domain.repo.LocalRepository
import test.app.ui.home.EventViewModel
import test.app.ui.home.UiStates


@OptIn(ExperimentalCoroutinesApi::class)
class EventViewModelTest {

    @Mock
    private lateinit var mockLocalRepository: LocalRepository
    private lateinit var testSubject: EventViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        testSubject = EventViewModel(mockLocalRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshEvents should update uiState with list of events when successful`() = runTest {

        whenever(mockLocalRepository.refreshEvents()).thenReturn(true)
        whenever(mockLocalRepository.getAllEvents()).thenReturn(mockEventList)

        testSubject.refreshEvents()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListEvents(mockEventList.map { EventItem(it.name, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `refreshEvents should update uiState with error message when unsuccessful`() = runTest {

        whenever(mockLocalRepository.refreshEvents()).thenReturn(false)

        testSubject.refreshEvents()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.EventsUpdate("Failed to refresh, please try again"), testSubject.uiState.value)
    }

    @Test
    fun `eventSearch should update uiState with list of events when search result is not empty`() = runTest {

        whenever(mockLocalRepository.refreshEvents()).thenReturn(true)
        whenever(mockLocalRepository.getEvents("search")).thenReturn(mockEventList)
        whenever(mockLocalRepository.getAllEvents()).thenReturn(mockEventList)

        testSubject.eventSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListEvents(mockEventList.map { EventItem(it.name, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `eventSearch should update uiState with no matching items message when search result is empty`() = runTest {

        whenever(mockLocalRepository.refreshEvents()).thenReturn(true)
        whenever(mockLocalRepository.getAllEvents()).thenReturn(emptyList())
        whenever(mockLocalRepository.getEvents("search")).thenReturn(emptyList<EventEntity>())

        testSubject.eventSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState: State<UiStates> = testSubject.uiState
        assertEquals(UiStates.EventsUpdate("No matching items found"), uiState.value)
    }

    private val mockEventList = listOf(
        EventEntity(0,"Event 1", "Description 1", "URL 1"),
        EventEntity(1,"Event 2", "Description 2", "URL 2")
    )
}
