package app.concertor

import app.concertor.mvi.Action
import app.concertor.mvi.Intent
import app.concertor.mvi.State

interface MviRenderer<I : Intent, A : Action, S : State> {

    val viewModel: MviViewModel<I, A, S>

    fun render(state: State)
}
