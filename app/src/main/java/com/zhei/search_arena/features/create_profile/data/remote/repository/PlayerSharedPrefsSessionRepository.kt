package com.zhei.search_arena.features.create_profile.data.remote.repository
import android.content.Context
import com.zhei.search_arena.features.create_profile.domain.repository.IPlayerSharedPrefsSession

class PlayerSharedPrefsSessionRepository(private val context: Context): IPlayerSharedPrefsSession {

    private val pref = context.getSharedPreferences("player_info", Context.MODE_PRIVATE)


    /**
     * Guarda el ID del jugador en SharedPreferences.
     *
     * - Usa la clave "player_id" para persistir el valor.
     * - apply() ejecuta la escritura de forma asíncrona (no bloquea el hilo principal).
     *
     * @param userId Identificador único del jugador que se desea almacenar.
     */
    override fun save(userId: String) {
        pref.edit().putString("player_id", userId).apply()
    }


    /**
     * Obtiene el ID del jugador almacenado en SharedPreferences.
     *
     * - Si no existe un valor guardado, retorna un String vacío ("").
     * - Este método es seguro para llamadas frecuentes y rápidas.
     *
     * @return El ID del jugador si existe, o una cadena vacía si no hay datos guardados.
     */
    override fun getId(): String =
        pref.getString("player_id", "") ?: ""


}