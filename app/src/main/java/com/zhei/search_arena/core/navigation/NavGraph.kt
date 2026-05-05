package com.zhei.search_arena.core.navigation
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhei.search_arena.feature_inital_profile.presentation.SetInitProfileUI


@Composable fun NavGraph() {

    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Scenes.InitScene
    ) {

        composable<Scenes.InitScene> {
            SetInitProfileUI()
        }

    }

}