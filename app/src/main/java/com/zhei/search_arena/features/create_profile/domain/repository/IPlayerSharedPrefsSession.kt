package com.zhei.search_arena.features.create_profile.domain.repository

interface IPlayerSharedPrefsSession {

    fun save(userId: String)

    fun getId(): String

}