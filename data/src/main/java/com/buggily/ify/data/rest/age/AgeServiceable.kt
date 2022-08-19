package com.buggily.ify.data.rest.age

import retrofit2.http.GET
import retrofit2.http.Query

interface AgeServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String,
    ): AgeRest

    private companion object {
        private const val GET = "/"
        private const val GET_NAME = "name"
    }
}