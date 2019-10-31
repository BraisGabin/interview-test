package com.braisgabin.interview.kapten.home.presentation.feature

import com.badoo.mvicore.feature.BaseFeature
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.domain.TripsUseCase
import com.braisgabin.interview.kapten.utils.Mockable
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@Mockable
class HomeFeature @Inject constructor(
  tripsUseCase: TripsUseCase,
  @Named("main") main: Scheduler
) : BaseFeature<Wish, Action, Effect, State, Nothing>(
  initialState = State.Load,
  bootstrapper = { Observable.just(Action.Load) },
  actor = HomeActor(tripsUseCase, main),
  reducer = HomeReducer(),
  wishToAction = { wish ->
    when (wish) {
      Wish.Load -> Action.Load
    }
  }
)

sealed class Wish {
  object Load : Wish()
}

sealed class Action {
  object Load : Action()
}

sealed class Effect {
  object Load : Effect()
  object Error : Effect()
  data class Data(val trips: List<Trip>) : Effect()
}

sealed class State {
  object Load : State()
  object Error : State()
  data class Data(val trips: List<Trip>) : State()
}
