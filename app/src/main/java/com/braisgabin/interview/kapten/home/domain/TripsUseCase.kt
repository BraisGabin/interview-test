package com.braisgabin.interview.kapten.home.domain

import arrow.core.Either
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.utils.Mockable
import io.reactivex.Single
import javax.inject.Inject

@Mockable
class TripsUseCase @Inject constructor(
  private val repository: TripsRepository
) {
  fun get(): Single<Either<Throwable, List<Trip>>> {
    return repository.get()
  }
}
