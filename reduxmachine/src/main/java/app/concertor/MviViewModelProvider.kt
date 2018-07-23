package app.concertor

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

@Suppress("UNCHECKED_CAST")
class MviViewModelProvider {

    inline fun <reified VM : ViewModel> viewModelProvider(
            fragment: Fragment,
            crossinline provider: () -> VM
    ) = lazy {
        ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
            override fun <VM1 : ViewModel> create(aClass: Class<VM1>) = provider() as VM1
        }).get(VM::class.java)
    }

    inline fun <reified VM : ViewModel> viewModelProvider(
            activity: AppCompatActivity,
            crossinline provider: () -> VM
    ) = lazy {
        ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <VM1 : ViewModel> create(aClass: Class<VM1>) = provider() as VM1
        }).get(VM::class.java)
    }

}
