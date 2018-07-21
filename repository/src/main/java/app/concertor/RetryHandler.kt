package app.concertor

import kotlinx.coroutines.experimental.delay
import java.io.IOException

class RetryHandler {

    suspend fun <T> retryIO(retryCount: Int = 3, action: suspend () -> T): T {
        var attemptsCount = 0
        var currentDelay = 1000L;
        while (true) {
            try {
                return action()
            } catch (e: IOException) {
                if (attemptsCount >= retryCount) {
                    throw e
                } else {
                    e.printStackTrace()
                    attemptsCount++
                }
            }
            delay(currentDelay)
            currentDelay = (currentDelay * 2).coerceAtMost(60_000L)
        }
    }
}