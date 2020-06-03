package top.qiyuey.demo.callback

import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.MoreExecutors
import java.util.concurrent.Callable
import java.util.concurrent.Executors

/**
 * @author yuchuan
 */
fun helloWorld(): String {
    Thread.sleep(1000)
    return "Hello, world"
}

fun main() {
    val executors = Executors.newFixedThreadPool(2)
    val listenableFuture = MoreExecutors.listeningDecorator(executors).submit(Callable { helloWorld() })
    Futures.addCallback(listenableFuture, object : FutureCallback<String> {
        override fun onSuccess(result: String?) {
            println(result)
        }

        override fun onFailure(t: Throwable) {
            println(t.message)
        }
    }, executors)
    // 防止主线程退出
    listenableFuture.get()
    executors.shutdown()
}