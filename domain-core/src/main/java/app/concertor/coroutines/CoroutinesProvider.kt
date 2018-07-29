package app.concertor.coroutines

import kotlin.coroutines.experimental.CoroutineContext

interface CoroutinesProvider {

    val Main: CoroutineContext

    val IO: CoroutineContext
}
