package com.socialevoeding.bap.usecases.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class ForeGroundUseCase<T>(private val errorMapper: ErrorMapper) {

    private var parentJob: Job = Job()
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnForeground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = UseCase.Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(foregroundContext) {
                    executeOnForeground()
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                response(errorMapper.mapToDomainErrorException(e))
            }
        }
    }


    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}