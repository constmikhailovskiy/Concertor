package app.concertor.coroutines

import kotlin.coroutines.experimental.CoroutineContext

object Coroutines {

    private lateinit var instance: CoroutinesProvider

    fun setInstance(instance: CoroutinesProvider) {
        Coroutines.instance = instance
    }

    @JvmStatic
    fun io(): CoroutineContext = instance.IO

    @JvmStatic
    fun main(): CoroutineContext = instance.Main
}
