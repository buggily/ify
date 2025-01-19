package com.buggily.ify.core.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Locale

class FormatUppercaseTest {

    private lateinit var formatUppercase: FormatUppercase

    @Before
    fun before() {
        formatUppercase = FormatUppercase(Locale.getDefault())
    }

    @Test
    fun formatUppercaseUppercases() {
        Assert.assertEquals(
            "ABC",
            formatUppercase("abc"),
        )
    }
}
