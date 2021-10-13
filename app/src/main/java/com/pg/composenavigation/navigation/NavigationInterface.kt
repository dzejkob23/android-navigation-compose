package com.pg.composenavigation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

interface NavigationDestination {

    fun getRoute(): String

    fun getTitle(): String

}

interface BottomNavigationDestination : NavigationDestination {

    fun getIcon(): ImageVector

}