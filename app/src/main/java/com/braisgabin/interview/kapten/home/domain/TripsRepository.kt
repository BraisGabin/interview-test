package com.braisgabin.interview.kapten.home.domain

import arrow.core.Either
import com.braisgabin.interview.kapten.entity.Trip
import io.reactivex.Single

interface TripsRepository {

  fun get(): Single<Either<Throwable, List<Trip>>>
}
