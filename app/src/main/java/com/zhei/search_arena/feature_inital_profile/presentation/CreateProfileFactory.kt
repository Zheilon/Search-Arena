package com.zhei.search_arena.feature_inital_profile.presentation
import com.zhei.search_arena.core.factories.BaseFactoryVM
import com.zhei.search_arena.feature_inital_profile.data.repository.CreateProfileRepository
import com.zhei.search_arena.feature_inital_profile.domain.usecases.UseCaseCreatePlayer

class CreateProfileFactory(
    private val createUserID: UseCaseCreatePlayer
):
    BaseFactoryVM<CreateProfileVM> ({
        CreateProfileVM(
            useCaseCreatePlayer = createUserID
        )
    })