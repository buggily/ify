package com.buggily.ify.data.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RestCallAdapterFactory<Body : Any, ErrorBody : Any> : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)
        val isCall: Boolean = rawReturnType == Call::class.java
        if (!isCall) return null

        val returnIndex = 0
        val returnParameterizedType: ParameterizedType = returnType as? ParameterizedType ?: return null
        val responseType: Type = getParameterUpperBound(returnIndex, returnParameterizedType)

        val rawResponseType: Class<*> = getRawType(responseType)
        val isRest: Boolean = rawResponseType == Rest::class.java
        if (!isRest) return null

        var responseIndex = 0
        val responseParameterizedType: ParameterizedType = responseType as? ParameterizedType ?: return null
        val bodyType: Type = getParameterUpperBound(responseIndex++, responseParameterizedType)
        val errorBodyType: Type = getParameterUpperBound(responseIndex, responseParameterizedType)

        val converter: Converter<ResponseBody, ErrorBody> = retrofit.nextResponseBodyConverter(
            null,
            errorBodyType,
            annotations
        )

        return RestAdapter<Body, ErrorBody>(
            bodyType = bodyType,
            converter = converter,
        )
    }
}
