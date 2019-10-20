package com.braisgabin.interview.kapten.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.entity.Trip
import com.squareup.picasso.Picasso

class TripAdapter(
  private val listener: Listener
) : ListAdapter<Trip, ViewHolder>(TripDiffer) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.create(LayoutInflater.from(parent.context), parent, listener::clickListener)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  interface Listener {
    fun clickListener(trip: Trip)
  }
}

class ViewHolder(
  view: View,
  private val clickListener: (Trip) -> Unit
) : RecyclerView.ViewHolder(view) {

  fun bind(item: Trip) {
    itemView.setOnClickListener { clickListener(item) }
    Picasso.get()
      .load(item.pilot.pictureUrl)
      .fit()
      .centerCrop()
      .into(itemView.findViewById<ImageView>(R.id.avatar))

    itemView.findViewById<TextView>(R.id.pilot).text = item.pilot.name
    itemView.findViewById<TextView>(R.id.pickUp).text = item.pickUp.name
    itemView.findViewById<TextView>(R.id.dropOff).text = item.dropOff.name
  }

  companion object {
    fun create(
      inflater: LayoutInflater,
      parent: ViewGroup,
      clickListener: (Trip) -> Unit
    ): ViewHolder {
      return ViewHolder(inflater.inflate(R.layout.item_trip, parent, false), clickListener)
    }
  }
}

private object TripDiffer : DiffUtil.ItemCallback<Trip>() {
  override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
    return oldItem == newItem
  }
}
