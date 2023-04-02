package com.buggily.ify.remote.gender

class RemoteGenderSource(
    private val remoteGenderService: RemoteGenderServiceable,
) : RemoteGenderSourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteGenderService.getByName(
        name = name,
    )
}
