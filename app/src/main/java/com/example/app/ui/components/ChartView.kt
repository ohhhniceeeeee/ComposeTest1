package com.example.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ChartView(
    modifier: Modifier = Modifier,
    points: List<Double>
) {

    //每一行的高度
    val heightForRow = 24
    //总行数
    val countForRow = 5

    //小圆圈半径
    val circleRadius = 2.5

    val perY = 8 // 24 * 5 / 15 每8dp为1积分，每行3积分

    //曲线图宽度
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2
    //曲线图高度
    val canvasHeight = heightForRow * countForRow + circleRadius

    //曲线图七平分宽度
    val averageOfWidth = canvasWidth / 7

    Canvas(
        modifier = modifier.size(width = canvasWidth.dp, height = canvasHeight.dp)
    ) {
        //背景横线
        for (index in 0..countForRow) {
            val y = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = y),
                end = Offset(x = size.width, y = y)
            )
        }

        //画圆圈和折线
        for (index in 0 until points.count()) {
            val circleCenter = Offset(
                x = (averageOfWidth * index + averageOfWidth / 2).dp.toPx(),
                y = (heightForRow * countForRow - points[index] * perY + circleRadius).dp.toPx()
            )
            drawCircle(
                color = Color(0xFF149EE7),
                radius = circleRadius.dp.toPx(),
                center = circleCenter,
                style = Stroke(width = 5f)
            )
            if (index < points.count() - 1) {
                val nextPointOffset = Offset(
                    x = (averageOfWidth * (index + 1) + averageOfWidth / 2).dp.toPx(),
                    y = (heightForRow * countForRow - points[index + 1] * perY + circleRadius).dp.toPx()
                )
                drawLine(
                    color = Color(0xFF149EE7),
                    start = circleCenter,
                    end = nextPointOffset,
                    strokeWidth = 5f
                )
            }
        }
    }

}