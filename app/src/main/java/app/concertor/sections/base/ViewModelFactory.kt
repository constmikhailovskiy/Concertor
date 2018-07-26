package app.concertor.sections.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.concertor.sections.home.HomeProcessor
import app.concertor.sections.home.HomeViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
        private val eventsProcessor: HomeProcessor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(eventsProcessor) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
