package app.concertor.injection.modules

import android.content.Context
import android.content.res.Resources
import app.concertor.ConcertorApp
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class AppModule(private val application: ConcertorApp) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    @Provides
    fun provideResources(appContext: Context): Resources = appContext.resources

    @Provides
    fun provideCalendar(): Calendar = Calendar.getInstance()
}
