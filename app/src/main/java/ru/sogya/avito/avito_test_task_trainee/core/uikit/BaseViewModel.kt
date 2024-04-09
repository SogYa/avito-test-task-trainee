package ru.sogya.avito.avito_test_task_trainee.core.uikit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<ScreenState : UDF.State, ScreenIntent : UDF.Intent, ScreenEffect : UDF.Effect>(
    initialState: ScreenState,
) : ViewModel() {
    protected val progress = MutableStateFlow(value = false)
    abstract fun handleIntents(intent: ScreenIntent)

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<ScreenIntent> = MutableSharedFlow()

    private val _effect: Channel<ScreenEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToIntents()
    }

    private fun subscribeToIntents() {
        viewModelScope.launch {
            _intent.collect {
                handleIntents(it)
            }
        }
    }

    fun setIntent(intent: ScreenIntent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    protected suspend fun setState(reducer: ScreenState.() -> ScreenState) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    protected fun setEffect(builder: () -> ScreenEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    protected fun clearProgress() {
        viewModelScope.launch {
            progress.emit(value = false)
        }
    }
}

interface UDF {
    interface State
    interface Intent
    interface Effect
}