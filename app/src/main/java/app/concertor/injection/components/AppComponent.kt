package app.concertor.injection.components

import app.concertor.ConcertorApp
import app.concertor.DomainModule
import app.concertor.injection.*
import app.concertor.injection.modules.AppModule
import app.concertor.sections.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (DatabaseModule::class),
    (RepositoriesModule::class), (DomainModule::class), (LocalStorageModule::class),
    (RemoteStorageModule::class)])
interface AppComponent {

    fun inject(app: ConcertorApp)

    fun inject(homeActivity: HomeActivity)
}
