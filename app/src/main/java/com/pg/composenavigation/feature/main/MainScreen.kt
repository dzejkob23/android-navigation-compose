package com.pg.composenavigation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.feature.bottomNavItem1.BottomNavContent1
import com.pg.composenavigation.feature.bottomNavItem2.BottomNavContent2
import com.pg.composenavigation.feature.bottomNavItem3.BottomNavContent3
import com.pg.composenavigation.navigation.Screen

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
    NavHost(
        navController = navController,
        startDestination = Screen.BottomNavigation.BottomNavItem1.route,
        modifier = modifier
    ) {
        composable(Screen.BottomNavigation.BottomNavItem1.route) {
            BottomNavContent1()
        }
        composable(Screen.BottomNavigation.BottomNavItem2.route) {
            BottomNavContent2()
        }
        composable(Screen.BottomNavigation.BottomNavItem3.route) {
            BottomNavContent3()
        }
    }
}