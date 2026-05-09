package com.zhei.search_arena.core.data.models

data class PlayerDTO(
    val id: String = "",
    val username: String = "",
    val matchesPlayed: Int = 0,
    val victories: Int = 0,
    val level: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
