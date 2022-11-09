package com.buggily.ify.remote.age

import com.buggily.ify.core.model.Rest
import retrofit2.http.GET
import retrofit2.http.Query

interface AgeServiceable {

    @GET(GET)
    suspend fun get(
        @Query(GET_NAME) name: String,
    ): Rest<Age, Age.Error>

    private companion object {
        private const val GET = "/"
        private const val GET_NAME = "name"
    }
}
