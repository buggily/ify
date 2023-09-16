package com.buggily.ify.ui.home

data class HomeUiState(
    val nameState: NameState,
) {

    data class NameState(
        val name: String,
        val onChange: (String) -> Unit,
        val onClear: (() -> Unit)?,
    )
}
