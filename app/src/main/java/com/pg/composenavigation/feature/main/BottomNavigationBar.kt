package com.pg.composenavigation.feature.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pg.composenavigation.navigation.Screen
import com.pg.composenavigation.ui.theme.ComposeNavigationTheme

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    bottomNavigationItems: List<Screen.BottomNavigation>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavigationItems.forEach { screen ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Bottom navigation item: ${screen.title}"
                    )
                },
                label = {
                    Text(text = screen.title)
                },
                selected = currentDestination
                    ?.hierarchy
                    ?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
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
                Screen.BottomNavigation.BottomNavItem1,
                Screen.BottomNavigation.BottomNavItem2,
                Screen.BottomNavigation.BottomNavItem3
            )
        )
    }
}