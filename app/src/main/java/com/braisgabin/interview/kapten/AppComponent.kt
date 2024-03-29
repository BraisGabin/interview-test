package com.braisgabin.interview.kapten

import android.os.Looper
import com.braisgabin.interview.kapten.home.data.ApiDataSource
import com.braisgabin.interview.kapten.home.data.BASE_URL
import com.braisgabin.interview.kapten.home.data.RestApi
import com.braisgabin.interview.kapten.home.domain.TripsRepository
import com.braisgabin.interview.kapten.home.presentation.MainActivity
import dagger.Binds
import dagger.Component
import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Singleton
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

    @JvmStatic
    @Provides
    @Singleton
    fun okhttpProvider(): OkHttpClient {
      check(Looper.getMainLooper() != Looper.myLooper()) { "Initializing OkHttpClient on main thread." }
      val builder = OkHttpClient().newBuilder()
      if (BuildConfig.DEBUG) {
        builder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BASIC
        })
      }
      return builder.build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: Lazy<OkHttpClient>): Retrofit {
      return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(object : Call.Factory {
          override fun newCall(request: Request): Call {
            return okHttpClient.get().newCall(request)
          }
        })
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    }

    @JvmStatic
    @Provides
    fun restApiProvider(retrofit: Retrofit): RestApi = retrofit.create()
  }
}
