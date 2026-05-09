package com.zhei.search_arena.features.main_arena.domain.usecases
import android.util.Log
import com.zhei.search_arena.core.domain.models.Player
import com.zhei.search_arena.features.create_profile.domain.repository.IPlayerSharedPrefsSession
import com.zhei.search_arena.features.main_arena.data.repository.MainArenaRemoteRepository
import com.zhei.search_arena.features.main_arena.domain.repository.IMainArenaRemote

class UseCaseGetRemotePlayer(
    private val mainArenaRemoteRepository: IMainArenaRemote,
    private val playerSharedPrefs: IPlayerSharedPrefsSession
) {

    suspend operator fun invoke(): Player? {
        val playerID = playerSharedPrefs.getId()
        Log.i("PlayerID", playerID)
        return mainArenaRemoteRepository.getPlayer(playerID)
    }
}