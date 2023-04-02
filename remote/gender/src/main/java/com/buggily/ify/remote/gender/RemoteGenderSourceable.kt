package com.buggily.ify.remote.gender

import com.buggily.core.remote.ApiResult

interface RemoteGenderSourceable {
    suspend fun getByName(name: String): ApiResult<RemoteGender, RemoteGender.Error>
}
