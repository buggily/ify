package com.buggily.ify.core.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Locale

class FormatCapitalizeTest {

    private lateinit var formatCapitalize: FormatCapitalize

    @Before
    fun before() {
        formatCapitalize = FormatCapitalize(Locale.getDefault())
    }

    @Test
    fun formatCapitalizeCapitalizes() {
        Assert.assertEquals(
            "Hello",
            formatCapitalize("hello"),
        )
    }
}
