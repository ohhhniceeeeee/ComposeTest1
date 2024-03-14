package com.example.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationContent(vm: MainViewModel) {

    //虚拟页数
    val virtualCount = 200
    //实际页数
    val actualCount = vm.notifications.size
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
        }, 2000, 3000)

        onDispose {
            timer.cancel()
        }
    }


    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0x22149EE7))
            .height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "最新活动",
            color = Color(0xFF149EE7),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        VerticalPager(
            state = state,
            modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.Start,
        ) { index ->
            Text(
                text = vm.notifications[index % actualCount],
                color = Color(0xFF333333),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "更多",
            color = Color(0xFF666666),
            fontSize = 14.sp,
            maxLines = 1
        )
    }
}