package app.concertor.sections.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.concertor.interactor.events.GetEventsUseCase
import app.concertor.models.Event
import app.concertor.models.Location
import app.concertor.sections.SynchronousCoroutines
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.willReturn
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @JvmField
    @Rule
    val rules = SynchronousCoroutines()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var actionsProcessor: HomeProcessor
    private val getEventsUseCase = mock<GetEventsUseCase>()
    private val observer: Observer<HomeViewState> = mock()

    @Before
    fun setUp() {
        actionsProcessor = HomeProcessor(getEventsUseCase)
        viewModel = HomeViewModel(actionsProcessor)
    }

    @Test
    fun testGetEventsSuccess() {
        val result = listOf(Event(
                0, "", "", "", "",
                Location(0.0, 0.0, ""), Date(),
                "p", "Mike", false, false
        ))
        given { runBlocking { getEventsUseCase.get("test") } }.willReturn(result)

        viewModel.states.observeForever(observer)

        viewModel.performIntent(HomeIntent.LoadEventsIntent("test"))

        verify(observer).onChanged(HomeViewState.Idle)
        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer).onChanged(HomeViewState.Success(result))
    }

    @Test
    fun testGetEventsFailure() {
        val failure = Throwable("Random failure")
        given { runBlocking { getEventsUseCase.get("test") } }.willThrow(failure)

        viewModel.states.observeForever(observer)

        viewModel.performIntent(HomeIntent.LoadEventsIntent("test"))

        verify(observer).onChanged(HomeViewState.Idle)
        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer).onChanged(HomeViewState.Failed(failure))
    }
}