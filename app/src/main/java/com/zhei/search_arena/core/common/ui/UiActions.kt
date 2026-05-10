package com.zhei.search_arena.core.common.ui
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

class UiActions {

    /**
     * Genera una animación infinita de color entre dos valores.
     * @param init Color inicial
     * @param target Color final
     * @param tween Duración de la transición (ms)
     * @return Color animado que oscila continuamente entre init y target
     */
    @Composable fun renderColorInfinityTransition(
        init: Color,
        target: Color,
        tween: Int
    ) : Color =
        rememberInfiniteTransition("").animateColor(
            initialValue = init,
            targetValue = target,
            animationSpec = infiniteRepeatable(
                animation = tween(tween),
                repeatMode = RepeatMode.Restart
            ), label = ""
        ).value


    /**
     * Esta función ayuda mucho, bastante! para evitar que se
     * generen más eventos, por efectos de que el usuario
     * pulse un botón varias veces seguidas.
     * */
    fun safeClick(lastClickTime: Long, action: (Long) -> Unit) {
        val now = System.currentTimeMillis()
        if (now - lastClickTime > 1500L) { action(now) }
    }
}

