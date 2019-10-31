package com.braisgabin.interview.kapten.home.presentation.feature

import com.badoo.mvicore.element.Reducer

class HomeReducer : Reducer<State, Effect> {
  override fun invoke(state: State, effect: Effect): State {
    return when (effect) {
      Effect.Load -> State.Load
      Effect.Error -> State.Error
      is Effect.Data -> State.Data(effect.trips)
    }
  }
}
