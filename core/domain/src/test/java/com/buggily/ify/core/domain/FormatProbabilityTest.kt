package com.buggily.ify.core.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FormatProbabilityTest {

    private lateinit var formatProbability: FormatProbability

    @Before
    fun before() {
        formatProbability = FormatProbability()
    }

    @Test
    fun `format probability generates percent`() {
        Assert.assertEquals(
            "50",
            formatProbability(.50f),
        )
    }

    @Test
    fun `format probability rounds to nearest percent`() {
        Assert.assertEquals(
            "51",
            formatProbability(.505f)
        )
    }
}
