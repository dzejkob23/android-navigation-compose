package com.pg.composenavigation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.navigation.graphs.MainNavHost
import com.pg.composenavigation.navigation.graphs.MainScreenNavHost

@Composable
fun MainScreen() {

    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                bottomNavigationItems = MainNavHost.values().toList()
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
