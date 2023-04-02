package com.buggily.core.remote

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

class ApiResultCall<Body : Any, FallbackBody : Any>(
    private val call: Call<Body>,
    private val converter: Converter<ResponseBody, FallbackBody>,
) : Call<ApiResult<Body, FallbackBody>> {

    override fun enqueue(
        callback: Callback<ApiResult<Body, FallbackBody>>,
    ) = ApiResultCallback(
        call = this,
        callback = callback,
        converter = converter,
    ).let { call.enqueue(it) }

    override fun clone(): Call<ApiResult<Body, FallbackBody>> = ApiResultCall(
        call = call.clone(),
        converter = converter,
    )

    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()

    override fun execute(): Response<ApiResult<Body, FallbackBody>> = throw NotImplementedError()
    override fun cancel() = call.cancel()

    override fun isExecuted(): Boolean = call.isExecuted
    override fun isCanceled(): Boolean = call.isCanceled
}
