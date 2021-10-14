package com.pg.composenavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class Screen(val route: String) {

    sealed class MainScreen(route: String) : Screen(route) {

        object Root1 : MainScreen("root1")
        object Root2 : MainScreen("root2")
        object Root3 : MainScreen("root3")

    }

    sealed class FirstNestedGraph(route: String) : Screen(route) {

        object TestingScreen1 : FirstNestedGraph("testscreen1")
        object TestingScreen2 : FirstNestedGraph("testscreen2/{requiredParameter}?optionalParameter={optionalParameter}") {

            const val required = "requiredParameter"
            const val optional = "optionalParameter"

            fun createRoute(requiredParam: String, optionalParam: String?): String {
                return "${TestingScreen2.route}/$requiredParam?$optional=$optionalParam"
            }
        }
        object TestingScreen3 : FirstNestedGraph("testscreen3")

    }

}

enum class MainNavHost(
    val itemTitle: String,
    val itemIcon: ImageVector
) {

    BottomNavItem1(
        itemTitle = "Item 1",
        itemIcon = Icons.Default.Add
    ) {
        override fun getRoute(): String = Screen.MainScreen.Root1.route
    },
    BottomNavItem2(
        itemTitle = "Item 2",
        itemIcon = Icons.Default.Build
    ) {
        override fun getRoute(): String = Screen.MainScreen.Root2.route
    },
    BottomNavItem3(
        itemTitle = "Item 3",
        itemIcon = Icons.Default.Call
    ) {
        override fun getRoute(): String = Screen.MainScreen.Root3.route
    };

    fun getIcon(): ImageVector = itemIcon

    fun getTitle(): String = itemTitle

    abstract fun getRoute(): String
}