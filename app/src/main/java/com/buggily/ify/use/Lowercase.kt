package com.buggily.ify.use

import java.util.Locale

class Lowercase(
    private val locale: Locale,
) {

    operator fun invoke(string: String): String = string.lowercase(locale)
}
