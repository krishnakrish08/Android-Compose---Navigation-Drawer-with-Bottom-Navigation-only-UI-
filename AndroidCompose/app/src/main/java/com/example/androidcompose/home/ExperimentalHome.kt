package com.example.androidcompose.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.androidcompose.R
import com.example.androidcompose.nestedscroll.VerticalNestedScrollView
import com.example.androidcompose.nestedscroll.rememberNestedScrollViewState

class ExperimentalHome {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun NestedViewsInHome() {

        val scope = rememberCoroutineScope()

        VerticalNestedScrollView(state = rememberNestedScrollViewState(),
            content = {

                Column {

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

                    LazyVerticalGrid(cells = GridCells.Fixed(3)) {
                        items(120) { index ->
                            Text(
                                "I'm item $index", modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            })

    }


}