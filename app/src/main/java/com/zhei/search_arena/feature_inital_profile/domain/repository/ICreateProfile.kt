package com.zhei.search_arena.feature_inital_profile.domain.repository
import com.zhei.search_arena.feature_inital_profile.domain.model.Player

interface ICreateProfile {

    suspend fun send(player: Player)

}