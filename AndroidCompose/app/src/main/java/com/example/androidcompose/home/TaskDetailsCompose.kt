package com.example.androidcompose.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcompose.R

class TaskDetailsCompose {

    @Composable
    fun TaskDetailsView() {

        val list = listOf(
            "Research Pattern", "Define Project Objectives",
            "Define Project Scope",
            "Create Options for User Flow",
            "Create Rapid Prototype",
            "Analyze Current Status",
            "Add Resources",
            "Publish the Plan",
            "Collect Project Information",
            "Release the build"
        )

        LazyColumn(modifier = Modifier.padding(16.dp)) {

            item {
                Text(
                    text = "Project Details",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.card_view_text_color)
                )
            }

            items(list) {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.card_view_text_color)
                )
            }
        }

    }

}