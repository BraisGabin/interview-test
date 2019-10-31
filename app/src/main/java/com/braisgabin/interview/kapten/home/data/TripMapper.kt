package com.braisgabin.interview.kapten.home.data

import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Duration
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
class TripMapper(
  val id: Long,
  val pilot: PilotMapper,
  val distance: MeasureMapper,
  val duration: Long,
  @Json(name = "pick_up") val pickUp: PlaceMapper,
  @Json(name = "drop_off") val dropOff: PlaceMapper
) {
  fun toDomain() = Trip(
    id.toString(),
    pilot.toDomain(),
    distance.toDomain(),
    Duration.ofMillis(duration),
    pickUp.toDomain(),
    dropOff.toDomain()
  )
}

@JsonClass(generateAdapter = true)
class PilotMapper(
  val name: String,
  val avatar: String,
  val rating: Float
) {
  fun toDomain() = Pilot(name, BASE_URL + avatar, if (rating == 0f) null else rating)
}

@JsonClass(generateAdapter = true)
class MeasureMapper(
  val value: Long,
  val unit: String
) {
  fun toDomain() = Measure(value, unit)
}

@JsonClass(generateAdapter = true)
class PlaceMapper(
  val name: String,
  val picture: String,
  val date: String
) {
  fun toDomain() = Place(name, BASE_URL + picture, Instant.parse(date))
}
