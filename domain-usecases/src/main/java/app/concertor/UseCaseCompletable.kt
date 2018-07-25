package app.concertor

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableCompletableObserver

@Suppress("unused")
abstract class UseCaseCompletable {

    var disposable = CompositeDisposable()

    /**
     * Builds an [io.reactivex.Completable] which will be used when executing the current [UseCaseCompletable].
     */
    protected abstract fun buildUseCaseCompletable(): Completable

    fun get(): Completable {
        return this.buildUseCaseCompletable()
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
    }

    fun execute(observer: DisposableCompletableObserver) {
        disposable.add(get()
                .subscribeWith(observer))
    }

    // TODO: think about mocking an interaction with this method
    inline fun executeWith(crossinline action: () -> Unit,
                           crossinline errorAction: (e: Throwable) -> Unit) {
        disposable.add(get().subscribeWith(object : DisposableCompletableObserver() {
            override fun onComplete() {
                action()
            }

            override fun onError(e: Throwable) {
                errorAction(e)
            }
        }))
    }

    /**
     * Unsubscribes from current [Disposables].
     */
    fun dispose() {
        disposable.clear()
    }
}
