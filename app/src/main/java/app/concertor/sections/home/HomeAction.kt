package app.concertor.sections.home

import app.concertor.mvi.Action

sealed class HomeAction : Action {

    data class Download(val artistName: String)
}