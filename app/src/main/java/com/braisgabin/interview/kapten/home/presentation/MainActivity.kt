package com.braisgabin.interview.kapten.home.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.braisgabin.interview.kapten.Navigator
import com.braisgabin.interview.kapten.R
import com.braisgabin.interview.kapten.appComponent
import com.braisgabin.interview.kapten.entity.Trip
import com.braisgabin.interview.kapten.home.presentation.feature.HomeFeature
import com.braisgabin.interview.kapten.home.presentation.feature.State
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity(), TripAdapter.Listener {

  @Inject
  internal lateinit var presenter: HomePresenter

  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    appComponent()
      .mainActivityComponent()
      .create(this)
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
    presenter.events.accept(HomeIntents.Click(trip))
  }

  private fun allGoneExcept(view: View) {
    progressBar.goneIfNot(view)
    retry.goneIfNot(view)
    recyclerView.goneIfNot(view)
  }

  private fun View.goneIfNot(view: View) {
    this.visibility = if (this == view) View.VISIBLE else View.GONE
  }

  @Subcomponent(modules = [MyModule::class])
  interface Component {

    @Subcomponent.Factory
    interface Factory {
      fun create(@BindsInstance activity: AppCompatActivity): Component
    }

    fun inject(activity: MainActivity)
  }

  @Module
  abstract class MyModule {

    @Binds
    abstract fun activityProvider(activity: AppCompatActivity): Activity

    @Module
    companion object {

      @JvmStatic
      @Provides
      fun presenterProvider(
        activity: AppCompatActivity,
        homeFeature: Provider<HomeFeature>,
        navigator: Navigator
      ): HomePresenter {
        return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {

          override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return HomePresenter(homeFeature.get(), navigator) as T
          }
        }).get(HomePresenter::class.java)
      }
    }
  }
}
