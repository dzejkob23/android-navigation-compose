package com.pg.composenavigation.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.pg.composenavigation.navigation.Screen
import com.pg.composenavigation.navigation.utils.getCurrentRoute
import com.pg.composenavigation.ui.common.SimpleButtonContent

fun NavGraphBuilder.firstNestedGraph(navController: NavController) {
    navigation(
        startDestination = Screen.FirstNestedGraph.TestingScreen1.route,
        route = "FirstNestedGraph" // something like nested graph name/prefix
    ) {

        addTestingScreen1(navController)

        addTestingScreen2(navController)

        addTestingScreen3(navController)
    }
}

private fun NavGraphBuilder.addTestingScreen1(navController: NavController) {
    composable(Screen.FirstNestedGraph.TestingScreen1.route) {
        SimpleButtonContent(
            text = Screen.FirstNestedGraph.TestingScreen1.route,
            onClickNext = {
                val route =  Screen.FirstNestedGraph.TestingScreen2.createRoute(
                    requiredParam = "Hello World",
                    optionalParam = "Nice to meet you..."
                )
                navController.navigate(route)
            },
            onClickBack = { navController.popBackStack() },
            route = navController.getCurrentRoute()
        )
    }
}

private fun NavGraphBuilder.addTestingScreen2(navController: NavController) {
    composable(
        route = Screen.FirstNestedGraph.TestingScreen2.route,
        arguments = listOf(
            navArgument(Screen.FirstNestedGraph.TestingScreen2.required) {
                type = NavType.StringType
            },
            navArgument(Screen.FirstNestedGraph.TestingScreen2.optional) {
                type = NavType.StringType
                defaultValue = "NOTHING" // "defaultValue" must be set for optional parameter or set "nullability = true"
            }
        )
    ) { backStackEntry ->

        val requiredParam = backStackEntry.arguments?.getString(
            Screen.FirstNestedGraph.TestingScreen2.required
        )
        val optionalParam = backStackEntry.arguments?.getString(
            Screen.FirstNestedGraph.TestingScreen2.optional
        )

        SimpleButtonContent(
            text = "Title: ${Screen.FirstNestedGraph.TestingScreen2.route}\n\n" +
                    "Required parameter: $requiredParam\n" +
                    "Optional parameter: $optionalParam",
            onClickNext = { navController.navigate(Screen.FirstNestedGraph.TestingScreen3.route) },
            onClickBack = { navController.popBackStack() },
            route = navController.getCurrentRoute()
        )
    }
}

private fun NavGraphBuilder.addTestingScreen3(navController: NavController) {
    composable(route = Screen.FirstNestedGraph.TestingScreen3.route) {
        SimpleButtonContent(
            text = Screen.FirstNestedGraph.TestingScreen3.route,
            onClickNext = {
                // Navigate back to the third bottom tab bar.
                navController.navigate(Screen.MainScreen.Root3.route) {
                    // Add navigation properties.
                    // Pop up to third bottom tab bar.
                    popUpTo(Screen.MainScreen.Root3.route) {
                        // Pop up the tab bar from the back stack.
                        inclusive = true
                    }
                }
            },
            onClickBack = { navController.popBackStack() },
            route = navController.getCurrentRoute()
        )
    }
}