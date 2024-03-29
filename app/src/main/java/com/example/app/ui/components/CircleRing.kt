package com.example.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.app.viewmodel.TaskViewModel

@Composable
fun CircleRing(
    boxWidthDp: Int,
    taskViewModel: TaskViewModel
) {

    val strokeWidth = 30f
    Canvas(
        modifier = Modifier.size(boxWidthDp.dp)
    ) {

        //弧形背景
        drawArc(
            color = Color(0, 0, 0, 15),
            startAngle = 160f,
            sweepAngle = 220f,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
        //弧形前景
        drawArc(
            color = Color.White,
            startAngle = 160f,
            sweepAngle = taskViewModel.pointOfYearPercent,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }


}