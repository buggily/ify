package com.buggily.ify.core.domain

import java.util.Locale

class Format(
    val locale: Locale,
    val formatLowercase: FormatLowercase,
    val formatNumber: FormatNumber,
    val formatProbability: FormatProbability,
)
