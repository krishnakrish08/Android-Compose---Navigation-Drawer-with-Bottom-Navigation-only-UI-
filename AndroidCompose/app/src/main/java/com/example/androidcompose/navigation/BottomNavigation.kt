package com.example.androidcompose.navigation

import com.example.androidcompose.R

sealed class BottomNavigationView(var route: String, var icon: Int, var title: String) {

    object Home : BottomNavigationView("home", R.drawable.ic_baseline_home_24, "Home")

    object Reports : BottomNavigationView("reports", R.drawable.ic_reporticon, "Reports")

    object Analytics : BottomNavigationView(
        "analytics", R.drawable.ic_baseline_analytics_24, "Analytics"
    )

    object Settings : BottomNavigationView(
        "settings", R.drawable.ic_baseline_settings_24, "Settings"
    )

    object Profile : BottomNavigationView(
        "profile", R.drawable.ic_baseline_account_circle_24, "Profile"
    )
}