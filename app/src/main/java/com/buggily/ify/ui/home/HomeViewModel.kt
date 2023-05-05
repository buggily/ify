package com.buggily.ify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState>
    val uiState: StateFlow<HomeUiState> get() = _uiState

    val name: StateFlow<String>

    init {
        HomeUiState.default.copy(
            nameState = HomeUiState.NameState.default.copy(
                name = "Adam",
                onChange = ::onNameChange,
                onClear = ::onNameClear,
            ),
        ).let { _uiState = MutableStateFlow(it) }

        name = uiState.map { it.nameState.name }.debounce(1000).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = uiState.value.nameState.name,
        )
    }

    private fun onNameChange(name: String): Unit = setNameOfNameState(
        name = name,
    )

    private fun onNameClear(): Unit = onNameChange(
        name = HomeUiState.NameState.default.name,
    )

    private fun setNameOfNameState(name: String) = uiState.value.let {
        val onClear: (() -> Unit)? = if (name.isNotEmpty()) ::onNameClear else null

        val nameState: HomeUiState.NameState = it.nameState.copy(
            name = name,
            onClear = onClear,
        )

        setNameState(nameState)
    }

    private fun setNameState(nameState: HomeUiState.NameState) = _uiState.update {
        it.copy(nameState = nameState)
    }
}
