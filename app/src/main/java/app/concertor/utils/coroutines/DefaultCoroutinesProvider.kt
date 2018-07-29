package app.concertor.utils.coroutines

import app.concertor.coroutines.CoroutinesProvider
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

class DefaultCoroutinesProvider : CoroutinesProvider {

    override val IO: CoroutineContext
        get() = CommonPool

    override val Main: CoroutineContext
        get() = UI
}
