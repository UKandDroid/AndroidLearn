package test.app

import android.app.Activity
import dagger.android.AndroidInjection
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.reflect.KClass

class ActivityInjectionRule(
    private vararg val injectors: Injector<out Activity>
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)

        mockkStatic(AndroidInjection::class)
        every{AndroidInjection.inject(any() as Activity)} answers {inject(firstArg())}
    }

    private fun inject(activity: Activity) {
        injectors
            .find{ it.canInject(activity)}
            ?.inject(activity)
    }

    override fun finished(description: Description?) {
        unmockkStatic(AndroidInjection::class)

        super.finished(description)
    }
}

class Injector<T : Activity>(
    private val clazz: KClass<T>,
    private val injectorFunction: T.() -> Unit,
) {

    fun canInject(activity: Activity): Boolean = clazz.isInstance(activity)

    @Suppress("UNCHECKED_CAST")
    fun inject(fragment: Activity) {
        if (canInject(fragment)) {
            (fragment as T).injectorFunction()
        }
    }
}