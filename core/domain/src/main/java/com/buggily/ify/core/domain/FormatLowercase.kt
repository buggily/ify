package com.buggily.ify.core.domain

import java.util.Locale

class FormatLowercase(
    private val locale: Locale,
) {

    operator fun invoke(string: String): String = string.lowercase(locale)
}
