package com.buggily.ify.remote.age

internal class RemoteAgeSource(
    private val remoteAgeService: RemoteAgeServiceable,
) : RemoteAgeSourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteAgeService.getByName(
        name = name,
    )
}
