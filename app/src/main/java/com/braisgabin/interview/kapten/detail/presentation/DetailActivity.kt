package com.braisgabin.interview.kapten.detail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Trip
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle


class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val trip = intent.getParcelableExtra<TripDTO>(EXTRA_TRIP)!!.toDomain()

    Picasso.get()
      .load(trip.pilot.pictureUrl)
      .fit()
      .centerCrop()
      .into(imageView)

    pilotName.text = trip.pilot.name

    pickUp.bind(
      R.string.pickUp,
      trip.pickUp.name,
      trip.pickUp.date.format()
    )
    dropOff.bind(
      R.string.dropOff,
      trip.dropOff.name,
      trip.dropOff.date.format()
    )
    distance.bind(
      R.string.tripDistance,
      trip.distance.format()
    )
    duration.bind(R.string.tripDuration, trip.duration.format())

    rating.bind(trip.pilot.rating)
  }

  companion object {
    fun getCallingIntent(context: Context, trip: Trip): Intent {
      return Intent(context, DetailActivity::class.java)
        .putExtra(EXTRA_TRIP, TripDTO(trip))
    }
  }
}

private fun Measure.format(): String {
  return "%,d $unit".format(value)
}

private fun Duration.format(): String {
  val hours = toHours()
  val minutes = minusHours(hours).toMinutes()
  val seconds = minusHours(hours).minusMinutes(minutes).toMillis() / 1000
  return "$hours:$minutes:$seconds"
}

private fun Instant.format(): String {
  val localTime = LocalTime.from(this.atZone(ZoneId.systemDefault()))
  return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(localTime)
}

private const val EXTRA_TRIP = "EXTRA_TRIP"
