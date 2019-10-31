package com.braisgabin.interview.kapten.home.domain

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Test

class TripsUseCaseTest {

  private val repository: TripsRepository = mock {
    on { get() } doReturn Single.never()
  }
  private val useCase: TripsUseCase = TripsUseCase(repository)

  @Test
  fun get() {
    useCase.get()

    verify(repository).get()
  }
}


