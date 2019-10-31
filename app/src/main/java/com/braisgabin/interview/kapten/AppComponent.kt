package com.braisgabin.interview.kapten

import com.braisgabin.interview.kapten.home.presentation.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

@Component(modules = [AppModule::class])
interface AppComponent {

  fun mainActivityComponent(): MainActivity.Component
}

@Module
object AppModule {

  @Provides
  @Named("main")
  fun mainProvider(): Scheduler = AndroidSchedulers.mainThread()
}
