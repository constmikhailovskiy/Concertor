package app.concertor.sections.home

import androidx.lifecycle.ViewModel
import app.concertor.mvi.BaseViewModel
import app.concertor.mvi.model.TaskStatus
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val eventsProcessor: HomeProcessor
) : ViewModel(), BaseViewModel<HomeIntent, HomeViewState> {

    private var intentSubject: PublishSubject<HomeIntent> = PublishSubject.create()

    private val reducer: BiFunction<HomeViewState, HomeResult, HomeViewState> =
            BiFunction { previousState, result ->
                when (result) {
                    is HomeResult.LoadEventsForArtistTask -> {
                        when (result.status) {
                            TaskStatus.SUCCESS -> HomeViewState.Success(result.events ?: emptyList())
                            TaskStatus.FAILURE -> HomeViewState.Failed(result.error!!)
                            TaskStatus.IN_FLIGHT -> HomeViewState.Loading
                            else -> HomeViewState.Idle
                        }
                    }
                }
            }

    private val statesSubject: Observable<HomeViewState> = getStatesFlow()

    override fun processIntents(intents: Observable<HomeIntent>) {
        intents.subscribe(intentSubject)
    }

    override fun states(): Observable<HomeViewState> = statesSubject

    fun performIntent(intent: HomeIntent) {
        intentSubject.onNext(intent)
    }

    private fun getStatesFlow(): Observable<HomeViewState> {
        Timber.d("Reducer: $reducer")
        return intentSubject
                .map { intentToAction(it) }
                .compose(eventsProcessor.actionProcessor)
                .scan(HomeViewState.Idle, reducer)
                .replay(1)
                .autoConnect(0)
    }

    private fun intentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadEventsIntent -> HomeAction.LoadEventsForArtist(intent.artistName)
        }
    }

}
