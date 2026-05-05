package com.zhei.search_arena.core.common.ui
import android.app.Activity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat


/**
 * Retorna el padding superior o inferior basado en los system bars (status bar + navigation bar).
 *
 * @param type Define qué padding calcular:
 *             - "top" → padding superior (status bar)
 *             - cualquier otro valor → padding inferior (navigation bar)
 *
 * @return Dp correspondiente al padding solicitado
 *
 * Nota:
 * - Usa WindowInsets.systemBars para adaptarse a dispositivos con notch o barras dinámicas.
 * - El parámetro `type` es frágil (String). Idealmente usar enum o constantes para evitar errores.
 */
@Composable fun getTopOrBottomPadding(type: String): Dp =
    if (type == "top") WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
    else WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()


/**
 * Modifica dinámicamente el color de la status bar y el estilo de sus íconos.
 *
 * @param color Color de fondo de la status bar
 * @param darkIcons true → íconos oscuros (para fondos claros)
 *                  false → íconos claros (para fondos oscuros)
 *
 * Funcionamiento:
 * - Obtiene la ventana actual desde el contexto
 * - Usa SideEffect para aplicar cambios fuera del ciclo de composición
 * - Cambia:
 *      1. Color de la status bar
 *      2. Apariencia de los íconos (light/dark)
 *
 * Nota:
 * - Solo funciona si el contexto es una Activity
 * - SideEffect evita recomposiciones innecesarias
 */
@Composable
fun PaintMyStatusBar(color: Color, darkIcons: Boolean) {
    val view = LocalView.current

    val window = (view.context as Activity).window

    SideEffect {
        window.statusBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = darkIcons
    }
}