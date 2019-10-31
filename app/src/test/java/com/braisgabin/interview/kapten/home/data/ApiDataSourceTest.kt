package com.braisgabin.interview.kapten.home.data

import arrow.core.right
import com.braisgabin.interview.kapten.entity.Trip
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class ApiDataSourceTest {

  private val restApi: RestApi = mock()
  private val dataSource = ApiDataSource(restApi)

  @Test
  fun onSuccess() {
    whenever(restApi.get()) doReturn Single.just(Response.success(emptyList()))

    dataSource.get()
      .test()
      .assertValue(emptyList<Trip>().right())
  }

  @Test
  fun onSuccess_responseFail() {
    whenever(restApi.get()) doReturn Single.just(
      Response.error(404, "".toResponseBody("application/json".toMediaType()))
    )

    dataSource.get()
      .test()
      .assertValue { it.isLeft() }
  }

  @Test
  fun onError() {
    whenever(restApi.get()) doReturn Single.error(Throwable(""))

    dataSource.get()
      .test()
      .assertValue { it.isLeft() }
  }
}
