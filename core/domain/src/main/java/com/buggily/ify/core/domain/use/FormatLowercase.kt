package com.buggily.ify.core.domain.use

import java.util.Locale

class FormatLowercase(
    private val locale: Locale,
) {

    operator fun invoke(string: String): String = string.lowercase(locale)
}
