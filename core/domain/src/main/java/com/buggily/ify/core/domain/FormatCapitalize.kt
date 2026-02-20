package com.buggily.ify.core.domain

import java.util.Locale

class FormatCapitalize(
    private val locale: Locale,
) {

    operator fun invoke(value: String): String = value.capitalize(locale)
}
