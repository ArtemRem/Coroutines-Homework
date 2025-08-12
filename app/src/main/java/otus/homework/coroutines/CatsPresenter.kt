package otus.homework.coroutines

import android.content.Context
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.launchimport otus.homework.coroutines.utils.CrashMonitor
import java.net.SocketTimeoutException

class CatsPresenter(
    private val catsService: CatsService,
    private val catsImagesService: CatsImageService,
    private val presenterScope = CoroutineScope(Dispatchers.Main + CoroutineName("CatsCoroutine"))
) {

    private var _catsView: ICatsView? = null
    private var job: Job? = null

    fun onInitComplete() {
        try {
        job = presenterScope.launch {
              val fact = async { catsService.getCatFact() }.await()
              val image = async { catsImageService.getCatImage() }.await()
              _catsView?.populate(catFact, catImage)
            }
        } catch (ex: Exception) {
                if (ex is java.net.SocketTimeoutException) {
                    _catsView?.onError("Unable to get response from server")
                }
                CrashMonitor.trackWarning(ex.message!!)
                 }
    }

    fun onStop() {
        job?.cancel()
    }
        

    fun attachView(catsView: ICatsView) {
        _catsView = catsView
    }

    fun detachView() {
        _catsView = null
    }
}
