package com.example.hellounittest.repo.impl

import com.example.hellounittest.api.RetrofitService
import com.example.hellounittest.model.Video
import com.example.hellounittest.repo.VideoRepository
import com.example.hellounittest.util.VideoUtil
import io.reactivex.Completable
import io.reactivex.Single

class VideoRepositoryImpl(private val retrofitService: RetrofitService) : VideoRepository {

    override fun getAllVideo(): Single<List<Video>> {
        return retrofitService.getAllVideo()
    }

    override fun getVideoById(id: String): Single<List<Video>> {
       return retrofitService.getVideo(id)
    }

    override fun getAllVideoWithMinimumRatting(minRatting: Int): Single<List<Video>> {
        return retrofitService.getAllVideo(minRatting)
    }

    override fun putVideoRating(id: String, ratting: Int): Completable {
        val newRatting = VideoUtil.calculateRatting(ratting)
        return retrofitService.putVideoRating(id, newRatting)
    }
}
