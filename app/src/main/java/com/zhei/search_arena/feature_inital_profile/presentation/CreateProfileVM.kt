package com.zhei.search_arena.feature_inital_profile.presentation
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateProfileVM: ViewModel() {

    private val nameClass = CreateProfileVM::class.simpleName.toString()

    private val _text = mutableStateOf("")
    val text: State<String> = _text


    /**
     * Reescribe el texto actual de la variable _text. Los
     * valores del parametro (value) son capturados desde la UI.
     * */
    fun setText(value: String) { _text.value = value }


    /**
     * Función encargada de validar si se cumplen las
     * condiciones para realizar el envío de la información.
     * */
    private fun readyToSend(): Boolean = _text.value.isNotEmpty()


    /**
     * Envía la información despues de validar despues de validar
     * los requerimientos necesarios para enviarla.
     * */
    fun send() {
        if (readyToSend()) {
            Log.e(nameClass, "¡Información enviada!")
        }
    }
}