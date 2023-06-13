package test.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.app.domain.model.PhotoItem
import test.app.domain.model.InfoItem
import test.app.domain.util.StringRes

import test.app.ui.compose.components.MainWindowComponent


class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private  val stringRes = mockk<StringRes>()

    @Before
    fun setup(){
        every { stringRes.LOADING } returns "Loading..."
        every { stringRes.FAILED_TO_REFRESH } returns "Failed to refresh, please try again"
        every { stringRes.NO_MATCHING_ITEM } returns "No matching items found"
    }

    @Test
    fun network_error_is_displayed() {
        val items = listOf(
            InfoItem(stringRes.FAILED_TO_REFRESH),
        )

        composeTestRule.setContent {
            MainWindowComponent(isLoading = false, listPhotos = items, {}, {})
        }

        composeTestRule.onNodeWithText(stringRes.FAILED_TO_REFRESH).assertIsDisplayed()
    }


    @Test
    fun no_search_result_is_displayed() {
        val items = listOf(
            InfoItem(stringRes.NO_MATCHING_ITEM),
        )

        composeTestRule.setContent {
            MainWindowComponent(isLoading = false, listPhotos = items, {}, {})
        }

        composeTestRule.onNodeWithText(stringRes.NO_MATCHING_ITEM).assertIsDisplayed()
    }

    @Test
    fun list_of_photos_is_displayed() {
        val items = listOf(
            PhotoItem(
                "Disco somewhere or anywhere",
                "Artist included",
                "https://somewhere.drawable.ic_launcher_foreground"
            ),
            PhotoItem(
                "Party at Moon",
                "Join us for a night of fun",
                "https://party.xyz/ic_launcher_foreground"
            )
        )

        composeTestRule.setContent {
            MainWindowComponent(isLoading = false, listPhotos = items, {}, {})
        }

        composeTestRule.onNodeWithText("Disco somewhere or anywhere").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist included").assertIsDisplayed()

        composeTestRule.onNodeWithText("Party at Moon").assertIsDisplayed()
        composeTestRule.onNodeWithText("Join us for a night of fun").assertIsDisplayed()
    }


}