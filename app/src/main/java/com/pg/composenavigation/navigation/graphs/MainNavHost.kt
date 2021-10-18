package com.pg.composenavigation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pg.composenavigation.feature.item1.Item1Content
import com.pg.composenavigation.feature.item2.Item2Content
import com.pg.composenavigation.feature.item3.Item3Content
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
        Item1Content()
    }
}

private fun NavGraphBuilder.addRoot2() {
    composable(Screen.MainScreen.Root2.route) {
        Item2Content()
    }
}

private fun NavGraphBuilder.addRoot3(navController: NavController) {
    composable(Screen.MainScreen.Root3.route) {
        Item3Content(
            onClickNext = { navController.navigate(Screen.FirstNestedGraph.TestingScreen1.route) }
        )
    }
}