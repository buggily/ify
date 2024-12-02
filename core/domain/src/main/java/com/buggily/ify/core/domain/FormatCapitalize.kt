package com.buggily.ify.core.domain

import java.util.Locale

class FormatCapitalize(
    private val locale: Locale,
) {

    operator fun invoke(string: String): String = string.capitalize(locale)
}
