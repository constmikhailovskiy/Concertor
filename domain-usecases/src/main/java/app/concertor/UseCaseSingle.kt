package app.concertor

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver

@Suppress("unused")
abstract class UseCaseSingle<T> {

    private var disposable = CompositeDisposable()

    /**
     * Builds an [io.reactivex.Single] which will be used when executing the current [UseCaseSingle].
     */
    protected abstract fun buildUseCaseSingle(): Single<T>

    fun get(): Single<T> {
        return this.buildUseCaseSingle()
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
    }

    fun execute(observer: DisposableSingleObserver<T>) {
        disposable.add(get()
                .subscribeWith(observer))
    }

    /**
     * Unsubscribes from current [Disposables].
     */
    fun dispose() {
        disposable.clear()
    }
}
