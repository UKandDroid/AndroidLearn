package test.app

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.User
import test.app.domain.repo.LocalRepository
import test.app.ui.home.ChatUiState
import test.app.domain.util.ChatConvertor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import test.app.data.StringRes
import test.app.domain.model.MessageModel
import test.app.domain.model.ui.SectionItem
import test.app.domain.repo.DispatcherProvider


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ChatViewModelTest {

    private lateinit var testSubject: ChatViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val localRepository = mockk<LocalRepository>()
    private val chatConvertor = mockk<ChatConvertor>()
    private val stringRes = mockk<StringRes>()

    private val dispatcherProvider = object : DispatcherProvider {
        override val MAIN: CoroutineDispatcher get() = StandardTestDispatcher()
        override val IO: CoroutineDispatcher get() = StandardTestDispatcher()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { stringRes.LOADING } returns "Loading..."
        every { localRepository.getAllMessages() } returns  flowOf(listOf(MessageModel("Hi There", true, 0)))
        every { chatConvertor.convertChat(localRepository)} returns flowOf(listOf(messageItem))

        testSubject = ChatViewModel(localRepository, chatConvertor, dispatcherProvider, stringRes)
    }

    @After
    fun upSet() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test Send Message`() = runTest {
        // Given
        val text = "Hello"
        val user = User("main", true)
        testSubject.sendMessage(text)

        // When
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(exactly = 1){ localRepository.saveMessage(text, user.main) }
    }

    @Test
    fun `test Ui State shows Loading message while message load`() = runTest {

        // When
        val uiState = testSubject.uiState.first()

        // Then
        assertEquals(chatUiStateLoading, uiState)
    }

    @Test
    fun `test Ui State shows message when loaded`() = runTest {

        testDispatcher.scheduler.advanceUntilIdle()

        // When
        val uiState = testSubject.uiState.first()

        // Then
        assertEquals(chatUiStateMessage, uiState)
    }

    var messageItem = MessageItem("Hi There", false, false, false)
    var sectionItem = SectionItem("Loading...")
    var chatUiStateMessage = ChatUiState(0, listOf(messageItem))
    var chatUiStateLoading = ChatUiState(0, listOf(sectionItem))
}


