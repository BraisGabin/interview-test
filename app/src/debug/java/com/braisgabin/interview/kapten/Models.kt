package com.braisgabin.interview.kapten

import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import org.threeten.bp.Duration
import org.threeten.bp.Instant

fun trip(
  id: String,
  pilot: Pilot = pilot(),
  distance: Measure = measure(),
  duration: Duration = Duration.ZERO,
  pickUp: Place = place(),
  dropOff: Place = place()
) = Trip(id, pilot, distance, duration, pickUp, dropOff)

fun pilot(
  name: String = "Darth Vador",
  pictureUrl: String = "https://starwars.kapten.com/static/dark-vador.png",
  rating: Float? = 5.0f
) = Pilot(name, pictureUrl, rating)

fun measure(
  value: Long = 987654321,
  unit: String = "km"
) = Measure(value, unit)

fun place(
  name: String = "Naboo",
  pictureUrl: String = "https://starwars.kapten.com/static/naboo.png",
  date: Instant = Instant.ofEpochSecond(123456789)
) = Place(name, pictureUrl, date)
