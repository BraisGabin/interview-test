package com.braisgabin.interview.kapten

import com.braisgabin.interview.kapten.home.data.ApiDataSource
import com.braisgabin.interview.kapten.home.domain.TripsRepository
import com.braisgabin.interview.kapten.home.presentation.MainActivity
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

@Component(modules = [AppModule::class])
interface AppComponent {

  fun mainActivityComponent(): MainActivity.Component.Factory
}

@Module
abstract class AppModule {

  @Binds
  abstract fun tripsRepositoryProvider(repository: ApiDataSource): TripsRepository

  @Module
  companion object {

    @JvmStatic
    @Provides
    @Named("main")
    fun mainProvider(): Scheduler = AndroidSchedulers.mainThread()
  }
}
