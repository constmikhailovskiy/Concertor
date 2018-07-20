package app.concertor

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutinesContextProvider() {

    override val Main: CoroutineContext
        get() = Unconfined

    override val IO: CoroutineContext
        get() = Unconfined
}
