package app.concertor.utils.rx

import app.concertor.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PresentationSchedulerProvider : SchedulerProvider {
    override fun mainThread() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()

    override fun computation() = Schedulers.computation()

    override fun newThread() = Schedulers.newThread()

    override fun trampoline() = Schedulers.trampoline()

}