package app.concertor.sections.home

import app.concertor.repository.models.EventEntry
import app.concertor.mvi.State

sealed class HomeState : State {

    object NoContent : HomeState()

    object Loading : HomeState()

    data class Loaded(val events: List<EventEntry>) : HomeState()

    data class Failure(val reason: String) : HomeState()
}
