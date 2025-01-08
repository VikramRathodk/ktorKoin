package org.devvikram.ktorkoin.navigation.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CanvasScreen(modifier: Modifier)
{

    Column (
        modifier = modifier
           .fillMaxSize()
           .padding(12.dp)
    ){
        // instagram logo
        Text(
            text = "Instagram",
            modifier = Modifier.padding(12.dp)
        )

        Canvas(
            modifier = Modifier.padding(12.dp).size(200.dp)
        ) {

            // rounder rectangle
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Blue
                    )
                ),
                cornerRadius = CornerRadius(
                    x = 120f,
                    y = 120f
                ),
                style = Stroke(
                    width = 12f,
                ),
            )
            // inner big circle
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Blue
                    )
                ),
                radius = 100f,
                style = Stroke(
                    width = 12f,
                ),
            )

            // draw  smaller circle
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Blue
                    )),
                radius = 24f,
                center =
                Offset(
                    x = size.width.times(0.8f),
                    y = size.height.times(0.2f)
                )
                ,

                )

        }

        // face book logo
        Text(
            text = "Facebook",
            modifier = Modifier.padding(12.dp)
        )
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = 520f
        }
        Canvas(
            modifier = Modifier.padding(12.dp).size(200.dp)
        )
        {
            drawRoundRect(
                color = Color.Blue,
                cornerRadius = CornerRadius(
                    x = 100f,
                    y = 100f
                )
            )
            drawContext.canvas.nativeCanvas.drawText(
                "f",
                center.x+100,
                center.y+220,
                paint

            )
        }

        Text(
            text = "circular Progress indicator",
            modifier = Modifier.padding(12.dp)
        )
        var progress by remember { mutableStateOf(0f) }

        LaunchedEffect(Unit) {
            while (true) {

                progress += 0.010f
                if(progress >=1f) progress = 0f
                delay(20)
            }
        }
        Canvas(
            modifier = Modifier
                .padding(12.dp)
                .size(60.dp)
        ) {
            val sweepAngle = 360f * progress
            val strokeWidth = 16f

            // back ground circle
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth
                )
            )

            // foreground
            drawArc(
                color = Color.Green,
                startAngle = 270f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

    }






//    val circleRadius = remember { mutableStateOf(50f) }
//    Column {
//        Canvas(modifier = Modifier.fillMaxSize().weight(1f)) {
//            drawCircle(
//                color = Color.Green,
//                radius = circleRadius.value,
//                center = Offset(size.width / 2, size.height / 2)
//            )
//        }
//        Slider(
//            value = circleRadius.value,
//            onValueChange = { circleRadius.value = it },
//            valueRange = 10f..150f
//        )
//    }


}

@Preview
@Composable
fun CanvasScreenPreview() {
    CanvasScreen(Modifier.fillMaxSize())
}