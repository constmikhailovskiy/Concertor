package app.concertor

import io.reactivex.observers.DisposableCompletableObserver

open class DefaultCompletableObserver : DisposableCompletableObserver() {
    override fun onComplete() {

    }

    override fun onError(e: Throwable) {

    }

}