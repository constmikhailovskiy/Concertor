package app.concertor.sections.home

import app.concertor.mvi.BaseAction

sealed class HomeAction : BaseAction {

    data class LoadEventsForArtist(val artistName: String) : HomeAction()
}
