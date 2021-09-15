package br.com.hunterbay.core

import kotlinx.coroutines.flow.Flow

abstract class UseCase {
    abstract class NoSource<ParamOne, Source> {
        abstract suspend fun execute(paramOne: ParamOne): Flow<Source>
        open suspend operator fun invoke(param: ParamOne)  = execute(param)
    }
}