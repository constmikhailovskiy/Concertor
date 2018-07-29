package app.concertor.mvi

import androidx.lifecycle.ViewModel
import app.concertor.coroutines.Coroutines
import kotlinx.coroutines.experimental.channels.*
import kotlinx.coroutines.experimental.launch

abstract class MviViewModel<I : BaseIntent, A : BaseAction, R : BaseResult, VS : BaseViewState, AP : BaseActionProcessor<A, R>>(
        private val actionsProcessor: AP,
        initialState: VS
) : ViewModel() {

    private val intents = actor<I>(Coroutines.io(), Channel.CONFLATED) {
        consumeEach { actions.send(intentToAction(lastState(), it)) }
    }

    private val actions = actor<A>(Coroutines.io(), Channel.CONFLATED) {
        consumeEach {
            actionsProcessor.processAction(it)
                    .consumeEach { state.send(resultToViewState(lastState(), it)) }
        }
    }

    private val state = ConflatedBroadcastChannel(initialState)
    private var stateSubscription: ReceiveChannel<VS>? = null

    init {
        stateSubscription = state.openSubscription().also {
            launch(Coroutines.main()) {
                it.distinct().consumeEach { onStateReceived(it) }
            }
        }
    }

    abstract fun intentToAction(lastState: VS, intent: I): A

    abstract fun resultToViewState(lastState: VS, result: R): VS

    abstract fun onStateReceived(viewState: VS)

    fun performIntent(intent: I) {
        intents.offer(intent)
    }

    override fun onCleared() {
        actionsProcessor.dispose()
        stateSubscription?.cancel()
        super.onCleared()
    }

    private fun lastState() = state.value
}
