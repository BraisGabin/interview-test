package com.braisgabin.interview.kapten

import android.app.Activity
import com.braisgabin.interview.kapten.detail.presentation.DetailActivity
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.utils.Mockable
import javax.inject.Inject

@Mockable
class Navigator @Inject constructor(
  private val activity: Activity
) {

  fun goToDetail(trip: Trip) {
    activity.startActivity(DetailActivity.getCallingIntent(activity, trip))
  }
}
