package top.qiyuey.demo.reactive

import reactor.core.publisher.Mono

/**
 * @author yuchuan
 */

fun hello(): Mono<String> {
    return Mono.just("Hello")
}

fun world(): Mono<String> {
    return Mono.just("world")
}

fun merge(string1: Mono<String>, string2: Mono<String>): Mono<String> {
    return Mono.zip(string1, string2).map {
        "${it.t1}, ${it.t2}"
    }
}

fun merge(string1: Mono<String>, string2: String): Mono<String> {
    return string1.flatMap { Mono.just("$it, $string2") }
}

fun sync() {
    val hello = hello()
    val world = "world"
    println(merge(hello, world).block())
}

fun async() {
    val hello = hello()
    val world = world()
    println(merge(hello, world).block())
}

fun main() {
    // 完全两种实现，不具备可组合性
    sync()
    async()
}