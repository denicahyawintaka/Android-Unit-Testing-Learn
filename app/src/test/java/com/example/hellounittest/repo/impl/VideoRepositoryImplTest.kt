package com.example.hellounittest.repo.impl

import com.example.hellounittest.api.RetrofitService
import com.example.hellounittest.model.Video
import com.example.hellounittest.repo.VideoRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeoutException

class VideoRepositoryImplTest {

    //test object
    lateinit var videoRepository: VideoRepository

    //depend

    @Mock
    lateinit var mockedRetrofitService: RetrofitService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        videoRepository = VideoRepositoryImpl(mockedRetrofitService)
    }

    @Test
    fun `getAllVideo should return list of video when d`() {

        //expected result
        val video1 = Video(
            id = "123",
            name = "video 1",
            ratting = 4,
            url = "asdasd"
        )
        val expectedVideoList = listOf(video1)
        val video2 = video1.copy(
            name = "video 2"
        )
        val expectedVideoList2 = listOf(video2)

        //stubing
        whenever(mockedRetrofitService.getAllVideo())
            .thenReturn(Observable.just(expectedVideoList, expectedVideoList2))

        //execute test object
        val testObserver = videoRepository.getAllVideo().test()

        //assert
        testObserver.assertNoErrors()
        testObserver.assertValueAt(0) {videoList ->
            expectedVideoList.size == videoList.size &&
                    video1 == videoList.first()
        }
        testObserver.assertValueAt(1) {videoList ->
            expectedVideoList2.size == videoList.size &&
                    video2 == videoList.first()
        }
    }

    @Test
    fun `getAllVideo should return error when failed`() {
        val expectedError = TimeoutException()

        whenever(mockedRetrofitService.getAllVideo())
            .thenReturn(Observable.error(expectedError))

        //execute test object
        val testObserver = videoRepository.getAllVideo().test()

        testObserver.assertNotComplete()
        testObserver.assertError { error ->
            expectedError == error
        }

    }

    @Test
    fun `getAllVideo should return list of video then error when first emmit success nut the second was error`() {

        //expected result
        val video1 = Video(
            id = "123",
            name = "video 1",
            ratting = 4,
            url = "asdasd"
        )
        val expectedVideoList = listOf(video1)

        val expectedError = TimeoutException()

        //stubing
        whenever(mockedRetrofitService.getAllVideo())
            .thenReturn(
                Observable.create { emitter ->
                    emitter.onNext(expectedVideoList)
                    emitter.onError(expectedError)
                }
            )

        //execute test object
        val testObserver = videoRepository.getAllVideo().test()

        //assert
        testObserver.assertValueAt(0) {videoList ->
            expectedVideoList.size == videoList.size &&
                    video1 == videoList.first()
        }

       assert(testObserver.errorCount() == 1)
        testObserver.assertError { error ->
            expectedError == error
        }
    }

    @Test
    fun `getAllVideo error when first error  but the second success`() {

        //expected result
        val video1 = Video(
            id = "123",
            name = "video 1",
            ratting = 4,
            url = "asdasd"
        )
        val expectedVideoList = listOf(video1)

        val expectedError = TimeoutException()

        //stubing
        whenever(mockedRetrofitService.getAllVideo())
            .thenReturn(
                Observable.create { emitter ->
                    emitter.onError(expectedError)
                    emitter.onNext(expectedVideoList)
                }
            )

        //execute test object
        val testObserver = videoRepository.getAllVideo().test()

        //assert

        assert(testObserver.errorCount() == 1)
        testObserver.assertError { error ->
            expectedError == error
        }
    }

    @Test
    fun `putVideoRatting should complete and call retrofitService_putVideoRating` () {
        val id = "213"
        val ratting = 3

        //stub
        whenever(mockedRetrofitService.putVideoRating(any(), any())).thenReturn(Completable.complete())

        //execute
        val testObserver = videoRepository.putVideoRating(id, ratting).test()

        testObserver.assertComplete()
        verify(mockedRetrofitService).putVideoRating(
            com.nhaarman.mockitokotlin2.check { idList ->
                id in idList
            },
            eq(ratting + 1)
        )
    }
}
