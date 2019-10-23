package com.braisgabin.interview.kapten.home.domain

import arrow.core.Either
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.utils.Mockable
import io.reactivex.Single

@Mockable
class TripsUseCase {
  fun get(): Single<Either<Throwable, List<Trip>>> {
    TODO("not implemented")
  }
}
