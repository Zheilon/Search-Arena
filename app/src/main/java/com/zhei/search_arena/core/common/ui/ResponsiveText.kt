package com.zhei.search_arena.core.common.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

object ResponsiveText {

    /**
     * Indica si el dispositivo posee un ancho reducido.
     * Utilizado para ajustar tamaños responsivos.
     * */
    @Composable
    fun isSmallDevice(): Boolean =
        LocalConfiguration.current.screenWidthDp <= 365


    /**
     * Tamaño destinado para títulos principales
     * o elementos visualmente dominantes.
     * */
    @Composable fun displayLarge(): TextUnit =
        if (isSmallDevice()) 28.sp else 32.sp


    /**
     * Tamaño utilizado en encabezados
     * o secciones importantes.
     * */
    @Composable fun headline(): TextUnit =
        if (isSmallDevice()) 23.sp else 27.sp


    /**
     * Tamaño pensado para títulos
     * secundarios dentro de la interfaz.
     * */
    @Composable fun title(): TextUnit =
        if (isSmallDevice()) 17.sp else 19.sp


    /**
     * Tamaño utilizado para texto
     * descriptivo o de contenido general.
     * */
    @Composable fun body(): TextUnit =
        if (isSmallDevice()) 14.sp else 16.sp


    /**
     * Tamaño destinado al texto
     * mostrado en botones.
     * */
    @Composable fun button(): TextUnit =
        if (isSmallDevice()) 12.sp else 15.sp


    /**
     * Tamaño utilizado para textos
     * pequeños o información auxiliar.
     * */
    @Composable fun caption(): TextUnit =
        if (isSmallDevice()) 10.sp else 11.sp
}