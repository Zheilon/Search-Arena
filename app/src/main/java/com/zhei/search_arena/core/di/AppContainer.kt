package com.zhei.search_arena.core.di
import android.content.Context
import com.zhei.search_arena.features.create_profile.data.remote.repository.CreateProfileRepository
import com.zhei.search_arena.features.create_profile.domain.usecases.UseCaseCreatePlayer
import com.zhei.search_arena.features.create_profile.presentation.CreateProfileFactory
import com.zhei.search_arena.features.main_arena.presentation.MainArenaFactory


/**
 * Construye y provee el contenedor de dependencias de la aplicación.
 *
 * Esta función actúa como el punto central de inicialización del grafo de dependencias
 * (DI manual), instanciando los componentes necesarios para la capa de presentación
 * y exponiéndolos mediante [SearchArenaDeps].
 *
 * Actualmente inicializa factories de ViewModels, pero está diseñada para escalar
 * incluyendo repositorios, casos de uso y data sources en el futuro.
 *
 * @param context Contexto de la aplicación, útil para inicializar dependencias
 * que requieran acceso a recursos del sistema (por ejemplo: base de datos, preferences, etc.).
 *
 * @return Instancia de [SearchArenaDeps] con las dependencias listas para ser inyectadas.
 */
fun depsContainer(context: Context): SearchArenaDeps {

    // * ---- Carga de Repositorios -------------------------------- *
    val createProfileRepo = CreateProfileRepository()

    // * ---- Carga de Use Cases -------------------------------- *
    val useCaseCreatePlayer = UseCaseCreatePlayer(createProfileRepo = createProfileRepo)

    // * ---- Carga de factories -------------------------------- *
    val mainArenaFact = MainArenaFactory()
    val createProfileFact = CreateProfileFactory(
        createUserID = useCaseCreatePlayer
    )

    return SearchArenaDeps(
        depSA1 = mainArenaFact,
        depSA2 = createProfileFact
    )
}