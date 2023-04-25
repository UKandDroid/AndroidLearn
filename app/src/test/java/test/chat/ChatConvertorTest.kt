package test.chat

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import test.chat.domain.model.MessageModel
import test.chat.domain.model.ui.MessageItem
import test.chat.domain.model.ui.SectionItem
import test.chat.domain.repo.LocalRepository
import test.chat.util.ChatConvertor
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

        val mockFlow = flowOf(emptyList<MessageModel>())
        whenever(repository.getAllMessages()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllMessages()
        assertEquals(0, resultFlow.first().size)

    }

    @Test
    fun `one Message Has Tail`() = runTest {

        val mockFlow = flowOf(SINGLE_MESSAGE)

        whenever(repository.getAllMessages()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllMessages()
        resultFlow.first().let {

            (it[1] as MessageItem).apply {
                assertEquals(true, hasTail)
            }
        }
    }

    @Test
    fun `consecutive message does not have tail`() = runTest {
        val mockFlow = flowOf(DOUBLE_MESSAGE)

        whenever(repository.getAllMessages()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllMessages()
        resultFlow.first().let {

            assertEquals(3, it.size)

            (it[0] as SectionItem).apply {
                assertTrue(title.isNotEmpty())
            }

            (it[1] as MessageItem).apply {
                assertEquals("Hi there!", text)
                assertEquals(true, isUser)
                assertEquals(false, hasTail)
            }

        }
    }


    @Test
    fun `last message has tail`() = runTest {
        val mockFlow = flowOf(DOUBLE_MESSAGE)

        whenever(repository.getAllMessages()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllMessages()
        resultFlow.first().let {

            assertEquals(3, it.size)

            (it[2] as MessageItem).apply {
                assertEquals("Did you miss me!", text)
                assertEquals(true, isUser)
                assertEquals(true, hasTail)
            }
        }
    }

    @Test
    fun testMultipleMessageDifferentUserAfter() = runTest {
        val mockFlow = flowOf(MULTIPLE_CHAT)

        whenever(repository.getAllMessages()).thenReturn(mockFlow)

        val resultFlow = testSubject.convertChat()

        verify(repository, times(1)).getAllMessages()
        resultFlow.first().let {

            assertEquals(4, it.size)

            (it[0] as SectionItem).run {
                assertTrue(title.isNotEmpty())
            }

            (it[1] as MessageItem).run {
                assertEquals("Hi there!", text)
                assertEquals(true, isUser)
                assertEquals(false, hasTail)
            }

            (it[2] as MessageItem).run {
                assertEquals("Did you miss me!", text)
                assertEquals(true, isUser)
                assertEquals(true, hasTail)
            }
        }
    }


    val MESSAGE_TIME1 = 1000L
    val MESSAGE_TIME2 = 2000L
    val MESSAGE_TIME3 = 3000L

    private val SINGLE_MESSAGE = listOf(
        MessageModel("Hi there!", true, MESSAGE_TIME1)
    )
    private val DOUBLE_MESSAGE = listOf(
        MessageModel("Hi there!", true, MESSAGE_TIME1),
        MessageModel("Did you miss me!", true, MESSAGE_TIME2)
    )
    private val MULTIPLE_CHAT = listOf(
        MessageModel("Hi there!", true, MESSAGE_TIME1),
        MessageModel("Did you miss me!", true, MESSAGE_TIME2),
        MessageModel("Not Really!", false, MESSAGE_TIME3)
    )

}