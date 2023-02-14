package com.buggily.ify.data.age.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.age.Age
import com.buggily.ify.remote.age.AgeServiceable

class AgeSource(
    private val service: AgeServiceable,
) : AgeSourceable {

    override suspend fun get(
        name: String,
    ) = when (val rest = service.get(name)) {
        is Rest.Success -> with(rest) {
            val body: Age = with(body) {
                Age(
                    name = name,
                    age = age,
                    count = count,
                )
            }

            Rest.Success(
                code = code,
                body = body,
            )
        }
        is Rest.Error.Api -> with(rest) {
            val errorBody: Age.Error = with(errorBody) {
                Age.Error(
                    error = error,
                )
            }

            Rest.Error.Api(
                code = code,
                errorBody = errorBody,
            )
        }
        is Rest.Error.Network -> with(rest) {
            Rest.Error.Network(
                exception = exception,
            )
        }
        is Rest.Error.Else -> with(rest) {
            Rest.Error.Else(
                throwable = throwable,
            )
        }
    }
}
