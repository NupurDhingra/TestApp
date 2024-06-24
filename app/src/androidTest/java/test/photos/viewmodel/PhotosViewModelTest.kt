package test.photos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mstech.testapp.photos.domain.model.Photo
import com.mstech.testapp.photos.domain.repository.PhotosRepository
import com.mstech.testapp.photos.presentation.viewmodel.PhotosViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class PhotosViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: PhotosRepository

    private lateinit var viewModel: PhotosViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = PhotosViewModel(mockRepository)
    }

    @Test
    fun test_fetchPhotos_success() = runBlockingTest {
        val mockPhotos = listOf(Photo(1, 1, "Title", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"))
        val response = Response.success(mockPhotos)
        `when`(mockRepository.fetchPhotos()).thenReturn(response)

        viewModel.fetchPhotos()
        assertEquals(mockPhotos, viewModel.photos.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun test_fetchPhotos_error() = runBlockingTest {
        val errorMessage = "Error fetching photos"
        `when`(mockRepository.fetchPhotos()).thenThrow(RuntimeException(errorMessage))

        viewModel.fetchPhotos()

        assertEquals(errorMessage, viewModel.errorResponse.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}
