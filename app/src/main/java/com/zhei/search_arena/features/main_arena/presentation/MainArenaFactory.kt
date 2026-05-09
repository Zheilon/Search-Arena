package com.zhei.search_arena.features.main_arena.presentation
import com.zhei.search_arena.core.factories.BaseFactoryVM
import com.zhei.search_arena.features.main_arena.domain.usecases.UseCaseGetRemotePlayer

class MainArenaFactory(
    private val remotePlayer: UseCaseGetRemotePlayer
):
    BaseFactoryVM<MainArenaVM>({
        MainArenaVM(remotePlayer)
})