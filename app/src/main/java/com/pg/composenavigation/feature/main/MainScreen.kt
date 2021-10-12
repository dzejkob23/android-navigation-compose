package com.pg.composenavigation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.pg.composenavigation.navigation.Screen
import com.pg.composenavigation.ui.common.SimpleButtonContent
import com.pg.composenavigation.ui.common.SimpleContent

@Composable
fun MainScreen() {

    val navController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<Screen.BottomNavigation> = listOf(
        Screen.BottomNavigation.BottomNavItem1,
        Screen.BottomNavigation.BottomNavItem2,
        Screen.BottomNavigation.BottomNavItem3
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                bottomNavigationItems = bottomNavigationItems
            )
        }
    ) {
        MainScreenNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
            // bottom navbar padding is included in "it: PaddingValues"
        )
    }
}

@Composable
private fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    // This NavHost is similar to the FragmentNavHost.
    NavHost(
        navController = navController,
        startDestination = Screen.BottomNavigation.BottomNavItem1.route,
        modifier = modifier
    ) {
        // Each "composable" is definition of a route to the current screen (content).
        // Definition 1
        composable(Screen.BottomNavigation.BottomNavItem1.route) {
            SimpleContent(Screen.BottomNavigation.BottomNavItem1.title)
        }
        // Definition 2
        composable(Screen.BottomNavigation.BottomNavItem2.route) {
            SimpleContent(Screen.BottomNavigation.BottomNavItem2.title)
        }
        // Definition 3
        composable(Screen.BottomNavigation.BottomNavItem3.route) {
            SimpleButtonContent(
                text = Screen.BottomNavigation.BottomNavItem3.title,
                onClickNext = { navController.navigate(screens[0].route) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }

        // Nested navigation graph
        nestedGraph(navController)
    }
}

fun NavGraphBuilder.nestedGraph(navController: NavController) {
    navigation(
        startDestination = screens[0].route,
        // route == prefix
        // if the graph continue in scaffold with bottom navigation bar, this helps to keep
        // the bottom bar navigation icon selected
        route = Screen.BottomNavigation.BottomNavItem3.route, // this keeps third bottom bar icon selected
    ) {
        composable(screens[0].route) {
            SimpleButtonContent(
                text = screens[0].title,
                onClickNext = { navController.navigate(screens[1].route) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
        composable(screens[1].route) {
            SimpleButtonContent(
                text = screens[1].title,
                onClickNext = { navController.navigate(screens[2].route) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
        composable(route = screens[2].route) {
            SimpleButtonContent(
                text = screens[2].title,
                onClickNext = {
                    // Navigate back to the third bottom tab bar. Also remove the backstack.
                    navController.navigate(Screen.BottomNavigation.BottomNavItem3.route) {
                        // Add navigation properties
                        popUpTo(Screen.BottomNavigation.BottomNavItem3.route) {
                            inclusive = true
                        }
                    }
                },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
    }
}

private fun NavController.getCurrentRoute(): String {
    return currentBackStackEntry?.arguments?.toString() ?: "NOTHING"
}

private val screens: List<Screen.Content> = listOf(
    Screen.Content("TestContent1", "Test content 1"),
    Screen.Content("TestContent2", "Test content 2"),
    Screen.Content("TestContent3", "Test content 3")
)
