package com.example.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.app.ui.components.TopAppBar

@Composable
fun MineScreen(statusBarHeight:Int){
    Column(modifier = Modifier) {
        TopAppBar(statusBarHeight=statusBarHeight) {
            Text(text = "我的页面", fontSize = 18.sp)
        }
        Text(text = "我的页面")
    }
}


@Preview
@Composable
fun MineScreenPreview(){
    MineScreen(20)
}