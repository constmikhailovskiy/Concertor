package app.concertor.utils.rx

import app.concertor.SchedulerProvider
import io.reactivex.Scheduler

object PresentationSchedulerProvider : SchedulerProvider {
    override fun mainThread(): Scheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun io(): Scheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun computation(): Scheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newThread(): Scheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trampoline(): Scheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}