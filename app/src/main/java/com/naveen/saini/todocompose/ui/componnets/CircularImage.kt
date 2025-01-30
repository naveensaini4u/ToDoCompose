package com.naveen.saini.todocompose.ui.componnets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.naveen.saini.todocompose.R

@Composable
fun CircularImage(){
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)                       // clip to the circle shape
            .border(1.dp, Color.Gray, CircleShape)   // add a border (optional)
    )
}