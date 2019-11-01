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

    val trip = intent.getParcelableExtra<TripDTO>(EXTRA_TRIP)!!.toDomain().parse()

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = ""

    Picasso.get()
      .load(trip.picture)
      .fit()
      .centerCrop()
      .into(imageView)

    pilotName.text = trip.name

    pickUp.bind(trip.pickUp)
    dropOff.bind(trip.dropOff)
    distance.bind(trip.distance)
    duration.bind(trip.duration)
    rating.bind(trip.rating)
  }

  companion object {
    fun getCallingIntent(context: Context, trip: Trip): Intent {
      return Intent(context, DetailActivity::class.java)
        .putExtra(EXTRA_TRIP, TripDTO(trip))
    }
  }
}

private fun SectionView.bind(section: Section) {
  bind(section.header, section.title, section.subtitle)
}

private fun Measure.format(): String {
  return "%,d $unit".format(value)
}

private fun Duration.format(): String {
  val hours = toHours()
  val minutes = minusHours(hours).toMinutes()
  val seconds = minusHours(hours).minusMinutes(minutes).toMillis() / 1000
  return "%d:%02d:%02d".format(hours, minutes, seconds)
}

private fun Instant.format(): String {
  val localTime = LocalTime.from(this.atZone(ZoneId.systemDefault()))
  return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(localTime)
}

private const val EXTRA_TRIP = "EXTRA_TRIP"

fun Trip.parse(): ViewModel {
  return ViewModel(
    pilot.pictureUrl,
    pilot.name,
    Section(R.string.pickUp, pickUp.name, pickUp.date.format()),
    Section(R.string.dropOff, pickUp.name, pickUp.date.format()),
    Section(R.string.tripDistance, distance.format()),
    Section(R.string.tripDuration, duration.format()),
    pilot.rating
  )
}

data class ViewModel(
  val picture: String,
  val name: String,
  val pickUp: Section,
  val dropOff: Section,
  val distance: Section,
  val duration: Section,
  val rating: Float?
)

data class Section(
  val header: Int,
  val title: String,
  val subtitle: String? = null
)
