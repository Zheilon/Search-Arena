package com.zhei.search_arena.core.domain.models

data class PlayerHistory(
    val algorithmSearch: String,
    val algorithmSort: String,
    val digits: Int,
    val numberFound: Int,
    val iteractionsQuantity: Int,
    val time: String
)
