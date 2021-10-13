package com.pg.composenavigation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
}

fun NavGraphBuilder.firstNestedGraph(navController: NavController) {
    navigation(
        startDestination = FirstNestedGraph.FirstScreen.getRoute(),
        // route == prefix
        // if the graph continue in scaffold with bottom navigation bar, this helps to keep
        // the bottom bar navigation icon selected
        route = MainNavHost.BottomNavItem3.getRoute(), // this keeps third bottom bar icon selected
    ) {
        composable(FirstNestedGraph.FirstScreen.getRoute()) {
            SimpleButtonContent(
                text = FirstNestedGraph.FirstScreen.getTitle(),
                onClickNext = { navController.navigate(FirstNestedGraph.SecondScreen.getRoute()) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }
        composable(FirstNestedGraph.SecondScreen.getRoute()) {
            SimpleButtonContent(
                text = FirstNestedGraph.SecondScreen.getTitle(),
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