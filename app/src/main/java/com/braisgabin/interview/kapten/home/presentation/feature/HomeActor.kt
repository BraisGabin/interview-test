package com.braisgabin.interview.kapten.home.presentation.feature

import com.badoo.mvicore.element.Actor
import com.braisgabin.interview.kapten.home.domain.TripsUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class HomeActor(
  private val tripsUseCase: TripsUseCase,
  private val main: Scheduler
) : Actor<State, Action, Effect> {
  override fun invoke(state: State, action: Action): Observable<out Effect> {
    return when (action) {
      Action.Load -> tripsUseCase.get()
        .map { either ->
          either.fold(
            { Effect.Error },
            { Effect.Data(it) })
        }
        .toObservable()
        .observeOn(main)
        .startWith(Effect.Load)
    }
  }
}
