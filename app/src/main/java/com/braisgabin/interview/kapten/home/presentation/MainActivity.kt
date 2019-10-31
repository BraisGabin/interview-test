package com.braisgabin.interview.kapten.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.ApplicationModule
import com.braisgabin.interview.kapten.home.presentation.feature.State
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TripAdapter.Listener {

  @Inject
  internal lateinit var presenter: HomePresenter

  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    DaggerMainActivity_Component
      .builder()
      .build()
      .inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setTitle(R.string.home_title)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL).apply {
      setDrawable(AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)!!)
    })
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
      State.Load -> {}
      State.Error -> {}
      is State.Data -> {
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

  @dagger.Component(modules = [ApplicationModule::class])
  interface Component {

    fun inject(activity: MainActivity)
  }
}
