package com.zhei.search_arena.features.create_profile.domain.repository
import com.zhei.search_arena.core.domain.models.Player

interface ICreateProfile {

    suspend fun send(player: Player)

}