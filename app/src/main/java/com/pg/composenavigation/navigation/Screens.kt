package com.pg.composenavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
) {
    BottomNavItem1(
        title = "Item 1",
        icon = Icons.Default.Add
    ),
    BottomNavItem2(
        title = "Item 2",
        icon = Icons.Default.Build
    ),
    BottomNavItem3(
        title = "Item 3",
        icon = Icons.Default.Call
    )
}

interface Screen {

    val route: String
    val title: String

    sealed class BottomNavigation(
        val icon: ImageVector,
        override val route: String,
        override val title: String
    ) : Screen {

        object BottomNavItem1 : BottomNavigation(
            BottomNavigationItem.BottomNavItem1.icon,
            BottomNavigationItem.BottomNavItem1.name,
            BottomNavigationItem.BottomNavItem1.title,
        )
        object BottomNavItem2 : BottomNavigation(
            BottomNavigationItem.BottomNavItem2.icon,
            BottomNavigationItem.BottomNavItem2.name,
            BottomNavigationItem.BottomNavItem2.title,
        )
        object BottomNavItem3 : BottomNavigation(
            BottomNavigationItem.BottomNavItem3.icon,
            BottomNavigationItem.BottomNavItem3.name,
            BottomNavigationItem.BottomNavItem3.title,
        )

    }

//    data class Content(
//        override val route: String,
//        override val title: String
//    ) : Screen
}