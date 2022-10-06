package com.example.core.utils

import com.example.core.model.Result

abstract class UseCase<Input, Output> {
    abstract suspend fun call(params: Input): Result<Output>
}