package com.buggily.core.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class ApiResultAdapter<Body : Any, FallbackBody : Any>(
    private val responseType: Type,
    private val converter: Converter<ResponseBody, FallbackBody>,
) : CallAdapter<Body, Call<ApiResult<Body, FallbackBody>>> {

    override fun adapt(
        call: Call<Body>,
    ): Call<ApiResult<Body, FallbackBody>> = ApiResultCall(
        call = call,
        converter = converter,
    )

    override fun responseType(): Type = responseType
}
