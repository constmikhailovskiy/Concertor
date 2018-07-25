package app.concertor

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {

    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

}