package com.zhei.search_arena.features.create_profile.domain.usecases
import com.zhei.search_arena.core.domain.models.Player
import com.zhei.search_arena.features.create_profile.domain.repository.ICreateProfile
import com.zhei.search_arena.features.create_profile.domain.repository.IPlayerSharedPrefsSession
import java.util.UUID

// Porque el UseCase debe depender de una abstracción y no de una implementación concreta.

class UseCaseCreatePlayer(
    private val playerSessionRepo: IPlayerSharedPrefsSession,
    private val createProfileRepo: ICreateProfile
) {

    /**
     * Genera un identificador único para un jugador
     * utilizando UUID versión 4.
     *
     * Ejemplo:
     * PLY_f47ac10b-58cc-4372-a567-0e02b2c3d479
     */
    private fun genID (): String = "Player_${UUID.randomUUID()}"


    suspend operator fun invoke(username: String) {

        val id = genID()

        createProfileRepo.send(
            Player(
                id = id,
                username = username,
                matchesPlayed = 0,
                victories = 0,
                level = 0
            )
        )

        playerSessionRepo.save(id)
    }

}