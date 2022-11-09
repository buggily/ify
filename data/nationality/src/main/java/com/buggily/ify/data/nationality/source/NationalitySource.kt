package com.buggily.ify.data.nationality.source

import com.buggily.ify.core.model.Rest
import com.buggily.ify.core.model.nationality.Nationality
import com.buggily.ify.remote.nationality.NationalityServiceable

class NationalitySource(
    private val service: NationalityServiceable,
) : NationalitySourceable {

    override suspend fun get(name: String) = when (val rest = service.get(name)) {
        is Rest.Success -> with(rest) {
            val body: Nationality = with(body) {
                val countries: List<Nationality.Country> = countries.map {
                    Nationality.Country(
                        locale = it.locale,
                        probability = it.probability,
                    )
                }

                Nationality(
                    name = name,
                    countries = countries,
                )
            }

            Rest.Success(
                code = code,
                body = body,
            )
        }
        is Rest.Error.Api -> with(rest) {
            val errorBody: Nationality.Error = with(errorBody) {
                Nationality.Error(
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
