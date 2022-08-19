package com.buggily.ify.data.rest.nationality

import retrofit2.http.GET
import retrofit2.http.Query

interface NationalityServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String,
    ): NationalityRest

    private companion object {
        private const val GET = "https://api.nationalize.io"
        private const val GET_NAME = "name"
    }
}
