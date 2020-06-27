package com.socialevoeding.usecases.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.socialevoeding.util_models.Result

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<T>() {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                response(Result.Success(result))
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                response(Result.Error(e))
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class Request<T> {
        private var onComplete: ((Result.Success<T>) -> Unit)? = null
        private var onError: ((Result.Error) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (Result.Success<T>) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Result.Error) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: Result.Success<T>) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: Result.Error) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}