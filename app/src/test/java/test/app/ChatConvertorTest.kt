package test.app

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import test.app.domain.model.ui.EventItem
import test.app.domain.model.ui.MessageItem
import test.app.domain.repo.LocalRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ChatConvertorTest {

    @Mock
    private lateinit var repository: LocalRepository

    private lateinit var testSubject: ChatConvertor

    @Before
    fun setup() {
        testSubject = ChatConvertor(repository)
    }

    @Test
    fun `test empty chat`() = runTest {

        val mockFlow = flowOf(emptyList<EventModel>())
        whenever(repository.getAllEvents()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllEvents()
        assertEquals(0, resultFlow.first().size)

    }

    @Test
    fun `one Message Has Tail`() = runTest {

        val mockFlow = flowOf(SINGLE_MESSAGE)

        whenever(repository.getAllEvents()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllEvents()
        resultFlow.first().let {

            (it[1] as EventItem).apply {
                assertEquals(true, url)
            }
        }
    }

    @Test
    fun `consecutive message does not have tail`() = runTest {
        val mockFlow = flowOf(DOUBLE_MESSAGE)

        whenever(repository.getAllEvents()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllEvents()
        resultFlow.first().let {

            assertEquals(3, it.size)

            (it[0] as MessageItem).apply {
                assertTrue(message.isNotEmpty())
            }

            (it[1] as EventItem).apply {
                assertEquals("Hi there!", name)
                assertEquals(true, desc)
                assertEquals(false, url)
            }

        }
    }


    @Test
    fun `last message has tail`() = runTest {
        val mockFlow = flowOf(DOUBLE_MESSAGE)

        whenever(repository.getAllEvents()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllEvents()
        resultFlow.first().let {

            assertEquals(3, it.size)

            (it[2] as EventItem).apply {
                assertEquals("Did you miss me!", name)
                assertEquals(true, desc)
                assertEquals(true, url)
            }
        }
    }

    @Test
    fun `test Multiple Message Different User After`() = runTest {
        val mockFlow = flowOf(MULTIPLE_CHAT)

        whenever(repository.getAllEvents()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllEvents()
        resultFlow.first().let {

            assertEquals(4, it.size)

            (it[0] as MessageItem).run {
                assertTrue(message.isNotEmpty())
            }

            (it[1] as EventItem).run {
                assertEquals("Hi there!", name)
                assertEquals(true, desc)
                assertEquals(false, url)
            }

            (it[2] as EventItem).run {
                assertEquals("Did you miss me!", name)
                assertEquals(true, desc)
                assertEquals(true, url)
            }
        }
    }


    val MESSAGE_TIME1 = 1000L
    val MESSAGE_TIME2 = 2000L
    val MESSAGE_TIME3 = 3000L

    private val SINGLE_MESSAGE = listOf(
        EventModel("Hi there!", true, MESSAGE_TIME1)
    )
    private val DOUBLE_MESSAGE = listOf(
        EventModel("Hi there!", true, MESSAGE_TIME1),
        EventModel("Did you miss me!", true, MESSAGE_TIME2)
    )
    private val MULTIPLE_CHAT = listOf(
        EventModel("Hi there!", true, MESSAGE_TIME1),
        EventModel("Did you miss me!", true, MESSAGE_TIME2),
        EventModel("Not Really!", false, MESSAGE_TIME3)
    )

}