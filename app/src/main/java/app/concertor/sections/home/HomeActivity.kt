package app.concertor.sections.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.concertor.ConcertorApp
import app.concertor.R
import app.concertor.sections.base.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ConcertorApp.appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)

        viewModel.states
                .observe(this, Observer {
                    viewState -> viewState?.let { render(it) }
                })

        viewModel.performIntent(HomeIntent.LoadEventsIntent("Cold"))
    }

    private fun render(state: HomeViewState) {
        Timber.d("State received: $state")
        when (state) {
            HomeViewState.Loading -> {
                Timber.d("In loading state")
            }
            is HomeViewState.Failed -> {
                Timber.d("In failed state: ${state.error.message}")
            }
            is HomeViewState.Success -> {
                Timber.d("Loaded events successfully: ${state.events}")
            }
        }
    }
}
