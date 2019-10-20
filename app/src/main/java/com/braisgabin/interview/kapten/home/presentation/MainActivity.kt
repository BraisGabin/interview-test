package com.braisgabin.interview.kapten.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.Duration
import org.threeten.bp.Instant

class MainActivity : AppCompatActivity(), TripAdapter.Listener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setTitle(R.string.home_title)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL).apply {
      setDrawable(AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)!!)
    })
    recyclerView.adapter = TripAdapter(this).apply {
      submitList(
        listOf(
          trip("1"),
          trip("2"),
          trip("3"),
          trip("4"),
          trip("5"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6"),
          trip("6")
        )
      )
    }
  }

  override fun clickListener(trip: Trip) {
    TODO("not implemented")
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
