package com.pg.composenavigation.feature.nestedGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.navigation.Screen
import com.pg.composenavigation.ui.common.SimpleButtonContent
import com.pg.composenavigation.ui.common.SimpleContent

@Composable
fun NestedGraph() {

    val navController: NavHostController = rememberNavController()

    Scaffold {
        NestedGraphNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun NestedGraphNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = screens[0].route,
        modifier = modifier
    ) {
        composable(screens[0].route) {
            SimpleButtonContent(
                text = screens[0].title,
                onClickNext = { navController.navigate(screens[1].route) },
                onClickBack = {}
            )
        }
        composable(screens[1].route) {
            SimpleButtonContent(
                text = screens[1].title,
                onClickNext = { navController.navigate(screens[2].route) },
                onClickBack = { navController.currentBackStackEntry }
            )
        }
        composable(screens[2].route) {
            SimpleContent(screens[2].title)
        }
    }
}


