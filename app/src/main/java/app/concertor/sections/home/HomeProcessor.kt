package app.concertor.sections.home

import app.concertor.interactor.events.GetEventsUseCase
import app.concertor.mvi.BaseActionProcessor
import io.reactivex.ObservableTransformer
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.broadcast
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.channels.sendBlocking
import javax.inject.Inject

class HomeProcessor constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val homeAnalytics: HomeAnalytics,
    private val coordinator: Coordinator
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
                is HomeAction.Quit -> {
                    homeAnalytics.sendQuitEvent()
                    coordinator.quit()
                }
            }
        }.broadcast()
    }

    override fun dispose() {
        getEventsUseCase.dispose()
    }
}

interface HomeAnalytics {

    fun sendQuitEvent()
}

class Coordinator {

    fun quit() {}
}
