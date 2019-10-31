package com.braisgabin.interview.kapten.home.presentation

import com.braisgabin.interview.kapten.home.presentation.feature.HomeFeature
import com.braisgabin.interview.kapten.home.presentation.feature.State
import com.braisgabin.interview.kapten.home.presentation.feature.Wish
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observer
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

class HomePresenterTest {

  private lateinit var stateObserver: Observer<State>
  private lateinit var testSubscriber: TestSubscriber<State>
  private val feature: HomeFeature = mock()
  private val presenter = HomePresenter(feature)

  @Before
  fun setUp() {
    testSubscriber = presenter.states
      .test()
    argumentCaptor<Observer<State>>().apply {
      verify(feature).subscribe(capture())
      stateObserver = firstValue
    }
  }

  @Test
  fun sendWishesToFeature() {
    presenter.events.accept(HomeIntents.Retry)

    verify(feature).accept(Wish.Load)
  }

  @Test
  fun emitNewStates() {
    stateObserver.onNext(State.Data(emptyList()))

    testSubscriber.assertValue(State.Data(emptyList()))
  }
}
