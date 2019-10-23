package com.braisgabin.interview.kapten.home.presentation.feature

import com.braisgabin.interview.kapten.entity.Trip

sealed class Wish
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
