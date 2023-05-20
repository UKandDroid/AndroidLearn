package test.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import test.app.domain.model.ui.EventItem
import test.app.domain.model.ui.MessageItem
import test.app.ui.components.EventWindowComponent


class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun network_error_is_displayed() {
        val events = listOf(
            MessageItem("Failed to refresh, please try again"),
        )

        composeTestRule.setContent {
            EventWindowComponent(isLoading = false, events = events, {}, {} )
        }

        composeTestRule.onNodeWithText("Failed to refresh, please try again").assertIsDisplayed()
    }


    @Test
    fun no_search_result_is_displayed() {
        val events = listOf(
            MessageItem("No matching items found"),
        )

        composeTestRule.setContent {
            EventWindowComponent(isLoading = false, events = events, {}, {})
        }

        composeTestRule.onNodeWithText("No matching items found").assertIsDisplayed()
    }

    @Test
    fun list_of_events_is_displayed() {
        val events = listOf(
            EventItem(
                "Disco somewhere or anywhere",
                "Artist included",
                "https://somewhere.drawable.ic_launcher_foreground"
            ),
            EventItem(
                "Party at Moon",
                "Join us for a night of fun",
                "https://party.xyz/ic_launcher_foreground"
            )
        )

        composeTestRule.setContent {
            EventWindowComponent(isLoading = false, events = events, {}, { } )
        }

        composeTestRule.onNodeWithText("Disco somewhere or anywhere").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist included").assertIsDisplayed()

        composeTestRule.onNodeWithText("Party at Moon").assertIsDisplayed()
        composeTestRule.onNodeWithText("Join us for a night of fun").assertIsDisplayed()
    }


}