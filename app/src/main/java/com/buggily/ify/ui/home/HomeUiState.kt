package com.buggily.ify.ui.home

data class HomeUiState(
    val nameState: NameState,
) {

    data class NameState(
        val name: String,
        val onChange: (String) -> Unit,
        val onClear: (() -> Unit)?,
    ) {

        companion object {
            val default: NameState
                get() = NameState(
                    name = String(),
                    onChange = {},
                    onClear = null,
                )
        }
    }

    companion object {
        val default: HomeUiState
            get() = HomeUiState(
                nameState = NameState.default,
            )
    }
}
