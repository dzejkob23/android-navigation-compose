package com.pg.composenavigation.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.pg.composenavigation.navigation.NavigationDestination
import com.pg.composenavigation.navigation.utils.getCurrentRoute
import com.pg.composenavigation.ui.common.SimpleButtonContent

enum class FirstNestedGraph : NavigationDestination {

    FirstScreen {
        override fun getTitle(): String = "First Screen"
    },
    SecondScreen {
        override fun getTitle(): String  = "Second Screen"
    },
    ThirdScreen {
        override fun getTitle(): String  = "Third Screen"
    };

    override fun getRoute(): String = name

    companion object {
        const val graphId = "FirstNestedGraph"
    }
}

fun NavGraphBuilder.firstNestedGraph(navController: NavController) {
    navigation(
        startDestination = FirstNestedGraph.FirstScreen.getRoute(),
        // route == prefix
        // if the graph continue in scaffold with bottom navigation bar, this helps to keep
        // the bottom bar navigation icon selected
        route = FirstNestedGraph.graphId
    ) {
        composable(FirstNestedGraph.FirstScreen.getRoute()) {

            val routeToNextScreen = FirstNestedGraph.SecondScreen.getRoute() +
                    "/Hello World" +
                    "?optionalParameter=Nice to meet you..."

            SimpleButtonContent(
                text = FirstNestedGraph.FirstScreen.getTitle(),
                onClickNext = { navController.navigate(routeToNextScreen) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
        composable(
            route = "${FirstNestedGraph.SecondScreen.getRoute()}/{requiredParameter}?optionalParameter={optionalParameter}",
            arguments = listOf(
                navArgument("requiredParameter") {
                    type = NavType.StringType
                },
                navArgument("optionalParameter") {
                    type = NavType.StringType
                    defaultValue = "NOTHING" // "defaultValue" must be set for optional parameter or set "nullability = true"
                }
            )
        ) { backStackEntry ->

            val requiredParam = backStackEntry.arguments?.getString("requiredParameter")
            val optionalParam = backStackEntry.arguments?.getString("optionalParameter")

            SimpleButtonContent(
                text = "Title: ${FirstNestedGraph.SecondScreen.getTitle()}\n" +
                        "Required parameter: $requiredParam\n" +
                        "Optional parameter: $optionalParam",
                onClickNext = { navController.navigate(FirstNestedGraph.ThirdScreen.getRoute()) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
        composable(route = FirstNestedGraph.ThirdScreen.getRoute()) {
            SimpleButtonContent(
                text = FirstNestedGraph.ThirdScreen.getTitle(),
                onClickNext = {
                    // Navigate back to the third bottom tab bar.
                    navController.navigate(MainNavHost.BottomNavItem3.getRoute()) {
                        // Add navigation properties.
                        // Pop up to third bottom tab bar.
                        popUpTo(MainNavHost.BottomNavItem3.getRoute()) {
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
}