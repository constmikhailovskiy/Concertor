package app.concertor.sections

import app.concertor.TestCoroutinesProvider
import app.concertor.coroutines.Coroutines
import org.junit.rules.ExternalResource

class SynchronousCoroutines : ExternalResource() {

    @Throws(Throwable::class)
    public override fun before() {
        Coroutines.setInstance(TestCoroutinesProvider())
    }
}