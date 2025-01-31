package com.buggily.ify.ui

data class IfyUiState(
    val nameState: NameState,
) {

    data class NameState(
        val value: String,
        val onValueChange: (String) -> Unit,
        val onValueClear: (() -> Unit)?,
    )
}
