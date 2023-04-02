package com.buggily.ify.remote.nationality

import com.buggily.core.remote.ApiResult

interface RemoteNationalitySourceable {
    suspend fun getByName(name: String): ApiResult<RemoteNationality, RemoteNationality.Error>
}
