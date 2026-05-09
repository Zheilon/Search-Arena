package com.zhei.search_arena.features.main_arena.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhei.search_arena.core.domain.models.Player
import com.zhei.search_arena.features.main_arena.domain.usecases.UseCaseGetRemotePlayer
import kotlinx.coroutines.launch

class MainArenaVM(
    private val useCaseGetRemotePlayer: UseCaseGetRemotePlayer
) : ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _level = mutableStateOf("")
    val level: State<String> = _level

    private val _matches = mutableStateOf("")
    val matches: State<String> = _matches

    private val _victories = mutableStateOf("")
    val victories: State<String> = _victories


    /**
     * Inicializa de forma perezosa (lazy) la información
     * del jugador remoto.
     *
     * Esta función:
     * - Consulta los datos del jugador usando el caso de uso.
     * - Actualiza el estado observable del ViewModel.
     * - Evita múltiples inicializaciones innecesarias.
     *
     * Se recomienda llamarla una sola vez desde la UI
     * (por ejemplo, dentro de LaunchedEffect).
     *
     * Ejemplo de uso:
     *
     * LaunchedEffect(Unit) {
     *     viewModel.lazyInit()
     * }
     */
    fun lazyInit() {
        viewModelScope.launch {
            useCaseGetRemotePlayer()?.let { player ->
                _username.value = player.username
                _matches.value  = player.matchesPlayed.toString()
                _victories.value = player.victories.toString()
                _level.value = player.level.toString()
            }

        }
    }

}