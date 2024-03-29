package com.example.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.components.ChartView
import com.example.app.ui.components.CircleRing
import com.example.app.ui.components.DailyTaskContent
import com.example.app.ui.components.appBarHeight
import com.example.app.viewmodel.TaskViewModel


@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel = viewModel()
) {

    //圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }

    //当学年积分改变时重新计算百分比
    LaunchedEffect(taskViewModel.pointOfYear) {
        taskViewModel.updatePointPercent()
    }

    LaunchedEffect(taskViewModel.tips) {
        taskViewModel.updateTips()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF149EE7),
                        Color.White
                    )
                )
            )
    ) {
        //标题栏
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(appBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "学习任务",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp
            )
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //学习周期
            item {
                Text(
                    textAlign = TextAlign.Center,
                    text = taskViewModel.taskDate,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            //学习进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ) {
                    //圆环
                    CircleRing(boxWidthDp, taskViewModel)

                    //进度数据
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            buildAnnotatedString {
                                append(taskViewModel.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年规定积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear - taskViewModel.pointOfYear}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(text = "还差", fontSize = 12.sp, color = Color.White)
                    }
                }
            }

            //学习明细
            item() {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .fillParentMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "学习明细",
                        fontSize = 16.sp,
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = "最近一周获得积分情况",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    )

                    //折线图
                    ChartView(
                        points = taskViewModel.pointsOfWeek,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    //折线图日期
                    Row {
                        taskViewModel.weeks.forEach {
                            Text(
                                text = it, fontSize = 12.sp, color = Color(0xFF999999),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    //今日任务提醒
                    Text(
                        text = taskViewModel.tips,
                        fontSize = 14.sp,
                        color = Color(0xFF149EE7),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .background(Color(0x33149EE7))
                            .padding(8.dp)

                    )

                    //每日任务
                    DailyTaskContent()
                }
            }

        }

    }
}


