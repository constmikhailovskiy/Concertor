package app.concertor.sections.home

import androidx.lifecycle.MutableLiveData
import app.concertor.mvi.MviViewModel
import app.concertor.mvi.model.TaskStatus
import javax.inject.Inject

open class HomeViewModel @Inject constructor(
        eventsProcessor: HomeProcessor
) : MviViewModel<HomeIntent, HomeAction, HomeResult, HomeViewState, HomeProcessor>(
        initialState = HomeViewState.Idle, actionsProcessor = eventsProcessor
) {

    val states: MutableLiveData<HomeViewState> = MutableLiveData()

    init {
        startTrackingStates()
    }

    override fun onStateReceived(viewState: HomeViewState) {
        println("State received: $viewState")
        states.value = viewState
    }

    override fun intentToAction(lastState: HomeViewState, intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadEventsIntent -> HomeAction.LoadEventsForArtist(
                    artistName = intent.artistName
            )
        }
    }

    override fun resultToViewState(lastState: HomeViewState, result: HomeResult): HomeViewState {
        return when (result) {
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
}
