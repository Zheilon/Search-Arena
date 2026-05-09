package com.zhei.search_arena.features.main_arena.data.repository
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.zhei.search_arena.core.data.models.PlayerDTO
import com.zhei.search_arena.core.domain.models.Player
import com.zhei.search_arena.core.mappers.toDomain
import com.zhei.search_arena.features.main_arena.domain.repository.IMainArenaRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainArenaRemoteRepository : IMainArenaRemote {

    private val nameclass = MainArenaRemoteRepository::class.java.simpleName.toString()
    private val firestore by lazy { FirebaseFirestore.getInstance() }


    override suspend fun getPlayer(playerId: String): Player? =
        withContext(Dispatchers.IO) {
            runCatching {

                firestore.collection("players")
                    .document(playerId)
                    .get()
                    .await()
                    .toObject(PlayerDTO::class.java)?.toDomain()

            }.onFailure {

                Log.e(nameclass, "FireStore: Error getting player", it)

            }.getOrNull()
        }


}