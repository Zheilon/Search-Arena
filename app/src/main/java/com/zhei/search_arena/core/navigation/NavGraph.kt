package com.zhei.search_arena.core.navigation
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhei.search_arena.features.create_profile.presentation.CreateProfileUI
import com.zhei.search_arena.features.main_arena.presentation.MainArenaUI


@Composable fun NavGraph() {

    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Scenes.CreateUserScene
    ) {

        composable<Scenes.CreateUserScene> (
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(800)
                )
            }
        ) {
            CreateProfileUI{
                nav.navigate(Scenes.MainArenaScene)
            }
        }

        composable<Scenes.MainArenaScene> {
            MainArenaUI {}
        }

    }

}