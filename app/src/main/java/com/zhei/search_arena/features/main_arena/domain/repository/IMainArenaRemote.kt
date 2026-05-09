package com.zhei.search_arena.features.main_arena.domain.repository
import com.zhei.search_arena.core.domain.models.Player

interface IMainArenaRemote {

    suspend fun getPlayer(playerId: String): Player?
}