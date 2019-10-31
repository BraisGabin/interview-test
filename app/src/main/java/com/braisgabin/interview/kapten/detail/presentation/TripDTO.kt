package com.braisgabin.interview.kapten.detail.presentation

import android.os.Parcelable
import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Duration
import org.threeten.bp.Instant

@Parcelize
class TripDTO(
  val id: String,
  val pilot: PilotDTO,
  val distance: MeasureDTO,
  val duration: Long,
  val pickUp: PlaceDTO,
  val dropOff: PlaceDTO
) : Parcelable {
  constructor(it: Trip) : this(
    it.id,
    PilotDTO(it.pilot),
    MeasureDTO(it.distance),
    it.duration.toMillis(),
    PlaceDTO(it.pickUp),
    PlaceDTO(it.dropOff)
  )

  fun toDomain() = Trip(
    id,
    pilot.toDomain(),
    distance.toDomain(),
    Duration.ofMillis(duration),
    pickUp.toDomain(),
    dropOff.toDomain()
  )
}

@Parcelize
class PilotDTO(
  val name: String,
  val pictureUrl: String,
  val rating: Float?
) : Parcelable {
  constructor(it: Pilot) : this(it.name, it.pictureUrl, it.rating)

  fun toDomain() = Pilot(name, pictureUrl, rating)
}

@Parcelize
class MeasureDTO(
  val value: Long,
  val unit: String
) : Parcelable {
  constructor(it: Measure) : this(it.value, it.unit)

  fun toDomain() = Measure(value, unit)
}

@Parcelize
class PlaceDTO(
  val name: String,
  val pictureUrl: String,
  val date: Long
) : Parcelable {
  constructor(it: Place) : this(it.name, it.pictureUrl, it.date.toEpochMilli())

  fun toDomain() = Place(name, pictureUrl, Instant.ofEpochMilli(date))
}
