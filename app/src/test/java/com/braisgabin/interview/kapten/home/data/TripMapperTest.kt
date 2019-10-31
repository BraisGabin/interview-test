package com.braisgabin.interview.kapten.home.data

import com.braisgabin.interview.kapten.entity.Measure
import com.braisgabin.interview.kapten.entity.Pilot
import com.braisgabin.interview.kapten.entity.Place
import com.braisgabin.interview.kapten.entity.Trip
import com.squareup.moshi.Moshi
import okio.BufferedSource
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.threeten.bp.Duration
import org.threeten.bp.Instant

class TripMapperTest {
  private val moshi: Moshi = Moshi.Builder().build()
  private val jsonAdapter = moshi.adapter<TripMapper>(TripMapper::class.java)

  @Test
  fun toDomain() {
    val reader = getResourceAsBuffer("json/trip.json")
    val item = jsonAdapter.fromJson(reader)!!.toDomain()

    MatcherAssert.assertThat(
      item,
      CoreMatchers.`is`(
        Trip(
          "1",
          Pilot("Dark Vador", "https://starwars.kapten.com/static/dark-vador.png", 5f),
          Measure(2478572, "km"),
          Duration.ofMillis(19427000),
          Place(
            "Yavin 4",
            "https://starwars.kapten.com/static/yavin-4.png",
            Instant.parse("2017-12-09T14:12:51Z")
          ),
          Place(
            "Naboo",
            "https://starwars.kapten.com/static/naboo.png",
            Instant.parse("2017-12-09T19:35:51Z")
          )
        )
      )
    )
  }

  @Test
  fun toDomain_noRating() {
    val reader = getResourceAsBuffer("json/trip_no_rating.json")
    val item = jsonAdapter.fromJson(reader)!!.toDomain()

    MatcherAssert.assertThat(
      item,
      CoreMatchers.`is`(
        Trip(
          "1",
          Pilot("Dark Vador", "https://starwars.kapten.com/static/dark-vador.png", null),
          Measure(2478572, "km"),
          Duration.ofMillis(19427000),
          Place(
            "Yavin 4",
            "https://starwars.kapten.com/static/yavin-4.png",
            Instant.parse("2017-12-09T14:12:51Z")
          ),
          Place(
            "Naboo",
            "https://starwars.kapten.com/static/naboo.png",
            Instant.parse("2017-12-09T19:35:51Z")
          )
        )
      )
    )
  }
}

fun Any.getResourceAsBuffer(name: String): BufferedSource {
  return javaClass.classLoader!!.getResourceAsStream(name)!!.source().buffer()
}
