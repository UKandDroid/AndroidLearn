package test.app

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import test.app.domain.model.ui.MessageItem
import test.app.domain.model.ui.User
import test.app.domain.repo.LocalRepository
import test.app.ui.home.ChatUiState
import test.app.ui.home.ChatViewModel
import test.app.domain.util.ChatConvertor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
    class ChatViewModelTest {

        private val testDispatcher = StandardTestDispatcher()

        @Mock
        private lateinit var localRepository: LocalRepository
        @Mock
        private lateinit var chatConvertor: ChatConvertor

        // Create instance of ChatViewModel
        private lateinit var testSubject: ChatViewModel

        @Before
        fun setUp() {
            Dispatchers.setMain(testDispatcher)

            val chatItems = listOf(messageItem)
            whenever(chatConvertor.convertChat()).thenReturn(MutableStateFlow(chatItems))

            testSubject = ChatViewModel(localRepository, chatConvertor)
        }

        @After
        fun upSet(){
            Dispatchers.resetMain()
        }

        @Test
        fun `test Send Message`() = runTest{
            // Given
            val text = "Hello"
            val user = User("main", true)
            testSubject.sendMessage(text)

            // When
            delay(100)

            // Then
            verify(localRepository).saveMessage(text, user.main)
        }

        @Test
        fun `test Ui State has chat message item`() = runTest {

            delay(100)

            // When
            val uiState = testSubject.uiState.first()

            // Then
            assertEquals(chatUiState, uiState)
        }

    }


var messageItem = (MessageItem("hi there", false, false))
var chatUiState = ChatUiState(listOf( messageItem) )
