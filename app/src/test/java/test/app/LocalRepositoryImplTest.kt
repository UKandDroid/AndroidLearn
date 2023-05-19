package test.app

import com.example.core.Image
import com.example.core.ListEvents
import com.example.core.message.EventEntity
import com.example.core.message.EventModel
import com.example.core.room.EventDatabase
import com.example.network.NetworkRepository
import com.example.network.ResponseWrapper
import io.mockk.coEvery
import io.mockk.every
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
import test.app.domain.repo.LocalRepositoryImpl

@OptIn(ExperimentalCoroutinesApi::class)
class LocalRepositoryImplTest {


    private val mockEventsDb = mockk<EventDatabase>()
    private  val mockNetworkRepository = mockk< NetworkRepository>()

    private lateinit var localRepository: LocalRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        localRepository = LocalRepositoryImpl(mockEventsDb, mockNetworkRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllEvents should return a list of EventModel`() = runTest {
        val expectedEvents = listOf(EventModel(0,"Event 1", "Description 1", "URL 1"))
        every{mockEventsDb.getEvents()} returns expectedEvents

        val result = localRepository.getAllEvents()

        assertEquals(expectedEvents, result)
    }

    @Test
    fun `getEvents should return a list of EventEntity`() = runTest {
        val name = "example"
        val expectedEvents = listOf(EventEntity(0,"Event 1", "Description 1", "URL 1"))
       every{mockEventsDb.getEvents(name)} returns expectedEvents

        val result = localRepository.getEvents(name)

        assertEquals(expectedEvents, result)
    }

    @Test
    fun `refreshEvents should return false when network call is unsuccessful`() = runTest {
        val mockResponse = ResponseWrapper.ApiError<ListEvents>(-1, "Error")
        coEvery { mockNetworkRepository.getAllEvents()} returns mockResponse

        val result = localRepository.refreshEvents()

        assertEquals(false, result)
    }


    private fun  List<Image>.firstOrEmpty() = if (isNotEmpty()) first().url else ""

}