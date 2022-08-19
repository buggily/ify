package com.buggily.ify.data.rest.gender

import retrofit2.http.GET
import retrofit2.http.Query

interface GenderServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String
    ): GenderRest

    private companion object {
        private const val GET = "/"
        private const val GET_NAME = "name"
    }
}
