package app.concertor

import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCoroutinesProvider(): CoroutinesContextProvider {
        return CoroutinesContextProvider()
    }
}