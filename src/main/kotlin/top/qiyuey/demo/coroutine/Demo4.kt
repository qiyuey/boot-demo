package top.qiyuey.demo.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * @author yuchuan
 */
suspend fun hello(): String {
    delay(1000)
    return "Hello"
}

suspend fun world(): String {
    delay(1000)
    return "world"
}

suspend fun merge(string1: String, string2: String): String {
    delay(1000)
    return "$string1, $string2"
}

// 同步非阻塞
suspend fun sync() {
    val hello = hello()
    val world = "world"
    val helloWorld = merge(hello, world)
    println(helloWorld)
}

// 异步非阻塞
suspend fun async() = coroutineScope {
    val hello = async { hello() }
    val world = async { world() }
    val helloWorld = merge(hello.await(), world.await())
    println(helloWorld)
}

suspend fun main() = coroutineScope {
    sync()
    async()
}