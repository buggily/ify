package com.buggily.ify.core.domain.use

import java.text.NumberFormat

class FormatNumber(
    private val numberFormat: NumberFormat,
) {

    operator fun invoke(number: Int): String = numberFormat.format(number)
}
