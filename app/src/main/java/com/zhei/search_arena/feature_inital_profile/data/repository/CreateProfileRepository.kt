package com.zhei.search_arena.feature_inital_profile.data.repository
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.zhei.search_arena.feature_inital_profile.domain.model.Player
import com.zhei.search_arena.feature_inital_profile.domain.repository.ICreateProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateProfileRepository: ICreateProfile {

    private val nameClass = CreateProfileRepository::class.java.simpleName.toString()
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    /**
     * Envío de información del jugador a la base de datos.
     *
     * Notas:
     * (1).  No realizo validación de que exista ID ya que en el
     * usecase: UseCaseCreatePlayer(), genero un id con
     * UUID, y la probabilidad de colisión es brutalmente pequeña
     * */
    override suspend fun send(player: Player) {
        withContext(Dispatchers.IO) {
            firestore.collection("players").document(player.id).set(player)
            Log.d(nameClass, "¡Información guardada correctamente!")
        }
    }
}