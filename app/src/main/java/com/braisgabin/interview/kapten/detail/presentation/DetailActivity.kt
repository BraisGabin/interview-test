package com.braisgabin.interview.kapten.detail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.entity.Trip

class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)
  }

  companion object {
    fun getCallingIntent(context: Context, trip: Trip): Intent {
      return Intent(context, DetailActivity::class.java)
        .putExtra(EXTRA_ID, TripDTO(trip))
    }
  }
}

private const val EXTRA_ID = "EXTRA_ID"
