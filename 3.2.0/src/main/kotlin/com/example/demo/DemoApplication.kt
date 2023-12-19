package com.example.demo

import kotlinx.coroutines.delay
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
class Controller {
    @GetMapping
    @Annotation
    suspend fun endpoint(): String = "Hello world"
}

annotation class Annotation

@Component
@Aspect
class Around {
    @Around("@annotation(Annotation)")
    fun around(joinPoint: ProceedingJoinPoint): Any? = joinPoint.runCoroutine {
        println("Before")
        delay(1)
        val returnValue = joinPoint.proceedCoroutine(joinPoint.coroutineArgs)
        println("After")

        returnValue
    }
}

val ProceedingJoinPoint.coroutineContinuation: Continuation<Any?>
    get() = this.args.last() as Continuation<Any?>

val ProceedingJoinPoint.coroutineArgs: Array<Any?>
    get() = this.args.sliceArray(0 until this.args.size - 1)

suspend fun ProceedingJoinPoint.proceedCoroutine(
    args: Array<Any?> = this.coroutineArgs,
): Any? = suspendCoroutineUninterceptedOrReturn { continuation ->
    this.proceed(args + continuation)
}

fun ProceedingJoinPoint.runCoroutine(
    block: suspend () -> Any?,
): Any? =
    block.startCoroutineUninterceptedOrReturn(this.coroutineContinuation)
