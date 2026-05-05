package com.zhei.search_arena.core.factories
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Clase encargada de crear factories genericas que pueda usar eficasmente otras clases
 * la cual recibe un Tipo ViewModel Implementa ViewModelProvider.Factory.
 * Mediante la función create, esta retorna el callback: createViewModel() casteandolo como un tipo (T)
 * */
abstract class BaseFactoryVM <VM: ViewModel> (private val create: () -> VM): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = create() as T
}