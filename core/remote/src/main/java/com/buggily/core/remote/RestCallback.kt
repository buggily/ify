package com.buggily.core.remote

import com.buggily.ify.core.model.Rest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class RestCallback<Body : Any, ErrorBody : Any>(
    private val call: RestCall<Body, ErrorBody>,
    private val callback: Callback<Rest<Body, ErrorBody>>,
    private val converter: Converter<ResponseBody, ErrorBody>,
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
        val body: Body = checkNotNull(response.body())

        Rest.Success(
            body = body,
            code = Rest.Code.get(response.code()),
        )
    } catch (e: IllegalStateException) {
        Rest.Error.Else(e)
    }.let { onResponse(it) }

    private fun onFailure(response: Response<Body>) = try {
        val responseBody: ResponseBody = checkNotNull(response.errorBody())
        val errorBody: ErrorBody = checkNotNull(converter.convert(responseBody))

        Rest.Error.Api(
            errorBody = errorBody,
            code = Rest.Code.get(response.code()),
        )
    } catch (e: Exception) {
        Rest.Error.Else(e)
    }.let { onResponse(it) }

    override fun onFailure(
        call: Call<Body>,
        t: Throwable,
    ) = when (t) {
        is IOException -> Rest.Error.Network(t)
        else -> Rest.Error.Else(t)
    }.let { onResponse(it) }

    private fun onResponse(
        rest: Rest<Body, ErrorBody>,
    ) = callback.onResponse(
        call,
        Response.success(rest),
    )
}
