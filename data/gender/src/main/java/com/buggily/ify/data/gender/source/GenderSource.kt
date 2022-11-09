package com.buggily.ify.data.gender.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.gender.Gender
import com.buggily.ify.remote.gender.GenderServiceable
import com.buggily.ify.remote.gender.Gender.Gender as GenderGender

class GenderSource(
    private val service: GenderServiceable,
) : GenderSourceable {

    override suspend fun get(name: String) = when (val rest = service.get(name)) {
        is Rest.Success -> with(rest) {
            val body: Gender = with(body) {
                val gender: Gender.Gender? = when (gender) {
                    GenderGender.Male -> Gender.Gender.Male
                    GenderGender.Female -> Gender.Gender.Female
                    else -> null
                }

                Gender(
                    name = name,
                    gender = gender,
                    probability = probability,
                    count = count,
                )
            }

            Rest.Success(
                code = code,
                body = body,
            )
        }
        is Rest.Error.Api -> with(rest) {
            val errorBody: Gender.Error = with(errorBody) {
                Gender.Error(
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
