package com.braisgabin.interview.kapten.entity

import org.threeten.bp.Duration
import org.threeten.bp.Instant

data class Trip(
  val id: String,
  val pilot: Pilot,
  val distance: Measure,
  val duration: Duration,
  val pickUp: Place,
  val dropOff: Place
)

data class Pilot(
  val name: String,
  val pictureUrl: String,
  val rating: Float?
)

data class Measure(
  val value: Long,
  val unit: String
)

data class Place(
  val name: String,
  val pictureUrl: String,
  val date: Instant
)
