package com.zhei.search_arena.core.common.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val DEVICE_TOP_PADDING: @Composable () -> Dp = { getTopOrBottomPadding(type = "top") }
val DEVICE_BOTTOM_PADDING: @Composable () -> Dp = { getTopOrBottomPadding(type = "bottom") }

val HEIGHT_DP_DEVICE: @Composable () -> Dp = { LocalConfiguration.current.screenHeightDp.dp }
val WIDTH_DP_DEVICE: @Composable () -> Dp = { LocalConfiguration.current.screenWidthDp.dp }