package com.hfad.vkeduc

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hfad.vkeduc.presentation.AppList
import com.hfad.vkeduc.presentation.appdetails.AppDetailsScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.APP_LIST,
        modifier = modifier
    ) {
        composable(Routes.APP_LIST) {
            AppList(
                onAppClick = { app ->
                    navController.navigate(Routes.appDetails(app.id))
                }
            )
        }

        composable(
            route = Routes.APP_DETAILS,
            arguments = listOf(
                navArgument("appId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId").orEmpty()

            AppDetailsScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = {
                    // Возврат к списку
                    navController.popBackStack(Routes.APP_LIST, inclusive = false)
                }
            )
        }
    }
}