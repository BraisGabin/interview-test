package com.braisgabin.interview.kapten

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen

class App : Application() {

  val component: AppComponent by lazy {
    DaggerAppComponent.builder().build()
  }

  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this)
  }
}

fun Context.appComponent(): AppComponent {
  return (this.applicationContext as App).component
}
