package com.buggily.ify.core.domain

import java.util.Locale

class FormatUppercase(
    private val locale: Locale,
) {

    operator fun invoke(string: String): String = string.uppercase(locale)
}
