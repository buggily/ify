package com.buggily.ify.remote.nationality

import com.buggily.ify.core.model.Rest
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalityServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String,
    ): Rest<Nationality, Nationality.Error>

    private companion object {
        private const val GET = "/"
        private const val GET_NAME = "name"
    }
}
