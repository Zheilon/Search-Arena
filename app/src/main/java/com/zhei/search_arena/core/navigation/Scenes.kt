package com.zhei.search_arena.core.navigation
import kotlinx.serialization.Serializable

@Serializable sealed class Scenes {

    /**
     * Este objeto representa la escena inicial donde el
     * user debe crear un perfil.
     * */
    @Serializable data object InitScene: Scenes()

}