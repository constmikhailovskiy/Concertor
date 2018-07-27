package app.concertor.sections.home

import app.concertor.interactor.events.GetEventsUseCase
import app.concertor.models.Event
import app.concertor.models.Location
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var actionsProcessor: HomeProcessor
    private val getEventsUseCase = mock<GetEventsUseCase>()

    @Before
    fun setUp() {
        actionsProcessor = HomeProcessor(getEventsUseCase)
        viewModel = HomeViewModel(actionsProcessor)
    }

    @Test
    fun testGetEventsSuccess() {
        val events = listOf(Event(id = 1L, name = "", type = "", uri = "uri", venue = "venue",
                artistName = "artist", location = Location(45.4, 53.4, "Paris"),
                date = Date(System.currentTimeMillis()), performanceName = "Test", planned = false,
                favoriteArtist = true))
        whenever(getEventsUseCase.get()).thenReturn(Single.just(events))

        val testObserver = viewModel.states().test()

        viewModel.performIntent(HomeIntent.LoadEventsIntent("Elvis"))

        testObserver.assertValueSequence(listOf(HomeViewState.Idle, HomeViewState.Loading,
                HomeViewState.Success(events)))

        verify(getEventsUseCase).artistName = "Elvis"
        verify(getEventsUseCase).get()
        verifyNoMoreInteractions(getEventsUseCase)
    }
}