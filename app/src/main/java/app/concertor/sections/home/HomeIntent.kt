package app.concertor.sections.home

import app.concertor.mvi.Intent

sealed class HomeIntent : Intent {

    data class LoadConcertsIntent(val artistName: String)
}