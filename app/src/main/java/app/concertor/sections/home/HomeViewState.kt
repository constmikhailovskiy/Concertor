package app.concertor.sections.home

import app.concertor.models.Event
import app.concertor.mvi.BaseViewState

sealed class HomeViewState(
        open val loading: Boolean = false,
        open val events: List<Event>? = null,
        open val error: Throwable? = null
) : BaseViewState {

    object Loading : HomeViewState(loading = true)

    data class Failed(override val error: Throwable) : HomeViewState(error = error)

    data class Success(override val events: List<Event>) : HomeViewState(events = events)

    data class NavigationState(val screenDest: String) : HomeViewState()

    object Idle : HomeViewState()
}
