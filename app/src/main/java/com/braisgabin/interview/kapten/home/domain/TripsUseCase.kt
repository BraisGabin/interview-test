package com.braisgabin.interview.kapten.home.domain

import arrow.core.Either
import arrow.core.right
import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.utils.Mockable
import io.reactivex.Single
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import javax.inject.Inject

@Mockable
class TripsUseCase @Inject constructor() {
  fun get(): Single<Either<Throwable, List<Trip>>> {
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

private fun trip(
  id: String,
  pilot: Pilot = pilot(),
  distance: Measure = measure(),
  duration: Duration = Duration.ZERO,
  pickUp: Place = place(),
  dropOff: Place = place()
) = Trip(id, pilot, distance, duration, pickUp, dropOff)

private fun pilot(
  name: String = "Darth Vador",
  pictureUrl: String = "https://starwars.kapten.com/static/dark-vador.png",
  rating: Float? = 5.0f
) = Pilot(name, pictureUrl, rating)

private fun measure(
  value: Long = 987654321,
  unit: String = "km"
) = Measure(value, unit)

private fun place(
  name: String = "Naboo",
  pictureUrl: String = "https://starwars.kapten.com/static/naboo.png",
  date: Instant = Instant.ofEpochSecond(123456789)
) = Place(name, pictureUrl, date)
