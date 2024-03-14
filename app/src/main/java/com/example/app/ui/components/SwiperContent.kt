package com.example.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


//循环轮播图
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperContent(
    vm: MainViewModel
) {

    //虚拟页数
    val virtualCount = 200
    //实际页数
    val actualCount = vm.swiperData.size
    //初始图片下标
    val initialIndex = virtualCount / 2


    val state = rememberPagerState(initialPage = initialIndex) {
        virtualCount
    }

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                coroutineScope.launch {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            }
        }, 3000, 3000)

        onDispose {
            timer.cancel()
        }
    }


    HorizontalPager(
        state = state,
        contentPadding = PaddingValues(8.dp),
        pageSpacing = 8.dp,
    ) { index ->
        Image(
            painter = painterResource(id = vm.swiperData[index % actualCount].imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}