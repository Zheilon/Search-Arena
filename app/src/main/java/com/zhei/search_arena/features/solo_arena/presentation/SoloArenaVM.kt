package com.zhei.search_arena.features.solo_arena.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class SoloArenaVM: ViewModel() {

    private val _searchAlgo = mutableStateOf<String?>(null)
    val searchAlgo: State<String?> = _searchAlgo

    private val _withSort = mutableStateOf(false)
    val withSort: State<Boolean> = _withSort

    private val _sortAlgo = mutableStateOf<String?>(null)
    val sortAlgo: State<String?> = _sortAlgo

    private val _datasetDigits = mutableStateOf("")
    val datasetDigits: State<String> = _datasetDigits

    private val _selectedNumber = mutableStateOf("")
    val selectedNumber: State<String> = _selectedNumber


    fun setSortAlgo(algoritm: String) { _sortAlgo.value = algoritm }

    fun setSearchAlgo(algorithm: String) { _searchAlgo.value = algorithm }

    fun setWithSort() { _withSort.value = !_withSort.value }

    fun setDataSetDigits(value: String) { _datasetDigits.value = value }


    fun setSelectedNumber(value: String) {
        if (_datasetDigits.value.isNotEmpty()) {
            val limit = _datasetDigits.value.toInt()
            if (value.length <= limit) _selectedNumber.value = value
        }
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

            return "[$paddZeros, $maxFormated]"

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
        if (_datasetDigits.value.isEmpty()) return true
        return _datasetDigits.value.toInt() > 0
    }


    /**
     * Valida si todos los datos necesarios están completos.
     */
    fun everythingIsReady(): Boolean {
        val cond1 = isIntoTheRange()
        val cond2 = _searchAlgo.value?.isNotEmpty() == true
        val cond3 = _datasetDigits.value.isNotEmpty()
        val cond4 = _selectedNumber.value.isNotEmpty()
        val cond5 = _selectedNumber.value.length == _datasetDigits.value.toInt()
        val cond6 = digitsForDatasetIsMorethanZero()
        val cond7 = _withSort.value && !_sortAlgo.value.isNullOrEmpty()

        return cond1 && cond2 && cond3 && cond4 && cond5 && cond6 && cond7
    }


}