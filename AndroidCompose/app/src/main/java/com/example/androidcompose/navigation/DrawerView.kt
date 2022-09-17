package com.example.androidcompose.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidcompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DrawerView {

    sealed class NavigationDrawerListView(var route: String, var icon: Int, var title: String) {

        object Project : NavigationDrawerListView(
            "Projects", R.drawable.ic_project, "Projects"
        )

        object Tasks : NavigationDrawerListView(
            "Tasks", R.drawable.ic_task, "Tasks"
        )

        object Learning : NavigationDrawerListView(
            "Learning", R.drawable.ic_learning, "Learning"
        )

        object Templates : NavigationDrawerListView(
            "Templates", R.drawable.ic_templates, "Templates"
        )

        object Releases : NavigationDrawerListView(
            "Releases", R.drawable.ic_product_release, "Releases"
        )

        object DiscussionBoard : NavigationDrawerListView(
            "DiscussionBoard", R.drawable.ic_discussion_board, "Discussion Boards"
        )

        object Article : NavigationDrawerListView(
            "Article", R.drawable.ic_article, "Articles"
        )

        object Events : NavigationDrawerListView(
            "Events", R.drawable.ic_event, "Events"
        )

    }

    @Composable
    fun NavigationDrawerView(
        scope: CoroutineScope,
        scaffoldState: ScaffoldState,
        navController: NavController
    ) {
        val items = listOf(
            NavigationDrawerListView.Project,
            NavigationDrawerListView.Tasks,
            NavigationDrawerListView.Learning,
            NavigationDrawerListView.Templates,
            NavigationDrawerListView.Releases,
            NavigationDrawerListView.DiscussionBoard,
            NavigationDrawerListView.Article,
            NavigationDrawerListView.Events,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.drawerGradientColorOne),
                            colorResource(id = R.color.drawerGradientColorTwo)
                        )
                    )
                )
        ) {
            // Header
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_round),
                contentDescription = R.mipmap.ic_launcher_round.toString(),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            // Space between
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            // List of navigation items
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
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
                    // Close drawer
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Designed for Project Management ",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

    @Preview(showBackground = false)
    @Composable
    fun DrawerPreview() {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val navController = rememberNavController()
        NavigationDrawerView(
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController
        )
    }

    @Composable
    fun DrawerItem(
        item: NavigationDrawerListView,
        selected: Boolean,
        onItemClick: (NavigationDrawerListView) -> Unit
    ) {

        val background = if (selected) R.color.drawerGradientColorOne
        else android.R.color.transparent

        Column() {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onItemClick(item) })
                    .height(48.dp)
                    .background(colorResource(id = background))
                    .padding(start = 16.dp)
            ) {

                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Divider(
                color = colorResource(id = android.R.color.white),
                thickness = 1.dp,
                startIndent = 24.dp,
                modifier = Modifier.padding(0.dp, 0.dp, 36.dp, 0.dp)
            )

        }


    }

    @Preview(showBackground = false)
    @Composable
    fun DrawerItemPreview() {
        DrawerItem(item = NavigationDrawerListView.Project, selected = false, onItemClick = {})
    }

    @Composable
    fun AddDrawerHeader(
        title: String,
        titleColor: Color = Color.Black,
    ) {
        Card(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, color = Color.Gray),
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = titleColor
                ),
                modifier = Modifier.padding(14.dp)
            )

        }
    }
}