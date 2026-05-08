package com.zhei.search_arena.features.create_profile.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhei.search_arena.features.create_profile.domain.usecases.UseCaseCreatePlayer
import kotlinx.coroutines.launch

// ¿Por qué ICreateProfile, en ves de la Clase CreateProfileRepository()?
// Porque el ViewModel debe depender de abstracciones y no de implementaciones concretas

class CreateProfileVM(
    private val useCaseCreatePlayer: UseCaseCreatePlayer
): ViewModel() {

    private val nameClass = CreateProfileVM::class.simpleName.toString()

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _success = mutableStateOf(false)
    val success: State<Boolean> = _success


    /**
     * Reescribe el texto actual de la variable _name. Los
     * valores del parametro (value) son capturados desde la UI.
     * */
    fun setText(value: String) { _name.value = value }


    /**
     * Función encargada de validar si se cumplen las
     * condiciones para realizar el envío de la información.
     * */
    private fun readyToSend(): Boolean = _name.value.trim().isNotEmpty()


    /**
     * Envía la información despues de validar despues de validar
     * los requerimientos necesarios para enviarla.
     * */
    fun send() {
        viewModelScope.launch {
            if (readyToSend()) {
                useCaseCreatePlayer(_name.value)
                _success.value = true
                setText("")
            }
        }
    }
}