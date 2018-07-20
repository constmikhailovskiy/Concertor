package app.concertor

import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutinesContextProvider {

    open val Main: CoroutineContext by lazy { throw IllegalStateException("Cannot use Main contest!") }

    open val IO: CoroutineContext by lazy { CommonPool }
}
