package com.buggily.core.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? = try {
        val rawReturnType: Class<*> = getRawType(returnType)
        require(rawReturnType == Call::class.java)

        val returnIndex = 0
        val returnParameterizedType = requireNotNull(returnType as? ParameterizedType)

        val responseType: Type = getParameterUpperBound(
            returnIndex,
            returnParameterizedType
        )

        val rawResponseType: Class<*> = getRawType(responseType)
        require(rawResponseType == ApiResult::class.java)

        var responseIndex = 0
        val responseParameterizedType = requireNotNull(responseType as? ParameterizedType)

        val responseBodyType: Type = getParameterUpperBound(
            responseIndex++,
            responseParameterizedType
        )

        val responseFallbackBodyType: Type = getParameterUpperBound(
            responseIndex,
            responseParameterizedType
        )

        val converter: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(
            null,
            responseFallbackBodyType,
            annotations
        )

        ApiResultAdapter<Any, Any>(
            responseType = responseBodyType,
            converter = converter,
        )
    } catch (_: IllegalArgumentException) {
        null
    }
}
