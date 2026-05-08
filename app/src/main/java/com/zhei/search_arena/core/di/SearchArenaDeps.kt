package com.zhei.search_arena.core.di
import com.zhei.search_arena.features.create_profile.presentation.CreateProfileFactory
import com.zhei.search_arena.features.main_arena.presentation.MainArenaFactory
import com.zhei.search_arena.features.main_arena.presentation.MainArenaVM


/**
 * Contenedor de dependencias de la aplicación (DI manual).
 *
 * Esta clase agrupa y expone las dependencias necesarias para las distintas
 * features de la aplicación, principalmente en la capa de presentación.
 *
 * Es utilizada junto con CompositionLocal para proveer acceso a factories,
 * repositorios y otros componentes sin acoplar directamente la UI a sus
 * implementaciones concretas.
 *
 * A medida que la aplicación escale, este contenedor puede ampliarse para
 * incluir nuevas dependencias compartidas entre features.
 *
 * @property depSA1 Factory encargada de crear instancias del
 * [MainArenaVM] para la feature de selección de rol.
 */
data class SearchArenaDeps (
    val depSA1: MainArenaFactory,
    val depSA2: CreateProfileFactory

)