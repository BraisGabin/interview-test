package com.braisgabin.interview.kapten.home.data

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {

  @GET("trips/")
  @Headers("Accept: application/json")
  fun get(): Single<Response<List<TripMapper>>>
}

const val BASE_URL = "https://starwars.kapten.com"
