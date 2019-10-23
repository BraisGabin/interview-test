package com.braisgabin.interview.kapten.home.presentation.feature

import arrow.core.left
import arrow.core.right
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.domain.TripsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class HomeActorTest {

  private val tripsUseCase: TripsUseCase = mock()
  private val actor = HomeActor(tripsUseCase, Schedulers.trampoline())

  @Test
  fun success() {
    whenever(tripsUseCase.get()).thenReturn(Single.just(emptyList<Trip>().right()))

    actor.invoke(State.Load, Action.Load)
      .cast(Effect::class.java)
      .test()
      .assertValues(
        Effect.Load,
        Effect.Data(emptyList())
      )
  }

  @Test
  fun unsuccessful() {
    whenever(tripsUseCase.get()).thenReturn(Single.just(Throwable().left()))

    actor.invoke(State.Load, Action.Load)
      .cast(Effect::class.java)
      .test()
      .assertValues(
        Effect.Load,
        Effect.Error
      )
  }
}
