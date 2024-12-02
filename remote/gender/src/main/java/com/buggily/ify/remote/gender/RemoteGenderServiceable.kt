package com.buggily.ify.remote.gender

import com.buggily.core.remote.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RemoteGenderServiceable {

    @GET(GET_BY_NAME)
    suspend fun getByName(
        @Query(NAME)
        name: String
    ): ApiResult<RemoteGender, RemoteGender.Error>

    private companion object {
        private const val GET_BY_NAME = "/"
        private const val NAME = "name"
    }
}
