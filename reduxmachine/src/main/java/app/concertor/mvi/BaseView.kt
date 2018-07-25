package app.concertor.mvi

interface BaseView<I : BaseIntent, in S : BaseViewState> {

    fun render(state: S)
}