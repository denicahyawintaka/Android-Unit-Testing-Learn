package com.example.hellounittest.api

import com.example.hellounittest.model.Video
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface RetrofitService {

    fun getAllVideo(): Observable<List<Video>>

    fun getVideo(id: String): Single<List<Video>>

    fun getAllVideo(minRatting: Int): Single<List<Video>>

    fun putVideoRating(ids:List<String>, ratting: Int): Completable
}
