package app.concertor.sections.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.concertor.ConcertorApp
import app.concertor.R
import app.concertor.sections.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
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

        viewModel.homeScreenLiveData
                .observe(this, Observer {
                    viewState -> viewState?.let { render(it) }
                })

        viewModel.performIntent(HomeIntent.LoadEventsIntent("Cold"))

        btnQuit.setOnClickListener { viewModel.performIntent(HomeIntent.QuitIntent) }
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
            is HomeViewState.NavigationState -> {
                Timber.d("Received navigation state")
            }
        }
    }

    private fun handleNavigation(navigationState: HomeViewState.NavigationState) {
        when (navigationState.screenDest) {
            "quit" -> finish()
        }
    }
}
