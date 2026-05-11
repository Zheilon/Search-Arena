package com.zhei.search_arena.features.solo_arena.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow


/**
 * Este ViewModel se va inicializar el la parte más alta
 * de la función encargada de manejar los gráficos de
 * navegación llamada, MyNavGraph().
 *
 * Ademas, la información que contiene este ViewModel será
 * util en las pantallas: SoloArenaLoad, SoloResults. Despues
 * de esta última toda la información quedará por default.
 *
 * Nota:
 * 1. No poner lógica de negocio en la UI de compose!
 * */
class SoloArenaVM: ViewModel() {

    private val _searchAlgo = mutableStateOf<String?>(null)
    val searchAlgo: State<String?> = _searchAlgo

    private val _withSort = mutableStateOf(false)
    val withSort: State<Boolean> = _withSort

    private val _disableButton = mutableStateOf(false)
    val disableButton: State<Boolean> = _disableButton

    private val _isCodeShowed = mutableStateOf(false)
    val isCodeShowed: State<Boolean> = _isCodeShowed

    private val _code = mutableStateOf("")
    val code: State<String> = _code

    private val _sortAlgo = mutableStateOf<String?>(null)
    val sortAlgo: State<String?> = _sortAlgo

    private val _datasetDigits = mutableStateOf("")
    val datasetDigits: State<String> = _datasetDigits

    private val _selectedNumber = mutableStateOf("")
    val selectedNumber: State<String> = _selectedNumber

    private val _decisionIsConfimed = mutableStateOf(false)
    val decisionIsConfirmed: State<Boolean> = _decisionIsConfimed


    fun <T> setSortAlgo(algoritm: T) { _sortAlgo.value = algoritm as? String }

    fun setSearchAlgo(algorithm: String) {

        _searchAlgo.value = algorithm

        val algorithmsNeedSort = listOf(
            "Binary",
            "Jump",
            "Interpolation",
            "Exponential",
            "Fibonacci",
            "Ternary"
        )

        val requiresSort = algorithm in algorithmsNeedSort

        _withSort.value = requiresSort
        _disableButton.value = requiresSort
    }

    fun setWithSortToggle() { _withSort.value = !_withSort.value }

    fun setIsCodeShowed(value: Boolean) { _isCodeShowed.value = value }

    fun setDataSetDigits(value: String) { _datasetDigits.value = value }

    fun setDesicion(decision: Boolean) { _decisionIsConfimed.value = decision }

    fun setCode(value: String) { _code.value = value }


    fun setSelectedNumber(value: String) {
        if (_datasetDigits.value.isNotEmpty()) {
            val limit = _datasetDigits.value.toInt()
            if (value.length <= limit) _selectedNumber.value = value
        }
    }


    fun cleanAllParams() {
        _sortAlgo.value = null
        _searchAlgo.value = null
        _withSort.value = false
        _disableButton.value = false
        _decisionIsConfimed.value = false
        _datasetDigits.value = ""
        _selectedNumber.value = ""
    }


    /**
     * Retorna el rango valido que tendra el dataset
     * de prueba
     *
     * Ejemplo: [000, 999]
     * */
    fun showDataRange(): String {

        if (_datasetDigits.value.trim().isNotEmpty()) {

            val digits = _datasetDigits.value.toInt()
            val max = 10.0.pow(digits) - 1
            val maxFormated = NumberFormat
                        .getNumberInstance(Locale("es", "CO"))
                        .format(max.toInt())

            val paddZeros = (0 until digits).joinToString("") { "0" }

            return "[$paddZeros...$maxFormated]"

        } else { return "" }
    }


    /**
     * Verifica si el número ingresado
     * está dentro del rango permitido.
     */
    fun isIntoTheRange(): Boolean {
        if (_selectedNumber.value.isBlank()) return true
        val digits = _datasetDigits.value.toIntOrNull() ?: return false
        val number = _selectedNumber.value
        return number.length == digits
    }


    /**
     * Verifica si el valor que define la
     * cantidad de digitos es mayor a (cero)
     */
    fun digitsForDatasetIsMorethanZero(): Boolean {
        // ¿Por qué se puso return true?
        // Primero para aplicar correctamente el .toInt(),
        // lo segundo sería que, para evitar que cuando el
        // textfield esta vacio, no se muestre el mensaje de que
        // debe ingresar un número mayor que (0).
        if (_datasetDigits.value.isEmpty()) return true
        return _datasetDigits.value.toInt() > 0
    }


    /**
     * Valida si todos los datos necesarios están completos.
     */
    fun everythingIsReady(): Boolean {

        val datasetDigits = _datasetDigits.value.toIntOrNull()
        val cond1 = isIntoTheRange()

        val cond2 = !_searchAlgo.value.isNullOrEmpty()
        val cond3 = _datasetDigits.value.isNotEmpty()

        val cond4 = _selectedNumber.value.isNotEmpty()
        val cond5 = datasetDigits != null && _selectedNumber.value.length == datasetDigits

        val cond6 = digitsForDatasetIsMorethanZero()
        val cond7 = _withSort.value && !_sortAlgo.value.isNullOrEmpty()
        val cond8 = !_withSort.value && _sortAlgo.value.isNullOrEmpty()

        return cond1 && cond2 && cond3 && cond4 && cond5 && cond6 && (cond7 || cond8)
    }


}