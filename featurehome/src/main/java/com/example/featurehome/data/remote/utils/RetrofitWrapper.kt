package com.example.featurehome.data.remote.utils

import com.example.core.model.GenericErrorException
import com.example.core.model.NetworkErrorException
import com.example.core.model.Result
import com.example.core.model.ServerErrorException
import java.io.IOException
import retrofit2.HttpException

suspend fun <T> retrofitWrapper(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (ioException: IOException) {
        Result.Error(NetworkErrorException())
    } catch (httpException: HttpException) {
        Result.Error(ServerErrorException())
    } catch (throwable: Throwable) {
        Result.Error(GenericErrorException())
    }
}