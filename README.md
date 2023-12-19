# spring-aop-around-repro

Repository to reproduce https://github.com/spring-projects/spring-framework/issues/31855

## Requirements

Java 17

## How to confirm

`3.2.0` uses Spring Boot 3.2.0.

```bash
cd 3.2.0
./gradlew bootRun
```

`3.2.0` uses Spring Boot 3.1.6.

```bash
cd 3.1.6
./gradlew bootRun
```

Then, access `localhost:8080` via browser.

## Stack trace

```
2023-12-19T21:49:03.098+09:00 ERROR 45157 --- [DefaultExecutor] a.w.r.e.AbstractErrorWebExceptionHandler : [16ab3937-1]  500 Server Error for HTTP GET "/"

java.lang.ClassCastException: class reactor.core.publisher.MonoOnErrorResume cannot be cast to class java.lang.CharSequence (reactor.core.publisher.MonoOnErrorResume is in unnamed module of loader 'app'; java.lang.CharSequence is in module java.base of loader 'bootstrap')
at reactor.core.publisher.FluxMap$MapSubscriber.onNext(FluxMap.java:106) ~[reactor-core-3.6.0.jar:3.6.0]
Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
Error has been observed at the following site(s):
*__checkpoint ⇢ Handler com.example.demo.Controller#endpoint(Continuation) [DispatcherHandler]
*__checkpoint ⇢ HTTP GET "/" [ExceptionHandlingWebHandler]
Original Stack Trace:
at reactor.core.publisher.FluxMap$MapSubscriber.onNext(FluxMap.java:106) ~[reactor-core-3.6.0.jar:3.6.0]
at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onNext(FluxOnErrorResume.java:79) ~[reactor-core-3.6.0.jar:3.6.0]
at reactor.core.publisher.FluxFilter$FilterSubscriber.onNext(FluxFilter.java:113) ~[reactor-core-3.6.0.jar:3.6.0]
at reactor.core.publisher.MonoCreate$DefaultMonoSink.success(MonoCreate.java:176) ~[reactor-core-3.6.0.jar:3.6.0]
at kotlinx.coroutines.reactor.MonoCoroutine.onCompleted(Mono.kt:103) ~[kotlinx-coroutines-reactor-1.7.3.jar:na]
at kotlinx.coroutines.AbstractCoroutine.onCompletionInternal(AbstractCoroutine.kt:93) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.JobSupport.finalizeFinishingState(JobSupport.kt:237) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.JobSupport.tryMakeCompletingSlowPath(JobSupport.kt:910) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.JobSupport.tryMakeCompleting(JobSupport.kt:867) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.JobSupport.makeCompletingOnce$kotlinx_coroutines_core(JobSupport.kt:832) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.AbstractCoroutine.resumeWith(AbstractCoroutine.kt:100) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:46) ~[kotlin-stdlib-1.9.20.jar:1.9.255-SNAPSHOT]
at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:235) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.DispatchedTaskKt.resumeUnconfined(DispatchedTask.kt:191) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:163) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:474) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:508) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:497) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.CancellableContinuationImpl.resumeUndispatched(CancellableContinuationImpl.kt:595) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.EventLoopImplBase$DelayedResumeTask.run(EventLoop.common.kt:493) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:280) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at kotlinx.coroutines.DefaultExecutor.run(DefaultExecutor.kt:109) ~[kotlinx-coroutines-core-jvm-1.7.3.jar:na]
at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
```
