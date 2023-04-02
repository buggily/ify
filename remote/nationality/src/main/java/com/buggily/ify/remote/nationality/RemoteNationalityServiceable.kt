package com.buggily.ify.remote.nationality

import com.buggily.core.remote.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteNationalityServiceable {

    @GET(GET_BY_NAME)
    suspend fun getByName(
        @Query(NAME)
        name: String,
    ): ApiResult<RemoteNationality, RemoteNationality.Error>

    private companion object {
        private const val GET_BY_NAME = "/"
        private const val NAME = "name"
    }
}
