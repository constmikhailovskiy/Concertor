package app.concertor

import app.concertor.coroutines.Coroutines
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

abstract class UseCase<out Type, in Params> {

    private var activeJob: Deferred<Type>? = null

    abstract suspend fun run(params: Params): Type

    suspend fun get(params: Params): Type {
        activeJob = async(Coroutines.io()) { run(params) }
        return activeJob!!.await()
    }

    fun dispose() {
        activeJob?.cancel()
    }
}
