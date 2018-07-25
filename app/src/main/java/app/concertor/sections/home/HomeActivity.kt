package app.concertor.sections.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import app.concertor.ConcertorApp
import app.concertor.R
import app.concertor.mvi.BaseView
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), BaseView<HomeIntent, HomeViewState> {

    @Inject lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ConcertorApp.appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)

        compositeDisposable.add(viewModel.states().subscribe { render(it) })

        viewModel.performIntent(HomeIntent.LoadEventsIntent("Cold"))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun render(state: HomeViewState) {
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
