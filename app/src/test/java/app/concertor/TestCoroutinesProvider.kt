package app.concertor

import app.concertor.coroutines.CoroutinesProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestCoroutinesProvider : CoroutinesProvider {

    override val Main: CoroutineContext
        get() = Unconfined

    override val IO: CoroutineContext
        get() = Unconfined

}