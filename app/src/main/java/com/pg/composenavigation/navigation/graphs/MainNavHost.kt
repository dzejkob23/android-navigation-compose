package com.pg.composenavigation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pg.composenavigation.navigation.Screen
import com.pg.composenavigation.navigation.utils.getCurrentRoute
import com.pg.composenavigation.ui.common.SimpleButtonContent
import com.pg.composenavigation.ui.common.SimpleContent

@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    // This NavHost is similar to the FragmentNavHost.
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.Root1.route,
        modifier = modifier
    ) {
        // Each "composable" is definition of a route to the current screen (content).
        // Definition 1
        addRoot1()

        // Definition 2
        addRoot2()

        // Definition 3
        addRoot3(navController)

        // Nested navigation graph
        firstNestedGraph(navController)
    }
}

private fun NavGraphBuilder.addRoot1() {
    composable(Screen.MainScreen.Root1.route) {
        SimpleContent("Item 1")
    }
}

private fun NavGraphBuilder.addRoot2() {
    composable(Screen.MainScreen.Root2.route) {
        SimpleContent("Item 2")
    }
}

private fun NavGraphBuilder.addRoot3(navController: NavController) {
    composable(Screen.MainScreen.Root3.route) {
        SimpleButtonContent(
            text = "Item 3",
            onClickNext = { navController.navigate(Screen.FirstNestedGraph.TestingScreen1.route) },
            onClickBack = { navController.popBackStack() },
            route = navController.getCurrentRoute()
        )
    }
}