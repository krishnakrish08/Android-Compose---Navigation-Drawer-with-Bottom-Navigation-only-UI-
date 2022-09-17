package com.example.androidcompose.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidcompose.R
import com.example.androidcompose.navigation.BottomNavigationBar
import com.example.androidcompose.navigation.DrawerView
import com.example.androidcompose.navigation.SetupNavGraph
import com.example.androidcompose.ui.theme.AndroidComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeComposeActivity : ComponentActivity() {

    private val textColorString = "#008080"
    private val themeColorString = "#3CB5A1"

    private val textColorInt = Color(textColorString.toColorInt())
    private val themeColorInt = Color(themeColorString.toColorInt())

    //  navigation - https://stackoverflow.com/questions/70056705/how-to-navigate-from-single-screen-to-bottom-screenscreen-b-with-bottomnavigati
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

            val coroutineScope = rememberCoroutineScope()

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        themeColorInt = themeColorInt,
                        coroutineScope,
                        scaffoldState
                    )
                },
                bottomBar = { BottomNavigationBar(navController) },
                drawerContent = {
                    DrawerView().NavigationDrawerView(
                        scope = coroutineScope,
                        scaffoldState = scaffoldState,
                        navController = navController
                    )
                }

            ) {
                AndroidComposeTheme {
                    SetupNavGraph(navController = navController)
                }
            }

        }
    }
}

@Composable
fun TopAppBar(themeColorInt: Color, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = themeColorInt,
        contentColor = Color.White,
        elevation = 12.dp,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
