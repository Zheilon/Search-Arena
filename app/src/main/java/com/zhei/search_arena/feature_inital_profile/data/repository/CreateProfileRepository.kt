package com.zhei.search_arena.feature_inital_profile.data.repository
import com.google.firebase.firestore.FirebaseFirestore

class CreateProfileRepository {

    private val firestore by lazy { FirebaseFirestore.getInstance() }


    /**
     * Valida si el id pasado por parámetro existe
     * */
    private fun thisIdExists(id: String): Boolean {
        return false
    }


    fun send(playerNm: String) {
        /* Envio de información de jugador a la base de datos */
    }
}