package com.buggily.ify.remote.age

import com.buggily.core.remote.ApiResult

interface RemoteAgeSourceable {
    suspend fun getByName(name: String): ApiResult<RemoteAge, RemoteAge.Error>
}
