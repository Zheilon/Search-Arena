package com.zhei.search_arena.core.navigation
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhei.search_arena.core.di.LocalDeps
import com.zhei.search_arena.features.create_profile.presentation.CreateProfileUI
import com.zhei.search_arena.features.main_arena.presentation.MainArenaUI
import com.zhei.search_arena.features.main_arena.presentation.MainArenaVM


@Composable fun NavGraph() {

    val mainArenaVM = viewModel<MainArenaVM>(factory = LocalDeps.current.depSA1)

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
            CreateProfileUI(
                navigate = { nav.navigate(Scenes.MainArenaScene) { launchSingleTop = true } }
            )
        }

        composable<Scenes.MainArenaScene> {

            MainArenaUI(
                vm = mainArenaVM,
                onSelectedMode = {
                    when (it) {
                        "S" -> nav.navigate(Scenes.SoloArenaScene) { launchSingleTop = true }
                        "CS" -> nav.navigate(Scenes.CreateArenaScene) { launchSingleTop = true }
                        "US" -> nav.navigate(Scenes.JoinToArenaScene) { launchSingleTop = true }
                    }
                }
            )
        }
    }

}