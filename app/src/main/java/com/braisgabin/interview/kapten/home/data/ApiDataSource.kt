package com.braisgabin.interview.kapten.home.data

import arrow.core.Either
import arrow.core.right
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.domain.TripsRepository
import com.braisgabin.interview.kapten.trip
import io.reactivex.Single
import javax.inject.Inject

class ApiDataSource @Inject constructor() : TripsRepository {

  override fun get(): Single<Either<Throwable, List<Trip>>> {
    return Single.just(
      listOf(
        trip("1"),
        trip("2"),
        trip("3"),
        trip("4")
      ).right()
    )
  }
}
