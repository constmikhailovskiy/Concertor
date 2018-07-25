package app.concertor.sections.home

import app.concertor.models.Event
import app.concertor.mvi.BaseResult
import app.concertor.mvi.model.TaskStatus

sealed class HomeResult : BaseResult {

    class LoadEventsForArtistTask(
            val status: TaskStatus,
            val events: List<Event>? = null,
            val error: Throwable? = null
    ) : HomeResult() {

        companion object {
            internal fun success(events: List<Event>): LoadEventsForArtistTask {
                return LoadEventsForArtistTask(TaskStatus.SUCCESS, events = events)
            }

            internal fun failure(error: Throwable): LoadEventsForArtistTask {
                return LoadEventsForArtistTask(TaskStatus.FAILURE, error = error)
            }

            internal fun loading(): LoadEventsForArtistTask {
                return LoadEventsForArtistTask(TaskStatus.IN_FLIGHT)
            }
        }
    }
}