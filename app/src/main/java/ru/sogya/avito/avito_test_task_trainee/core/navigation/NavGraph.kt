package ru.sogya.avito.avito_test_task_trainee.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.sogya.avito.avito_test_task_trainee.film.ui.MovieScreen
import ru.sogya.avito.avito_test_task_trainee.home.ui.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "HomeScreen"
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "HomeScreen") {
            HomeScreen(
                onNavigateToMovieScreen = { movieId ->
                    navController.navigate(route = "MovieScreen/$movieId")
                }
            )
        }
        composable(
            route = "MovieScreen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            MovieScreen(backStackEntry.arguments?.getInt("movieId")) {
                navController.popBackStack()
            }
        }
    }
}