package com.braisgabin.interview.kapten.home.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.domain.TripsRepository
import io.reactivex.Single
import javax.inject.Inject

class ApiDataSource @Inject constructor(
  private val restApi: RestApi
) : TripsRepository {

  override fun get(): Single<Either<Throwable, List<Trip>>> {
    return restApi.get()
      .map<Either<Throwable, List<Trip>>> { response ->
        if (response.isSuccessful) {
          response.body()!!.map { it.toDomain() }.right()
        } else {
          Exception("Something went wrong").left()
        }
      }
      .doOnError {
        it.printStackTrace()
      }
      .onErrorReturn { it.left() }
  }
}
