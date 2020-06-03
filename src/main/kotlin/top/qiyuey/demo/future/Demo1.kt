package top.qiyuey.demo.future

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
    val executors = Executors.newSingleThreadExecutor()
    val future = executors.submit(Callable { helloWorld() })
    println(future.get())
    executors.shutdown()
}