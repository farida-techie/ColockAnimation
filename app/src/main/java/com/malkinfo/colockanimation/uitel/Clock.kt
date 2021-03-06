package com.malkinfo.colockanimation.uitel

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp


var currentAngle = 0f
var previous = 0f

@Composable
fun Clocks(modifier:Modifier){
    val infiniteTransition = rememberInfiniteTransition()
    val poisition = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        )
    )
    val handColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val t = poisition.value
    /**set Canvas*/
    Canvas(modifier = modifier){
        val middle = Offset(size.minDimension/2,size.minDimension/2)
        drawCircle(
            color = handColor,
            center = middle,
            radius = size.minDimension/2,
            style = Stroke(4.dp.toPx()),
        )
        withTransform(
            {
                if (previous>t){
                   currentAngle +=360/60
                }
                previous = t
                rotate(currentAngle,middle)
            },{
                drawLine(
                    cap = StrokeCap.Round,
                    strokeWidth = 12.dp.toPx(),
                    color = handColor,
                    start = middle,
                    end = Offset(size.minDimension/2,36.dp.toPx())
                )
            }
        )
        withTransform(
            {
                rotate(360*t,middle)
            },{
                drawLine(
                    strokeWidth = 8.dp.toPx(),
                    cap = StrokeCap.Round,
                    color = Color.Red,
                    start = middle,
                    end = Offset(size.minDimension/2,12.dp.toPx())
                )
            }
        )
    }
}