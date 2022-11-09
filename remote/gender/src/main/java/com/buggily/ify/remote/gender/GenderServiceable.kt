package com.buggily.ify.remote.gender

import com.buggily.ify.core.model.Rest
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String
    ): Rest<Gender, Gender.Error>

    private companion object {
        private const val GET = "/"
        private const val GET_NAME = "name"
    }
}
