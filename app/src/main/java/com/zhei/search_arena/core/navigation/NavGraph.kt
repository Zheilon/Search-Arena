package com.zhei.search_arena.core.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhei.search_arena.feature_inital_profile.presentation.CreateProfileUI


@Composable fun NavGraph() {

    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Scenes.InitScene
    ) {

        composable<Scenes.InitScene> {
            CreateProfileUI()
        }

    }

}