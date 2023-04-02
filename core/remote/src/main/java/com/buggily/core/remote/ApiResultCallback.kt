package com.buggily.core.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class ApiResultCallback<Body : Any, FallbackBody : Any>(
    private val call: ApiResultCall<Body, FallbackBody>,
    private val callback: Callback<ApiResult<Body, FallbackBody>>,
    private val converter: Converter<ResponseBody, FallbackBody>,
) : Callback<Body> {

    override fun onResponse(
        call: Call<Body>,
        response: Response<Body>,
    ) = if (response.isSuccessful) {
        onSuccess(response)
    } else {
        onFailure(response)
    }

    private fun onSuccess(response: Response<Body>) = try {
        ApiResult.Response(
            body = checkNotNull(response.body()),
            code = HttpCode.get(response.code()),
        )
    } catch (e: IllegalStateException) {
        ApiResult.Failure.Else(e)
    }.let { onResponse(it) }

    private fun onFailure(response: Response<Body>) = try {
        val responseBody: ResponseBody = checkNotNull(response.errorBody())
        val fallbackBody: FallbackBody = checkNotNull(converter.convert(responseBody))

        ApiResult.Failure.Api(
            fallbackBody = fallbackBody,
            code = HttpCode.get(response.code()),
        )
    } catch (e: Exception) {
        ApiResult.Failure.Else(e)
    }.let { onResponse(it) }

    override fun onFailure(
        call: Call<Body>,
        t: Throwable,
    ) = when (t) {
        is IOException -> ApiResult.Failure.Network(t)
        else -> ApiResult.Failure.Else(t)
    }.let { onResponse(it) }

    private fun onResponse(
        result: ApiResult<Body, FallbackBody>,
    ) = callback.onResponse(
        call,
        Response.success(result),
    )
}
