package com.buggily.ify.data.nationality

import com.buggily.core.remote.ApiResult
import com.buggily.core.remote.HttpCode
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.core.test.CoroutineTestRule
import com.buggily.ify.local.nationality.LocalNationality
import com.buggily.ify.local.nationality.LocalNationalitySourceable
import com.buggily.ify.local.nationality.country.LocalNationalityCountrySourceable
import com.buggily.ify.remote.nationality.RemoteNationality
import com.buggily.ify.remote.nationality.RemoteNationalitySourceable
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

class NationalityRepositoryTest {

    private val remoteNationalitySource: RemoteNationalitySourceable = mockk()
    private val localNationalitySource: LocalNationalitySourceable = mockk()
    private val localNationalityCountrySource: LocalNationalityCountrySourceable = mockk()

    private lateinit var nationalityRepository: NationalityRepository

    @get:Rule
    val rule = CoroutineTestRule

    @Before
    fun before() {
        clearAllMocks()

        nationalityRepository = NationalityRepository(
            remoteNationalitySource = remoteNationalitySource,
            localNationalitySource = localNationalitySource,
            localNationalityCountrySource = localNationalityCountrySource,
        )

        coEvery { localNationalitySource.insert(any()) } returns Unit
        coEvery { localNationalityCountrySource.insert(any()) } returns Unit
    }

    @Test
    fun `get by name should return local response when local source has name`() = runTest {
        val localNationality = LocalNationality(
            name = NAME,
            count = 0,
        )

        val localNationalityWithCountries = LocalNationality.WithCountries(
            nationality = localNationality,
            countries = emptyList(),
        )

        coEvery { localNationalitySource.getByName(NAME) } returns flowOf(localNationalityWithCountries)

        Assert.assertEquals(
            nationalityRepository.getByName(NAME),
            DataResult.Response.Local(localNationalityWithCountries.to()),
        )
    }

    @Test
    fun `get by name should return remote response when local source does not have name and remote source has name`() =
        runTest {
            val remoteNationality = RemoteNationality(
                name = NAME,
                countries = emptyList(),
                count = 0,
            )

            coEvery { localNationalitySource.getByName(NAME) } returns emptyFlow() andThen flowOf(remoteNationality.toLocalWithCountries())
            coEvery { remoteNationalitySource.getByName(NAME) } returns ApiResult.Response(
                code = HttpCode.Default,
                body = remoteNationality,
            )

            Assert.assertEquals(
                nationalityRepository.getByName(NAME),
                DataResult.Response.Remote(remoteNationality.toLocalWithCountries().to()),
            )
        }

    @Test
    fun `get by name should return local failure when local source does not have name and remote source has name but insert fails`() =
        runTest {
            val remoteNationality = RemoteNationality(
                name = NAME,
                countries = emptyList(),
                count = 0,
            )

            coEvery { localNationalitySource.getByName(NAME) } returns emptyFlow()
            coEvery { remoteNationalitySource.getByName(NAME) } returns ApiResult.Response(
                code = HttpCode.Default,
                body = remoteNationality,
            )

            Assert.assertEquals(
                nationalityRepository.getByName(NAME),
                DataResult.Failure.Local,
            )
        }

    @Test
    fun `get by name should return remote api failure on api failure`() = runTest {
        coEvery { localNationalitySource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteNationalitySource.getByName(NAME) } returns ApiResult.Failure.Api(
            code = HttpCode.Default,
            fallbackBody = RemoteNationality.Error(MESSAGE),
        )

        Assert.assertEquals(
            nationalityRepository.getByName(NAME),
            DataResult.Failure.Remote.Api(MESSAGE),
        )
    }

    @Test
    fun `get by name should return remote network failure on network failure`() = runTest {
        coEvery { localNationalitySource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteNationalitySource.getByName(NAME) } returns ApiResult.Failure.Network(
            exception = IOException(MESSAGE)
        )

        Assert.assertEquals(
            nationalityRepository.getByName(NAME),
            DataResult.Failure.Remote.Network(MESSAGE),
        )
    }

    @Test
    fun `get by name should return remote else failure on else failure`() = runTest {
        coEvery { localNationalitySource.getByName(NAME) } returns emptyFlow()
        coEvery { remoteNationalitySource.getByName(NAME) } returns ApiResult.Failure.Else(
            throwable = Throwable(MESSAGE),
        )

        Assert.assertEquals(
            nationalityRepository.getByName(NAME),
            DataResult.Failure.Remote.Else(MESSAGE),
        )
    }

    private companion object {
        private const val NAME = "name"
        private const val MESSAGE = "message"
    }
}