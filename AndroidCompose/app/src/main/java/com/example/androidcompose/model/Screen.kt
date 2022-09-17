package com.example.androidcompose.model

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object TaskDetails : Screen("task_details")
}