package app.concertor.injection.components

import app.concertor.ConcertorApp
import app.concertor.injection.NetworkModule
import app.concertor.injection.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface AppComponent {

    fun inject(app: ConcertorApp)
}
