package com.zhei.search_arena.core.navigation
import android.transition.Scene
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
import com.zhei.search_arena.features.solo_arena.presentation.SoloArenaUI
import com.zhei.search_arena.features.solo_arena.presentation.SoloArenaVM
import com.zhei.search_arena.features.solo_arena_load.presentation.SoloArenaLoadUI


@Composable fun NavGraph() {

    val mainArenaVM = viewModel<MainArenaVM>(factory = LocalDeps.current.depSA1)
    val soloArenaVM = viewModel<SoloArenaVM>(factory = LocalDeps.current.depSA3)

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

        composable<Scenes.SoloArenaScene> {

            SoloArenaUI(
                vm = soloArenaVM,
                navigate = { nav.navigate(Scenes.SoloArenaLoadScene) { launchSingleTop = true } }
            )
        }

        composable<Scenes.SoloArenaLoadScene> {

            SoloArenaLoadUI(
                navigation = {}
            )
        }
    }

}