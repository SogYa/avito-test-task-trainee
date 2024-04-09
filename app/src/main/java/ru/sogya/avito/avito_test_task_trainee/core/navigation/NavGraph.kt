package ru.sogya.avito.avito_test_task_trainee.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            HomeScreen()
        }
        composable(route = "InfoScreen") {
//            EnterScreenContent(
//                onNavigateToSignIn = { email ->
//                    navController.navigate(route = "signIn/$email")
//                },
//                onNavigateToRegistration = { email ->
//                    navController.navigate(route = "registration/$email")
//                }
//            )
        }
    }
}