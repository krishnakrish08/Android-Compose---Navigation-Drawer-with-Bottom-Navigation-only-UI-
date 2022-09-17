package com.example.androidcompose.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidcompose.R
import com.example.androidcompose.home.HomeScreen
import com.example.androidcompose.home.TaskDetailsCompose
import com.example.androidcompose.model.Screen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomNavigationView.Home.route) {
            HomeScreen(navController = navController)
        }

        //BottomNavigation
        composable(BottomNavigationView.Reports.route) {
            BottomNavigationComposeView().ReportsScreen()
        }
        composable(BottomNavigationView.Analytics.route) {
            BottomNavigationComposeView().AnalyticsScreen()
        }
        composable(BottomNavigationView.Settings.route) {
            BottomNavigationComposeView().SettingsScreen()
        }
        composable(BottomNavigationView.Profile.route) {
            BottomNavigationComposeView().ProfileScreen()
        }

        //Navigation Drawer
        composable(DrawerView.NavigationDrawerListView.Project.route) {
            NavigationDrawerComposeView().ProjectView()
        }
        composable(DrawerView.NavigationDrawerListView.Tasks.route) {
            NavigationDrawerComposeView().TaskView()
        }
        composable(DrawerView.NavigationDrawerListView.Learning.route) {
            NavigationDrawerComposeView().LearningView()
        }
        composable(DrawerView.NavigationDrawerListView.Templates.route) {
            NavigationDrawerComposeView().TemplatesView()
        }
        composable(DrawerView.NavigationDrawerListView.Releases.route) {
            NavigationDrawerComposeView().ReleasesView()
        }
        composable(DrawerView.NavigationDrawerListView.DiscussionBoard.route) {
            NavigationDrawerComposeView().DiscussionBoardView()
        }
        composable(DrawerView.NavigationDrawerListView.Article.route) {
            NavigationDrawerComposeView().ArticleView()
        }
        composable(DrawerView.NavigationDrawerListView.Events.route) {
            NavigationDrawerComposeView().EventsView()
        }

        //Grid Click Listener
        composable(Screen.TaskDetails.route) {
            TaskDetailsCompose().TaskDetailsView()
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavigationView.Home,
        BottomNavigationView.Reports,
        BottomNavigationView.Analytics,
        BottomNavigationView.Settings,
        BottomNavigationView.Profile
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_700),
        contentColor = Color.White
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselect the same item
                        launchSingleTop = true
                        // Restore state when reselect a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = NavController(LocalContext.current))
}