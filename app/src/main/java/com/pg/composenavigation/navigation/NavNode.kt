package com.pg.composenavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class NavNode(val route: String) {

    sealed class Root(
        route: String,
        val icon: ImageVector? = null,
        val title: String? = null
    ) : NavNode(route) {

        // Root of graph with bottom navigation bar
        object BottomItem1 : Root(
            route = "root1",
            icon = Icons.Default.Add,
            title = "Item 1"
        )
        object BottomItem2 : Root(
            route = "root2",
            icon = Icons.Default.Build,
            title = "Item 2"
        )
        object BottomItem3 : Root(
            route = "root3",
            icon = Icons.Default.Call,
            title = "Item 3"
        )

        // Other root screens
        object Item3NestedGraphRoot : Root("item3nestedgraphroot")
    }

    sealed class Item3NestedGraph(route: String) : NavNode(route) {

        object TestingScreen1 : Item3NestedGraph("testscreen1")

        object TestingScreen2 : Item3NestedGraph("testscreen2/{requiredParameter}?optionalParameter={optionalParameter}") {

            const val required = "requiredParameter"
            const val optional = "optionalParameter"

            fun createRoute(requiredParam: String, optionalParam: String?): String {
                return "${TestingScreen2.route}/$requiredParam?$optional=$optionalParam"
            }
        }
    }
}