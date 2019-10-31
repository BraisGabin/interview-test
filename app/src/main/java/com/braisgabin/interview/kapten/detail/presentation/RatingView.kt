package com.braisgabin.interview.kapten.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.braisgabin.interview.kapten.R
import kotlinx.android.synthetic.main.view_rating.view.*

class RatingView : LinearLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  )

  init {
    LayoutInflater.from(context).inflate(R.layout.view_rating, this, true)
    orientation = VERTICAL
  }

  fun bind(rating: Float?) {
    if (rating != null) {
      ratingBar.rating = rating
    }
    ratingBar.visibility = if (rating != null) View.VISIBLE else View.GONE
    noRatingTextView.visibility = if (rating == null) View.VISIBLE else View.GONE
  }
}
