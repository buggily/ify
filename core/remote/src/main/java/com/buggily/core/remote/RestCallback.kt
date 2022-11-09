package com.buggily.core.remote

import com.buggily.ify.core.model.Rest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class RestCallback<Body : Any, ErrorBody : Any>(
    private val restCall: RestCall<Body, ErrorBody>,
    private val callback: Callback<Rest<Body, ErrorBody>>,
    private val converter: Converter<ResponseBody, ErrorBody>,
) : Callback<Body> {

    override fun onResponse(
        call: Call<Body>,
        response: Response<Body>,
    ) {
        val isSuccessful: Boolean = response.isSuccessful
        val isUnsuccessful: Boolean = !isSuccessful

        val body: Body? = response.body()
        val code: Rest.Code = Rest.Code.get(response.code())

        if (isUnsuccessful) {
            val errorBody: ErrorBody? = try {
                response.errorBody()?.let { converter.convert(it) }
            } catch (e: IOException) {
                null
            }

            if (errorBody == null) {
                Rest.Error.Else(
                    throwable = IOException(),
                ).let { onResponse(it) }

                return
            }

            Rest.Error.Api(
                errorBody = errorBody,
                code = code,
            ).let { onResponse(it) }

            return
        }

        if (body == null) {
            Rest.Error.Else(
                throwable = NullPointerException(),
            ).let { onResponse(it) }

            return
        }

        Rest.Success(
            code = code,
            body = body,
        ).let { onResponse(it) }
    }

    override fun onFailure(
        call: Call<Body>,
        t: Throwable,
    ) = when (t) {
        is IOException -> Rest.Error.Network(
            exception = t,
        )
        else -> Rest.Error.Else(
            throwable = t,
        )
    }.let { onResponse(it) }

    private fun onResponse(
        rest: Rest<Body, ErrorBody>,
    ) = callback.onResponse(
        restCall,
        Response.success(rest),
    )
}
