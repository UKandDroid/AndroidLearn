package test.app

import androidx.compose.runtime.State
import com.example.core.entity.PhotoEntity
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
import test.app.domain.model.InfoItem
import test.app.domain.model.PhotoItem
import test.app.domain.repo.LocalRepository
import test.app.domain.util.StringRes
import test.app.ui.home.MainViewModel
import test.app.ui.home.UiStates


@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val mockLocalRepository = mockk<LocalRepository>()
    private  val stringRes = mockk<StringRes>()
    private lateinit var testSubject: MainViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        every { stringRes.LOADING } returns "Loading..."
        every { stringRes.FAILED_TO_REFRESH } returns "Failed to refresh, please try again"
        every { stringRes.NO_MATCHING_ITEM } returns "No matching items found"

        Dispatchers.setMain(testDispatcher)
        testSubject = MainViewModel(mockLocalRepository, stringRes)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshPhotos should update uiState with list of photos when successful`() = runTest {

        coEvery { mockLocalRepository.refreshPhotos() } returns  true
        coEvery { mockLocalRepository.getAllPhotos() } returns  testPhotoList

        testSubject.refreshPhotos()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListPhotos(testPhotoList.map { PhotoItem(it.title, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `refreshPhotos should update uiState with error message when unsuccessful`() = runTest {

        coEvery { mockLocalRepository.refreshPhotos() } returns  false
        coEvery { mockLocalRepository.getAllPhotos() } returns  emptyList<PhotoEntity>()

        testSubject.refreshPhotos()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListPhotos(listOf(InfoItem("Failed to refresh, please try again"))), testSubject.uiState.value)
    }

    @Test
    fun `photoSearch should update uiState with list of photos when search result is not empty`() = runTest {

        coEvery { mockLocalRepository.refreshPhotos() } returns  true
        coEvery { mockLocalRepository.getAllPhotos() } returns  testPhotoList
        coEvery { mockLocalRepository.getPhotoByTitle("search") } returns  testPhotoList

        testSubject.photoSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(UiStates.ListPhotos(testPhotoList.map { PhotoItem(it.title, it.desc, it.url) }), testSubject.uiState.value)
    }

    @Test
    fun `photoSearch should update uiState with no matching items message when search result is empty`() = runTest {

        coEvery { mockLocalRepository.refreshPhotos() } returns  true
        coEvery { mockLocalRepository.getAllPhotos() } returns  emptyList<PhotoEntity>()
        coEvery { mockLocalRepository.getPhotoByTitle("search") } returns  emptyList<PhotoEntity>()

        testSubject.photoSearch("search")
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState: State<UiStates> = testSubject.uiState
        assertEquals(UiStates.PhotosUpdate("No matching items found"), uiState.value)
    }

    private val testPhotoList = listOf(
        PhotoEntity(0,"Mona Leasea", "bon-de-jour", "url_1"),
        PhotoEntity(1,"Mona Leasea", "en-bon-de-jour-2", "url_2")
    )
}
