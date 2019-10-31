package com.braisgabin.interview.kapten.home

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

@Module
object ApplicationModule {

  @Provides
  @Named("main")
  fun mainProvider(): Scheduler = AndroidSchedulers.mainThread()
}
