package app.concertor.sections.home

import app.concertor.interactor.events.GetEventsUseCase
import app.concertor.mvi.BaseActionProcessor
import io.reactivex.ObservableTransformer
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.broadcast
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.channels.sendBlocking
import javax.inject.Inject

class HomeProcessor @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : BaseActionProcessor<HomeAction, HomeResult>() {

    override fun processAction(action: HomeAction): BroadcastChannel<HomeResult> {
        return produce {
            when (action) {
                is HomeAction.LoadEventsForArtist -> {
                    send(HomeResult.LoadEventsForArtistTask.loading())
                    try {
                        val events = getEventsUseCase.get(action.artistName)
                        send(HomeResult.LoadEventsForArtistTask.success(events))
                    } catch (exc: Exception) {
                        send(HomeResult.LoadEventsForArtistTask.failure(exc))
                    }
                }
            }
        }.broadcast()
    }

    override fun dispose() {
        getEventsUseCase.dispose()
    }
}
