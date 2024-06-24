package test.photos.repository

import com.mstech.testapp.photos.data.remote.PhotosApiService
import com.mstech.testapp.photos.domain.model.Photo
import com.mstech.testapp.photos.domain.repository.PhotosRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)

class PhotosRepositoryTest {

    @Mock
    private lateinit var mockApiService: PhotosApiService

    private lateinit var repository: PhotosRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = PhotosRepository(mockApiService)
    }

    @Test
    fun test_fetchPhotos_success() = runBlocking {
        val mockPhotos = listOf(Photo(1, 1, "Title", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"))
        val response = Response.success(mockPhotos)
        `when`(mockApiService.fetchPhotos()).thenReturn(response)

        val result = repository.fetchPhotos()

        assertEquals(response, result)
    }

    @Test
    fun test_fetchPhotos_error() = runBlocking {
        val errorMessage = "Error fetching photos"
        `when`(mockApiService.fetchPhotos()).thenThrow(RuntimeException(errorMessage))

        val result = repository.fetchPhotos()
        assertEquals(errorMessage, result.message())
    }
}