package com.buggily.ify.remote.nationality

class RemoteNationalitySource(
    private val remoteNationalityService: RemoteNationalityServiceable,
) : RemoteNationalitySourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteNationalityService.getByName(
        name = name,
    )
}
