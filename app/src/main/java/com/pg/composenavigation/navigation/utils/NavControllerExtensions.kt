package com.pg.composenavigation.navigation.utils

import androidx.navigation.NavController

fun NavController.getCurrentRoute(): String {
    return currentBackStackEntry?.arguments?.toString() ?: "NOTHING"
}