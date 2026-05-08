package com.zhei.search_arena.feature_inital_profile.domain.usecases
import com.zhei.search_arena.feature_inital_profile.data.repository.CreateProfileRepository
import com.zhei.search_arena.feature_inital_profile.domain.model.Player
import com.zhei.search_arena.feature_inital_profile.domain.repository.ICreateProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class UseCaseCreatePlayer(private val createProfileRepo: ICreateProfile) {

    /**
     * Genera un identificador único para un jugador
     * utilizando UUID versión 4.
     *
     * Ejemplo:
     * PLY_f47ac10b-58cc-4372-a567-0e02b2c3d479
     */
    private fun genID (): String = "PLY_${UUID.randomUUID()}"


    suspend operator fun invoke(nickname: String) {
        createProfileRepo.send(
            Player(
                id = genID(),
                name = nickname
            )
        )
    }

}