package com.pg.composenavigation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pg.composenavigation.navigation.BottomNavigationDestination
import com.pg.composenavigation.navigation.utils.getCurrentRoute
import com.pg.composenavigation.ui.common.SimpleButtonContent
import com.pg.composenavigation.ui.common.SimpleContent

enum class MainNavHost(
    val itemTitle: String,
    val itemIcon: ImageVector
) : BottomNavigationDestination {

    BottomNavItem1(
        itemTitle = "Item 1",
        itemIcon = Icons.Default.Add
    ),
    BottomNavItem2(
        itemTitle = "Item 2",
        itemIcon = Icons.Default.Build
    ),
    BottomNavItem3(
        itemTitle = "Item 3",
        itemIcon = Icons.Default.Call
    );

    override fun getRoute(): String = name

    override fun getIcon(): ImageVector = itemIcon

    override fun getTitle(): String = itemTitle
}

@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    // This NavHost is similar to the FragmentNavHost.
    NavHost(
        navController = navController,
        startDestination = MainNavHost.BottomNavItem1.getRoute(),
        modifier = modifier
    ) {
        // Each "composable" is definition of a route to the current screen (content).
        // Definition 1
        composable(MainNavHost.BottomNavItem1.getRoute()) {
            SimpleContent(MainNavHost.BottomNavItem1.getTitle())
        }
        // Definition 2
        composable(MainNavHost.BottomNavItem2.getRoute()) {
            SimpleContent(MainNavHost.BottomNavItem2.getTitle())
        }
        // Definition 3
        composable(MainNavHost.BottomNavItem3.getRoute()) {
            SimpleButtonContent(
                text = MainNavHost.BottomNavItem3.getTitle(),
                onClickNext = { navController.navigate(FirstNestedGraph.FirstScreen.getRoute()) },
                onClickBack = { navController.popBackStack() },
                route = navController.getCurrentRoute()
            )
        }

        // Nested navigation graph
        firstNestedGraph(navController)
    }
}