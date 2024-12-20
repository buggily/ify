package com.buggily.ify.remote.gender

internal class RemoteGenderSource(
    private val remoteGenderService: RemoteGenderServiceable,
) : RemoteGenderSourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteGenderService.getByName(
        name = name,
    )
}
