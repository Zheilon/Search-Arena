package com.zhei.search_arena.core.di
import android.content.Context
import com.zhei.search_arena.feature_inital_profile.data.repository.CreateProfileRepository
import com.zhei.search_arena.feature_inital_profile.domain.usecases.UseCaseCreatePlayer
import com.zhei.search_arena.feature_inital_profile.presentation.CreateProfileFactory
import com.zhei.search_arena.feature_select_rol.presentation.SelectRolFactory


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
    val selectRolFact = SelectRolFactory()
    val createProfileFact = CreateProfileFactory(
        createProfileRepository = createProfileRepo,
        createUserID = useCaseCreatePlayer
    )

    return SearchArenaDeps(
        depSA1 = selectRolFact,
        depSA2 = createProfileFact
    )
}