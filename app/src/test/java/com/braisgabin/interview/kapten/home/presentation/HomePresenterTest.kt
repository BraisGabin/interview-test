package com.braisgabin.interview.kapten.home.presentation

import com.braisgabin.interview.kapten.Navigator
import com.braisgabin.interview.kapten.home.presentation.feature.HomeFeature
import com.braisgabin.interview.kapten.home.presentation.feature.State
import com.braisgabin.interview.kapten.home.presentation.feature.Wish
import com.braisgabin.interview.kapten.trip
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Observer
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomePresenterTest {

  private lateinit var stateObserver: Observer<State>
  private lateinit var testSubscriber: TestSubscriber<State>
  private val feature: HomeFeature = mock()
  private val navigator: Navigator = mock()
  private val presenter = HomePresenter(feature, navigator)

  @Before
  fun setUp() {
    testSubscriber = presenter.states
      .test()
    argumentCaptor<Observer<State>>().apply {
      verify(feature).subscribe(capture())
      stateObserver = firstValue
    }
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(feature, navigator)
  }

  @Test
  fun sendWishesToFeature() {
    presenter.events.accept(HomeIntents.Retry)

    verify(feature).accept(Wish.Load)
  }

  @Test
  fun sendWishesToNavigator() {
    val trip = trip("1")
    presenter.events.accept(HomeIntents.Click(trip))

    verify(navigator).goToDetail(trip)
  }

  @Test
  fun emitNewStates() {
    stateObserver.onNext(State.Data(emptyList()))

    testSubscriber.assertValue(State.Data(emptyList()))
  }
}
