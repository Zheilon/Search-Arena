package com.zhei.search_arena
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.zhei.search_arena.core.di.depsContainer
import com.zhei.search_arena.core.di.LocalDeps
import com.zhei.search_arena.core.navigation.NavGraph
import com.zhei.search_arena.ui.theme.Search_arenaTheme

class MainActivity : ComponentActivity() {

    private val deps by lazy { depsContainer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CompositionLocalProvider(LocalDeps provides deps) {
                Search_arenaTheme { NavGraph() }
            }
        }
    }
}