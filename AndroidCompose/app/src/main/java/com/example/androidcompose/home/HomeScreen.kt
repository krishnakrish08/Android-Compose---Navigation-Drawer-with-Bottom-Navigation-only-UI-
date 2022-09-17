package com.example.androidcompose.home

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidcompose.R
import com.example.androidcompose.model.Screen
import com.example.androidcompose.model.Task

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    val localFocusManager = LocalFocusManager.current

    val lazyListState = rememberLazyListState()

    val activityTaskArray = ArrayList<Task>()
    activityTaskArray.add(Task("Meeting", R.drawable.ic_baseline_people_24))
    activityTaskArray.add(Task("Discuss", R.drawable.ic_baseline_chat_24))
    activityTaskArray.add(Task("Design", R.drawable.ic_baseline_design_services_24))
    activityTaskArray.add(Task("Development", R.drawable.ic_baseline_developer_board_24))
    activityTaskArray.add(Task("Testing", R.drawable.ic_baseline_library_add_check_24))
    activityTaskArray.add(Task("Product", R.drawable.ic_baseline_filter_vintage_24))
    activityTaskArray.add(Task("QA", R.drawable.ic_baseline_settings_24))
    activityTaskArray.add(Task("Playing", R.drawable.ic_baseline_local_play_24))
    activityTaskArray.add(Task("Room", R.drawable.ic_baseline_meeting_room_24))

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.backgroundColor))
            /*.verticalScroll(state = rememberScrollState())*/
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "What are you doing Today ?",
                    color = colorResource(id = R.color.card_view_text_color),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { text = it },
                label = { Text(text = "Search") },
                trailingIcon = { Icon(Icons.Filled.Search, "") },
                placeholder = { Text(text = "Search your content") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = colorResource(id = R.color.card_view_text_color)
                )
            )

            /*Spacer(modifier = Modifier.padding(8.dp))*/
        }

        Column() {
            Text(
                text = "Choose Activity",
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp),
                color = colorResource(id = R.color.card_view_text_color),
                fontSize = 18.sp
            )

            Text(
                text = "Choose Activity to create new Task",
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                color = colorResource(id = R.color.colorSecondaryText),
            )
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            state = lazyListState
        ) {

            items(activityTaskArray.size) { position ->
                Column(
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            elevation = 4.dp,
                            onClick = {
                                navController.navigate(Screen.TaskDetails.route)
                            }
                        ) {
                            Image(
                                painterResource(id = activityTaskArray[position].drawable), "",
                                modifier = Modifier.padding(24.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = activityTaskArray[position].name,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 16.dp),
                            color = colorResource(id = R.color.card_view_text_color)
                        )
                    }
                }
            }
        }
    }
}