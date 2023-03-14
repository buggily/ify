package com.buggily.core.remote

import com.buggily.ify.core.model.Rest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RestCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *> {
        val rawReturnType: Class<*> = getRawType(returnType)
        require(rawReturnType == Call::class.java)

        val returnIndex = 0
        val returnParameterizedType = requireNotNull(returnType as? ParameterizedType)
        val responseType: Type = getParameterUpperBound(returnIndex, returnParameterizedType)

        val rawResponseType: Class<*> = getRawType(responseType)
        require(rawResponseType == Rest::class.java)

        var responseIndex = 0
        val responseParameterizedType = requireNotNull(responseType as? ParameterizedType)
        val bodyType: Type = getParameterUpperBound(responseIndex++, responseParameterizedType)
        val errorBodyType: Type = getParameterUpperBound(responseIndex, responseParameterizedType)

        val converter: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(
            null,
            errorBodyType,
            annotations
        )

        return RestAdapter<Any, Any>(
            bodyType = bodyType,
            converter = converter,
        )
    }
}
