package app.concertor.sections.home

import app.concertor.interactor.events.GetEventsUseCase
import io.reactivex.ObservableTransformer
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.broadcast
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.channels.sendBlocking
import javax.inject.Inject

class HomeProcessor @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) {

    fun actionProcessor(action: HomeAction): BroadcastChannel<HomeResult> = produce {
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

    fun stop() {
        getEventsUseCase.dispose()
    }

}
