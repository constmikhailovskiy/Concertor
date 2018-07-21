package app.concertor

import androidx.lifecycle.ViewModel
import app.concertor.mvi.Action
import app.concertor.mvi.Intent
import app.concertor.mvi.State
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.*
import kotlinx.coroutines.experimental.launch

abstract class MviViewModel<I : Intent, A : Action, S : State>(
        private val coroutinesContextProvider: CoroutinesContextProvider,
        initialState: S
) : ViewModel() {

    private val intents = actor<I>(coroutinesContextProvider.IO, Channel.CONFLATED) {
        consumeEach { actions.send(intentsReducer(latestState(), it)) }
    }

    private val actions = actor<A>(coroutinesContextProvider.IO, Channel.CONFLATED) {
        consumeEach { state.send(actionsReducer(latestState(), it)) }
    }

    private val state = ConflatedBroadcastChannel(initialState)

    private var stateSubscriber: ReceiveChannel<S>? = null

    private var renderHandler: Job? = null

    protected abstract suspend fun intentsReducer(lastState: S, intent: Intent): A

    protected abstract suspend fun actionsReducer(lastState: S, action: Action): S

    fun startRendering(renderer: MviRenderer<I, A, S>) {
        disposeRendering()
        stateSubscriber = state.openSubscription().also { channel ->
            renderHandler = launch(coroutinesContextProvider.Main) {
                channel.consumeEach { renderer.render(it) }
            }
        }
    }

    fun disposeRendering() {
        renderHandler?.cancel()
        stateSubscriber?.cancel()
    }

    fun latestState() = state.value

    fun triggerIntent(intent: I) = intents.offer(intent)

    override fun onCleared() {
        super.onCleared()
        disposeRendering()
    }
}
