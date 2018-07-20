package app.concertor

import android.app.Application
import app.concertor.injection.components.AppComponent
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class ConcertorApp : Application() {

    companion object {
        lateinit var application: ConcertorApp
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        application = this



        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Stetho.initializeWithDefaults(this)
    }
}
