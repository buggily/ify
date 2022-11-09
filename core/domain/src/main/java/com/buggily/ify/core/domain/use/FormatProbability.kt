package com.buggily.ify.core.domain.use

import kotlin.math.roundToInt

class FormatProbability {

    operator fun invoke(probability: Float): String {
        val percentage: Float = probability * 100
        return percentage.roundToInt().toString()
    }
}
