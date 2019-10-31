package com.braisgabin.interview.kapten.detail.presentation

import com.braisgabin.interview.kapten.trip
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TripDTOTest {

  @Test
  fun toDomain() {
    val trip = trip("1")

    assertThat(TripDTO(trip).toDomain(), `is`(trip))
  }
}
