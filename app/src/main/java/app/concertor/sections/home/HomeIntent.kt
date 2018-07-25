package app.concertor.sections.home

import app.concertor.mvi.BaseIntent

sealed class HomeIntent : BaseIntent {

    data class LoadEventsIntent(val artistName: String) : HomeIntent()
}
