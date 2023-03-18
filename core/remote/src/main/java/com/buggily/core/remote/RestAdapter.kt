package com.buggily.core.remote

import com.buggily.ify.core.model.Rest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class RestAdapter<Body : Any, ErrorBody : Any>(
    private val responseType: Type,
    private val converter: Converter<ResponseBody, ErrorBody>,
) : CallAdapter<Body, Call<Rest<Body, ErrorBody>>> {

    override fun adapt(
        call: Call<Body>,
    ): Call<Rest<Body, ErrorBody>> = RestCall(
        call = call,
        converter = converter,
    )

    override fun responseType(): Type = responseType
}
