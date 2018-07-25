package app.concertor.sections.home

import app.concertor.interactor.events.GetEventsUseCase
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class HomeProcessor @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) {

    val actionProcessor: ObservableTransformer<HomeAction, HomeResult> = ObservableTransformer {
            it.publish {
                it.ofType(HomeAction.LoadEventsForArtist::class.java)
                        .compose(eventsProcessor)
            }
    }

    private val eventsProcessor: ObservableTransformer<HomeAction.LoadEventsForArtist, HomeResult> = ObservableTransformer {
        it.switchMap {
            getEventsUseCase.artistName = it.artistName
            return@switchMap getEventsUseCase.get()
                    .map { HomeResult.LoadEventsForArtistTask.success(it) }
                    .onErrorReturn { HomeResult.LoadEventsForArtistTask.failure(it) }
                    .toObservable()
                    .startWith(HomeResult.LoadEventsForArtistTask.loading())
        }
    }

}
