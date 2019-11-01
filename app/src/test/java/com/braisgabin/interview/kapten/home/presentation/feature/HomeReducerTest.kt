package com.braisgabin.interview.kapten.home.presentation.feature

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HomeReducerTest {

  private val reducer = HomeReducer()

  @Test
  fun load() {
    assertThat(reducer.invoke(State.Load, Effect.Load), `is`<State>(State.Load))
  }

  @Test
  fun error() {
    assertThat(reducer.invoke(State.Load, Effect.Error), `is`<State>(State.Error))
  }

  @Test
  fun data() {
    assertThat(
      reducer.invoke(State.Load, Effect.Data(emptyList())),
      `is`<State>(State.Data(emptyList()))
    )
  }
}
