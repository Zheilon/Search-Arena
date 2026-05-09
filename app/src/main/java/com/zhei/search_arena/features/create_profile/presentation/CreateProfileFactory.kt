package com.zhei.search_arena.features.create_profile.presentation
import com.zhei.search_arena.core.factories.BaseFactoryVM
import com.zhei.search_arena.features.create_profile.domain.usecases.UseCaseCreatePlayer

class CreateProfileFactory(
    private val usercaseCreatePlayer: UseCaseCreatePlayer
):
    BaseFactoryVM<CreateProfileVM> ({
        CreateProfileVM(
            useCaseCreatePlayer = usercaseCreatePlayer
        )
    })