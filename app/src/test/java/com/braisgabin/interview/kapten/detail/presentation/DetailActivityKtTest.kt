package com.braisgabin.interview.kapten.detail.presentation

import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.trip
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.Locale

class DetailActivityKtTest {

  @Test
  fun tripParse() {
    Locale.setDefault(Locale.US)
    assertThat(
      trip("1").parse(),
      `is`(
        ViewModel(
          "https://starwars.kapten.com/static/dark-vador.png",
          "Darth Vador",
          Section(R.string.pickUp, "Naboo", "10:33 PM"),
          Section(R.string.dropOff, "Naboo", "10:33 PM"),
          Section(R.string.tripDistance, "987,654,321 km"),
          Section(R.string.tripDuration, "3:05:07"),
          5f
        )
      )
    )
  }
}
