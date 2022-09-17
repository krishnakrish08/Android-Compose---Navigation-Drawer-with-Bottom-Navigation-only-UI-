package com.example.androidcompose.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcompose.R
import com.example.androidcompose.home.HomeComposeActivity

@Composable
fun SplashScreen() {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.splashGradientColorOne),
                        colorResource(id = R.color.splashGradientColorTwo)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.mipmap.ic_launcher_round,
                ),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Yellow)
                    .size(80.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(32.dp),
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, HomeComposeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }, 2000)
    }
}