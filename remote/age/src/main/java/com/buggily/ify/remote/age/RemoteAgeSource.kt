package com.buggily.ify.remote.age

class RemoteAgeSource(
    private val remoteAgeService: RemoteAgeServiceable,
) : RemoteAgeSourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteAgeService.getByName(
        name = name,
    )
}
