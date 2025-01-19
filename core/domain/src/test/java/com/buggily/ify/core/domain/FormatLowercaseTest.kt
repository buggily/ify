package com.buggily.ify.core.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Locale

class FormatLowercaseTest {

    private lateinit var formatLowercase: FormatLowercase

    @Before
    fun before() {
        formatLowercase = FormatLowercase(Locale.getDefault())
    }

    @Test
    fun formatLowercaseLowercases() {
        Assert.assertEquals(
            "abc",
            formatLowercase("ABC"),
        )
    }
}
