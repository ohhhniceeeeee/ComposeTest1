package com.example.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.app.ui.theme.Blue200
import com.example.app.ui.theme.Blue700

//标题栏高度
val appBarHeight = 56.dp
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    statusBarHeight: Int,
    content: @Composable () -> Unit
) {


    //转换状态栏高度为dp
    val statusBarHeightDp = with(LocalDensity.current) {
        statusBarHeight.toDp()
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    listOf(
                        Blue700,
                        Blue200
                    )
                )
            )
            .height(appBarHeight + statusBarHeightDp)
            .padding(top = statusBarHeightDp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}


@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(statusBarHeight = 30) {
        Text(text = "标题")
    }
}