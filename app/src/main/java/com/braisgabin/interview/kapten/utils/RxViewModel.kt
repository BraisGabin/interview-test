package com.braisgabin.interview.kapten.utils

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class RxViewModel : ViewModel() {
  protected val disposable = CompositeDisposable()

  override fun onCleared() {
    disposable.dispose()
  }
}
