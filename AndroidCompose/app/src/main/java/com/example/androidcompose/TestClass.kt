package com.example.androidcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

class ComposeActivity : ComponentActivity() {

    private val textColorString = "#008080"
    private val themeColorString = "#3CB5A1"

    private val textColorInt = Color(textColorString.toColorInt())
    private val themeColorInt = Color(themeColorString.toColorInt())

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar =
                {
                    TopAppBar(
                        title = { Text(text = "Android Compose Tutorials") },
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Filled.Menu, "")
                            }
                        },
                        backgroundColor = themeColorInt,
                        contentColor = Color.White,
                        elevation = 12.dp
                    )
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    showTextAndImage(
                        drawableResource = R.drawable.ic_launcher_background,
                        displayText = "Compass"
                    )
                }
            }
        }
    }

    @Composable
    fun showTextAndImage(drawableResource: Int, displayText: String) {
        Row(modifier = Modifier.padding(8.dp)) {
            showImage(drawableResource = drawableResource)
            showComposeText(displayText = displayText)
        }
    }

    @Composable
    fun showComposeText(displayText: String) {
        val shape = CircleShape
        Text(
            text = displayText,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colors.secondary, shape)
                .background(MaterialTheme.colors.primary, shape)
                .padding(16.dp)
        )

    }

    @Composable
    fun showImage(drawableResource: Int) {
        Card(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            Image(
                painterResource(drawableResource),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Preview
    @Composable
    fun showText() {

        Text(
            text = "TextView",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "Edit Text",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "Image",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = "Button",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "Dialog",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "List",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "Floating Action Button",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Text(
            text = "APIs",
            modifier = Modifier.alpha(1f),
            color = textColorInt,
            fontSize = 18.sp
        )

        Box(Modifier.padding(10.dp)) {
            Surface(color = Color.LightGray) {
                Text(
                    text = "Box Text", color = Color.Blue,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Text(
            text = "Hello World!",
            color = Color.Red,
            modifier = Modifier
                .padding(8.dp) // margin
                .border(2.dp, Color.DarkGray) // outer border
                .padding(8.dp) // space between the borders
                .border(2.dp, Color.Green) // inner border
                .padding(8.dp) // padding
        )

        val shape = CircleShape
        Text(
            text = "Text 1",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colors.secondary, shape)
                .background(MaterialTheme.colors.primary, shape)
                .padding(16.dp)
        )

        Card(
            elevation = 4.dp, modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Text(
                text = "Switch",
                modifier = Modifier
                    .alpha(1f)
                    .padding(8.dp),
                color = textColorInt,
                fontSize = 18.sp
            )
        }

    }
}
