package com.buggily.core.remote

import com.buggily.ify.core.model.Rest
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

class RestCall<Body : Any, ErrorBody : Any>(
    private val call: Call<Body>,
    private val converter: Converter<ResponseBody, ErrorBody>,
) : Call<Rest<Body, ErrorBody>> {

    override fun enqueue(callback: Callback<Rest<Body, ErrorBody>>) = RestCallback(
        call = this,
        callback = callback,
        converter = converter,
    ).let { call.enqueue(it) }

    override fun clone(): Call<Rest<Body, ErrorBody>> = RestCall(
        call = call.clone(),
        converter = converter,
    )

    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()

    override fun execute(): Response<Rest<Body, ErrorBody>> = throw NotImplementedError()
    override fun cancel() = call.cancel()

    override fun isExecuted(): Boolean = call.isExecuted
    override fun isCanceled(): Boolean = call.isCanceled
}
