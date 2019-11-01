package com.braisgabin.interview.kapten.home.presentation

import com.braisgabin.interview.kapten.Navigator
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.presentation.feature.HomeFeature
import com.braisgabin.interview.kapten.home.presentation.feature.State
import com.braisgabin.interview.kapten.home.presentation.feature.Wish
import com.braisgabin.interview.kapten.utils.RxViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Consumer

class HomePresenter(
  homeFeature: HomeFeature,
  navigator: Navigator
) : RxViewModel() {

  val events: Consumer<HomeIntents>
  val states: Flowable<State> = Observable.wrap(homeFeature)
    .replay(1)
    .autoConnect(1) { disposable.add(it) }
    .toFlowable(BackpressureStrategy.LATEST)

  init {
    disposable.add(homeFeature)
    val events: Relay<HomeIntents> = PublishRelay.create()
    disposable.add(events
      .map { intent ->
        when (intent) {
          HomeIntents.Retry -> homeFeature.accept(Wish.Load)
          is HomeIntents.Click -> navigator.goToDetail(intent.trip)
        }
      }
      .ignoreElements()
      .subscribe())
    this.events = events
  }
}

sealed class HomeIntents {
  object Retry : HomeIntents()
  data class Click(val trip: Trip) : HomeIntents()
}
