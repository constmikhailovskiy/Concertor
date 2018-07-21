package app.concertor.sections.home

import app.concertor.CoroutinesContextProvider
import app.concertor.MviViewModel
import app.concertor.mvi.Action
import app.concertor.mvi.Intent

class HomeViewModel(
        private val coroutinesContextProvider: CoroutinesContextProvider
) : MviViewModel<HomeIntent, HomeAction, HomeState>(coroutinesContextProvider, HomeState.NoContent) {

    override suspend fun intentsReducer(lastState: HomeState, intent: Intent): HomeAction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun actionsReducer(lastState: HomeState, action: Action): HomeState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}