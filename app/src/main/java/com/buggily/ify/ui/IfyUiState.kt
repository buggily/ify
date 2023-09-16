package com.buggily.ify.ui

data class IfyUiState(
    val nameState: NameState,
) {

    data class NameState(
        val name: String,
        val onChange: (String) -> Unit,
        val onClear: (() -> Unit)?,
    )
}
