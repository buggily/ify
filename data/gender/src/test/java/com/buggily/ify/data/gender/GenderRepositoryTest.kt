package com.buggily.ify.data.gender

import com.buggily.core.remote.ApiResult
import com.buggily.core.remote.HttpCode
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.local.gender.LocalGender
import com.buggily.ify.local.gender.LocalGenderSourceable
import com.buggily.ify.remote.gender.RemoteGender
import com.buggily.ify.remote.gender.RemoteGenderSourceable
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class GenderRepositoryTest {

    private val remoteGenderSource: RemoteGenderSourceable = mockk()
    private val localGenderSource: LocalGenderSourceable = mockk()
    private lateinit var genderRepository: GenderRepository

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()

        genderRepository = GenderRepository(
            remoteGenderSource = remoteGenderSource,
            localGenderSource = localGenderSource,
        )

        coEvery {
            localGenderSource.insert(any())
        } returns Unit
    }

    @Test
    fun `get by name returns local response when local source has name`() = runTest {
        val localGender = LocalGender(
            gender = null,
            name = NAME,
            count = 0,
            probability = 0f,
        )

        coEvery {
            localGenderSource.getByName(NAME)
        } returns flowOf(localGender)

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Response.Local(localGender.to()),
        )
    }

    @Test
    fun `get by name returns remote response when local source lacks name and remote source has name`() = runTest {
        val remoteGender = RemoteGender(
            gender = null,
            name = NAME,
            count = 0,
            probability = 0f,
        )

        coEvery {
            localGenderSource.getByName(NAME)
        } returns emptyFlow() andThen flowOf(remoteGender.toLocal())

        coEvery {
            remoteGenderSource.getByName(NAME)
        } returns ApiResult.Response(
            code = HttpCode.Default,
            body = remoteGender,
        )

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Response.Remote(remoteGender.toLocal().to()),
        )

        coVerify {
            localGenderSource.insert(remoteGender.toLocal())
        }
    }

    @Test
    fun `get by name returns local failure when local source lacks name and remote source has name but insert fails`() = runTest {
        val remoteGender = RemoteGender(
            name = NAME,
            gender = null,
            count = 0,
            probability = 0f,
        )

        coEvery {
            localGenderSource.getByName(NAME)
        } returns emptyFlow()

        coEvery {
            remoteGenderSource.getByName(NAME)
        } returns ApiResult.Response(
            code = HttpCode.Default,
            body = remoteGender,
        )

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Failure.Local,
        )

        coVerify {
            localGenderSource.insert(remoteGender.toLocal())
        }
    }

    @Test
    fun `get by name returns remote api failure on api failure`() = runTest {
        coEvery {
            localGenderSource.getByName(NAME)
        } returns emptyFlow()

        coEvery {
            remoteGenderSource.getByName(NAME)
        } returns ApiResult.Failure.Api(
            code = HttpCode.Default,
            fallbackBody = RemoteGender.Error(MESSAGE),
        )

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Failure.Remote.Api(MESSAGE),
        )
    }

    @Test
    fun `get by name returns remote network failure on network failure`() = runTest {
        coEvery {
            localGenderSource.getByName(NAME)
        } returns emptyFlow()

        coEvery {
            remoteGenderSource.getByName(NAME)
        } returns ApiResult.Failure.Network(
            exception = IOException(MESSAGE)
        )

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Failure.Remote.Network(MESSAGE),
        )
    }

    @Test
    fun `get by name returns remote else failure on else failure`() = runTest {
        coEvery {
            localGenderSource.getByName(NAME)
        } returns emptyFlow()

        coEvery {
            remoteGenderSource.getByName(NAME)
        } returns ApiResult.Failure.Else(
            throwable = Throwable(MESSAGE),
        )

        Assert.assertEquals(
            genderRepository.getByName(NAME),
            DataResult.Failure.Remote.Else(MESSAGE),
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}
