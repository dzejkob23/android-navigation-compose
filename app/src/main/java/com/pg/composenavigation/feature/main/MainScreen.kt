package com.pg.composenavigation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.navigation.MainNavHost
import com.pg.composenavigation.navigation.graphs.MainScreenNavHost

@Composable
fun MainScreen() {

    val navController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<MainNavHost> = MainNavHost.values().toList()

    Scaffold(
        bottomBar = {
            if (bottomNavigationItems.showBottomBarOnScreens(navController)) {
                BottomNavigationBar(
                    navController = navController,
                    bottomNavigationItems = bottomNavigationItems
                )
            }
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
private fun List<MainNavHost>.showBottomBarOnScreens(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return this
        .filter { it.getRoute() == navBackStackEntry?.destination?.route }
        .isNullOrEmpty()
        .not()
}
