package com.braisgabin.interview.kapten.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.braisgabin.interview.kapten.R
import kotlinx.android.synthetic.main.view_section.view.*

class SectionView : LinearLayout {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  )

  init {
    LayoutInflater.from(context).inflate(R.layout.view_section, this, true)
    orientation = VERTICAL
  }

  fun bind(@StringRes header: Int, title: String, subtitle: String? = null) {
    headerTextView.setText(header)
    titleTextView.text = title
    subtitleTextView.text = subtitle
    subtitleTextView.visibility = if (subtitle != null) View.VISIBLE else View.GONE
  }
}
