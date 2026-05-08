package com.zhei.search_arena.features.create_profile.data.remote.repository
import com.google.firebase.firestore.FirebaseFirestore
import com.zhei.search_arena.core.domain.models.Player
import com.zhei.search_arena.features.create_profile.domain.repository.ICreateProfile
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
     *
     * (1). ______________________________________________________
     * No realizo validación de que exista ID ya que en el
     * usecase: UseCaseCreatePlayer(), genero un id con
     * UUID, y la probabilidad de colisión es brutalmente pequeña
     *
     * (2). ______________________________________________________
     * Se añade .await() para suspender la coroutine
     * hasta que Firestore finalice la operación de guardado.
     *
     * Esto permite:
     * - esperar correctamente el resultado
     * - capturar excepciones reales
     * - evitar continuar el flujo antes del guardado
     * */
    override suspend fun send(player: Player) {
        withContext(Dispatchers.IO) {
            firestore
                .collection("players")
                .document(player.id)
                .set(player)
                .await()
        }
    }
}