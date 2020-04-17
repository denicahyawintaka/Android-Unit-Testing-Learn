package com.example.hellounittest.repo

import com.example.hellounittest.model.Video
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface VideoRepository {

    fun getAllVideo(): Observable<List<Video>>

    fun getVideoById(id: String): Single<List<Video>>

    fun getAllVideoWithMinimumRatting(minRatting: Int): Single<List<Video>>

    fun putVideoRating(id:String, ratting: Int): Completable
}
