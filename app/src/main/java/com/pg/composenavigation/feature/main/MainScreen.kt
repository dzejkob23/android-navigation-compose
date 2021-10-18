package com.pg.composenavigation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.navigation.NavNode
import com.pg.composenavigation.navigation.graphs.MainScreenNavHost
import com.pg.composenavigation.ui.theme.ComposeNavigationTheme

@Composable
fun MainScreen() {

    val navController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<NavNode.Root> = listOf(
        NavNode.Root.BottomItem1,
        NavNode.Root.BottomItem2,
        NavNode.Root.BottomItem3
    )

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
private fun List<NavNode>.showBottomBarOnScreens(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry
        ?.destination
        ?.hierarchy
        ?.any { navDestination ->
            this.map {
                    screen -> screen.route
            }.contains(navDestination.route)
        }
        ?: false
}

@Composable
private fun BottomNavigationBar(
    navController: NavHostController,
    bottomNavigationItems: List<NavNode.Root>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavigationItems.forEach { item ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = "Bottom navigation item: ${item.title}"
                    )
                },
                label = {
                    Text(text = item.title!!)
                },
                selected = currentDestination
                    ?.hierarchy
                    ?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    ComposeNavigationTheme {
        BottomNavigationBar(
            navController = rememberNavController(),
            bottomNavigationItems = listOf(
                NavNode.Root.BottomItem1,
                NavNode.Root.BottomItem2,
                NavNode.Root.BottomItem3
            )
        )
    }
}