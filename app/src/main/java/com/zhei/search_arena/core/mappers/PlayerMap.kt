package com.zhei.search_arena.core.mappers
import com.zhei.search_arena.core.data.models.PlayerDTO
import com.zhei.search_arena.core.domain.models.Player

fun PlayerDTO.toDomain(): Player = Player(
    id, username, matchesPlayed, victories, level, createdAt
)