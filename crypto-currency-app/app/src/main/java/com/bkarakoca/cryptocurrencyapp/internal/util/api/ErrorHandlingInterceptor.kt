package com.bkarakoca.cryptocurrencyapp.internal.util.api

import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.bkarakoca.cryptocurrencyapp.internal.util.NetworkStateHolder
import java.io.IOException
import java.net.SocketTimeoutException
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException

class ErrorHandlingInterceptor(
    private val networkStateHolder: NetworkStateHolder,
) : Interceptor {

    @Throws(IOException::class, Failure::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStateHolder.isConnected) {
            throw Failure.NoConnectivityError
        }

        val response = try {
            chain.proceed(chain.request())
        } catch (e: Exception) { // https://github.com/square/okhttp/issues/4380
            throw when (e) {
                is HttpException -> Failure.ApiError(e.code(), e.message())
                is SocketTimeoutException -> Failure.TimeOutError(e.message)
                else -> IOException(e)
            }
        }

        if (response.isSuccessful) {
            if (response.body == null) {
                throw Failure.EmptyResponse
            }
            return response
        } else {
            val responseJson = response.body?.string()
                ?: throw Failure.ApiError(
                    code = response.code,
                    message = response.message
                )

            throw Failure.ApiError(
                code = 1,
                message = "" // TODO :
            )
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}
