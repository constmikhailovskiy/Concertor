package app.concertor.mvi

import kotlinx.coroutines.experimental.channels.BroadcastChannel

abstract class BaseActionProcessor<A : BaseAction, R : BaseResult> {

    abstract fun processAction(action: A): BroadcastChannel<R>

    abstract fun dispose()
}
