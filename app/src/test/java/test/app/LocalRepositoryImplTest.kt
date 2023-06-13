package test.app

import com.example.network.model.ListPhotos
import com.example.core.entity.PhotoEntity
import com.example.core.entity.PhotoModel
import com.example.core.room.PhotoDatabase
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

    private val photosDb = mockk<PhotoDatabase>()
    private val mockNetworkRepository = mockk<NetworkRepository>()
    private lateinit var localRepository: LocalRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        localRepository = LocalRepositoryImpl(photosDb, mockNetworkRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPhotos should return a list of PhotoModel`() = runTest {
        val expectedPhotos = listOf(PhotoModel(0, "Some picture", "Some picture of Some picture", "picture_url"))
        every { photosDb.getPhotos() } returns expectedPhotos

        val result = localRepository.getAllPhotos()

        assertEquals(expectedPhotos, result)
    }

    @Test
    fun `getPhotos should return a list of PhotoEntity`() = runTest {
        val name = "Some picture"
        val expectedPhotos = listOf(PhotoEntity(0, "Some picture", "Some picture of Some picture", "picture_url"))
        every { photosDb.getPhotos(name) } returns expectedPhotos

        val result = localRepository.getPhotoByTitle(name)

        assertEquals(expectedPhotos, result)
    }

    @Test
    fun `refreshPhotos should return false when network call is unsuccessful`() = runTest {
        val mockResponse = ResponseWrapper.ApiError<ListPhotos>(-1, "Error")
        coEvery { mockNetworkRepository.getAllPhotos() } returns mockResponse

        val result = localRepository.refreshPhotos()

        assertEquals(false, result)
    }

}