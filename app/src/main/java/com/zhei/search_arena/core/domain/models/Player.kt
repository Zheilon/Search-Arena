package com.zhei.search_arena.core.domain.models

data class Player(
    val id: String,
    val username: String,
    val matchesPlayed: Int,
    val createdAt: Long = System.currentTimeMillis()
)