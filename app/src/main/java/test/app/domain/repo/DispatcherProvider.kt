package test.app.domain.repo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {
    val MAIN : CoroutineDispatcher
    val IO: CoroutineDispatcher
}

class AppDispatcherProvider @Inject constructor(): DispatcherProvider {
    override val MAIN: CoroutineDispatcher
        get() = Dispatchers.Main
    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

}