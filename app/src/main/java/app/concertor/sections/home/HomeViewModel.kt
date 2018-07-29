package app.concertor.sections.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.concertor.coroutines.Coroutines
import app.concertor.mvi.model.TaskStatus
import kotlinx.coroutines.experimental.channels.*
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

open class HomeViewModel @Inject constructor(
        private val eventsProcessor: HomeProcessor
) : ViewModel() {

    private val intents = actor<HomeIntent>(Coroutines.io(), Channel.CONFLATED) {
        consumeEach { actions.send(intentToAction(lastState(), it)) }
    }

    private val actions = actor<HomeAction>(Coroutines.io(), Channel.CONFLATED) {
        consumeEach {
            eventsProcessor.actionProcessor(it)
                    .consumeEach { state.send(resultToViewState(lastState(), it)) }
        }
    }

    private val state = ConflatedBroadcastChannel<HomeViewState>(HomeViewState.Idle)
    private var stateSubscription: ReceiveChannel<HomeViewState>? = null

    val homeScreenLiveData: MutableLiveData<HomeViewState> = MutableLiveData()

    init {
        stateSubscription = state.openSubscription().also {
            launch(Coroutines.main()) {
                it.distinct().consumeEach { homeScreenLiveData.value = it }
            }
        }
    }

    fun performIntent(intent: HomeIntent) {
        intents.offer(intent)
    }

    fun lastState() = state.value

    override fun onCleared() {
        eventsProcessor.stop()
        stateSubscription?.cancel()
        super.onCleared()
    }

    private fun intentToAction(previousState: HomeViewState, intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadEventsIntent -> HomeAction.LoadEventsForArtist(
                    artistName = intent.artistName
            )
        }
    }

    private fun resultToViewState(previousState: HomeViewState, result: HomeResult): HomeViewState {
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
