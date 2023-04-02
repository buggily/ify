package com.buggily.ify.remote.age

import com.buggily.core.remote.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAgeServiceable {

    @GET(GET_BY_NAME)
    suspend fun getByName(
        @Query(NAME)
        name: String,
    ): ApiResult<RemoteAge, RemoteAge.Error>

    private companion object {
        private const val GET_BY_NAME = "/"
        private const val NAME = "name"
    }
}
