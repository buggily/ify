package com.buggily.ify.data.age

import com.buggily.core.remote.ApiResult
import com.buggily.core.remote.HttpCode
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.local.age.LocalAge
import com.buggily.ify.local.age.LocalAgeSourceable
import com.buggily.ify.remote.age.RemoteAge
import com.buggily.ify.remote.age.RemoteAgeSourceable
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class AgeRepositoryTest {

    private val remoteAgeSource: RemoteAgeSourceable = mockk()
    private val localAgeSource: LocalAgeSourceable = mockk()
    private lateinit var ageRepository: AgeRepository

    @get:Rule
    val rule = CoroutineTestRule

    @Before
    fun before() {
        clearAllMocks()

        ageRepository = AgeRepository(
            remoteAgeSource = remoteAgeSource,
            localAgeSource = localAgeSource,
        )

        coEvery { localAgeSource.insert(any()) } returns Unit
    }

    @Test
    fun `get by name should return local response when local source has name`() = runTest {
        val localAge = LocalAge(
            name = NAME,
            age = null,
            count = 0,
        )

        coEvery { localAgeSource.getByName(NAME) } returns flowOf(localAge)

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Response.Local(localAge.to()),
        )
    }

    @Test
    fun `get by name should return remote response when local source does not have name and remote source has name`() = runTest {
        val remoteAge = RemoteAge(
            name = NAME,
            age = null,
            count = 0,
        )

        coEvery { localAgeSource.getByName(NAME) } returns emptyFlow() andThen flowOf(remoteAge.toLocal())
        coEvery { remoteAgeSource.getByName(NAME) } returns ApiResult.Response(
            code = HttpCode.Default,
            body = remoteAge,
        )

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Response.Remote(remoteAge.toLocal().to()),
        )
    }

    @Test
    fun `get by name should return local failure when local source does not have name and remote source has name but insert fails`() = runTest {
        val remoteAge = RemoteAge(
            name = NAME,
            age = null,
            count = 0,
        )

        coEvery { localAgeSource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteAgeSource.getByName(NAME) } returns ApiResult.Response(
            code = HttpCode.Default,
            body = remoteAge,
        )

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Failure.Local,
        )
    }

    @Test
    fun `get by name should return remote api failure on api failure`() = runTest {
        coEvery { localAgeSource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteAgeSource.getByName(NAME) } returns ApiResult.Failure.Api(
            code = HttpCode.Default,
            fallbackBody = RemoteAge.Error(MESSAGE),
        )

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Failure.Remote.Api(MESSAGE),
        )
    }

    @Test
    fun `get by name should return remote network failure on network failure`() = runTest {
        coEvery { localAgeSource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteAgeSource.getByName(NAME) } returns ApiResult.Failure.Network(
            exception = IOException(MESSAGE)
        )

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Failure.Remote.Network(MESSAGE),
        )
    }

    @Test
    fun `get by name should return remote else failure on else failure`() = runTest {
        coEvery { localAgeSource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteAgeSource.getByName(NAME) } returns ApiResult.Failure.Else(
            throwable = Throwable(MESSAGE),
        )

        Assert.assertEquals(
            ageRepository.getByName(NAME),
            DataResult.Failure.Remote.Else(MESSAGE),
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}