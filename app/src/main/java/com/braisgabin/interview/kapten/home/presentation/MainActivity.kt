package com.braisgabin.interview.kapten.home.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.appComponent
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.presentation.feature.State
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TripAdapter.Listener {

  @Inject
  internal lateinit var presenter: HomePresenter

  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    appComponent()
      .mainActivityComponent()
      .inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setTitle(R.string.home_title)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL).apply {
      setDrawable(AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)!!)
    })

    retry.setOnClickListener { presenter.events.accept(HomeIntents.Retry) }
  }

  override fun onStart() {
    super.onStart()
    disposable.add(presenter.states
      .subscribe(this::render) {
        throw RuntimeException(it)
      })
  }

  override fun onStop() {
    super.onStop()
    disposable.clear()
  }

  private fun render(state: State) {
    when (state) {
      State.Load -> allGoneExcept(progressBar)
      State.Error -> allGoneExcept(retry)
      is State.Data -> {
        allGoneExcept(recyclerView)
        val adapter = (recyclerView.adapter as? TripAdapter ?: TripAdapter(this))
        adapter.submitList(state.trips)
        if (recyclerView.adapter !== adapter) {
          recyclerView.adapter = adapter
        }
      }
    }
  }

  override fun clickListener(trip: Trip) {
    TODO("not implemented")
  }

  private fun allGoneExcept(view: View) {
    progressBar.goneIfNot(view)
    retry.goneIfNot(view)
    recyclerView.goneIfNot(view)
  }

  private fun View.goneIfNot(view: View) {
    this.visibility = if (this == view) View.VISIBLE else View.GONE
  }

  @Subcomponent
  interface Component {

    fun inject(activity: MainActivity)
  }
}
