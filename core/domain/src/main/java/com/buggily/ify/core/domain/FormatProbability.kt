package com.buggily.ify.core.domain

import kotlin.math.roundToInt

class FormatProbability {

    operator fun invoke(probability: Float?): String? = probability?.let {
        val percentage: Float = it * 100
        percentage.roundToInt().toString()
    }
}
