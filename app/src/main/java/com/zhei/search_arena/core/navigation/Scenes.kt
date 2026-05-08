package com.zhei.search_arena.core.navigation
import kotlinx.serialization.Serializable

@Serializable sealed class Scenes {

    /**
     * Este objeto representa la escena donde el
     * user debe crear un perfil.
     * */
    @Serializable data object CreateUserScene: Scenes()

    /**
     * Este objeto presenta la escena donde el usuario
     * puede elegir que hacer en la app, si crear una
     * sala solo o jugar con más personas.
     * */
    @Serializable data object MainArenaScene: Scenes()

}