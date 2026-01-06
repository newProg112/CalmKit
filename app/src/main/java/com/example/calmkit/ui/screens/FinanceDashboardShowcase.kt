package com.example.calmkit.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.theme.Accent
import com.example.calmkit.ui.theme.AccentSoft
import com.example.calmkit.ui.theme.ChartAmber
import com.example.calmkit.ui.theme.ChartCyan
import com.example.calmkit.ui.theme.ChartLime
import com.example.calmkit.ui.theme.ChartPink
import com.example.calmkit.ui.theme.ChartViolet
import com.example.calmkit.ui.theme.Good
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Slice(
    val label: String,
    val value: Float,
    val color: Color
)

@Composable
fun FinanceDashboardShowcase() {
    val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f

    fun vivid(c: Color): Color =
        if (isDark) lerp(c, Color.White, 0.10f) else c

    val slices = listOf(
        Slice("Food", 320f, vivid(ChartAmber)),
        Slice("Bills", 260f, vivid(Accent)),
        Slice("Travel", 180f, vivid(ChartCyan)),
        Slice("Subs", 48f, vivid(ChartPink)),
        Slice("Other", 90f, vivid(ChartViolet)),
    )

    val total = slices.sumOf { it.value.toDouble() }.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Finance Dashboard",
            style = MaterialTheme.typography.headlineLarge
        )

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Total balance",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "£79,480.50",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "+ £5,200 this month",
                color = Good,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Spending trend",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))

            // Fake data: Current month vs Last month (14 days)
            val current = listOf(
                420f, 380f, 460f, 520f, 480f, 560f, 610f,
                590f, 640f, 710f, 680f, 760f, 820f, 790f
            )
            val lastMonth = listOf(
                360f, 340f, 390f, 430f, 410f, 470f, 520f,
                500f, 560f, 610f, 590f, 640f, 700f, 680f
            )

            var selectedIndex by remember { mutableStateOf<Int?>(null) }

            val grid = MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.30f else 0.18f)

            val lineA = vivid(ChartCyan)
            val fillA = vivid(ChartCyan).copy(alpha = if (isDark) 0.38f else 0.25f)

            // comparison line
            val baseB = vivid(ChartLime)
            val lineBSoft = baseB.copy(alpha = if (isDark) 0.90f else 0.75f)

            PillLegendRow(
                left = "14D",
                middle = "Spending",
                right = "+12%",
                rightColor = Good
            )

            Spacer(Modifier.height(10.dp))

            InteractiveTrendChart(
                pointsA = current,
                pointsB = lastMonth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                lineA = lineA,
                fillA = fillA,
                lineB = lineBSoft,
                gridColor = grid,
                selectedIndex = selectedIndex,
                onSelectedIndex = { selectedIndex = it },
                labelForIndex = { i -> "Day ${i + 1}" }
            )
        }

        // ✅ Donut chart card
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Category breakdown",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "This month",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(12.dp))

                    Legend(
                        slices = slices,
                        total = total
                    )
                }

                Spacer(Modifier.width(16.dp))

                DonutChart(
                    slices = slices,
                    centerTitle = "Spent",
                    centerValue = "£${total.toInt()}",
                    modifier = Modifier.size(150.dp),
                    strokeWidth = 18.dp
                )
            }
        }
    }
}

@Composable
private fun DonutChart(
    slices: List<Slice>,
    centerTitle: String,
    centerValue: String,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 16.dp
) {
    val total = slices.sumOf { it.value.toDouble() }.toFloat().coerceAtLeast(1f)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
        val trackColor = MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.30f else 0.18f)

        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round
            )

            val diameter = size.minDimension
            val topLeft = Offset(
                (size.width - diameter) / 2f,
                (size.height - diameter) / 2f
            )
            val arcSize = Size(diameter, diameter)

            var startAngle = -90f

            // subtle track behind arcs
            drawArc(
                color = trackColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = stroke
            )

            slices.forEach { s ->
                val sweep = (s.value / total) * 360f
                drawArc(
                    color = s.color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = stroke
                )
                startAngle += sweep
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = centerTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                text = centerValue,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun Legend(
    slices: List<Slice>,
    total: Float
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        slices.forEach { s ->
            val pct = ((s.value / total) * 100f)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(s.color, CircleShape)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = s.label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${pct.toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PillLegendRow(
    left: String,
    middle: String,
    right: String,
    rightColor: Color
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        TinyPill(text = left)
        TinyPill(text = middle)
        TinyPill(
            text = right,
            textColor = rightColor
        )
    }
}

@Composable
private fun TinyPill(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 0.dp
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
        )
    }
}

@Composable
private fun InteractiveTrendChart(
    pointsA: List<Float>,
    pointsB: List<Float>,
    modifier: Modifier = Modifier,
    lineA: Color,
    fillA: Color,
    lineB: Color,
    gridColor: Color,
    selectedIndex: Int?,
    onSelectedIndex: (Int?) -> Unit,
    labelForIndex: (Int) -> String = { "Day ${it + 1}" }
) {
    val n = min(pointsA.size, pointsB.size)
    val a = remember(pointsA) { pointsA.take(n) }
    val b = remember(pointsB) { pointsB.take(n) }

    var sizePx by remember { mutableStateOf(IntSize(1, 1)) }

    // Read theme colors OUTSIDE canvas
    val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f

    val bubbleBg = if (isDark) {
        // "Glass" look: use surfaceVariant with a touch of transparency
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.92f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val bubbleText = MaterialTheme.colorScheme.onSurface

    val bubbleSubText = if (isDark) {
        // slightly brighter in dark so it reads cleanly
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.78f)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val bubbleBorder = if (isDark) {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    } else {
        MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .onSizeChanged { sizePx = it }
            .pointerInput(n, sizePx) {
                fun nearestIndex(x: Float): Int {
                    if (n < 2) return 0

                    val leftPad = 10.dp.toPx()
                    val rightPad = 10.dp.toPx()
                    val w = sizePx.width.toFloat() - leftPad - rightPad

                    fun xFor(i: Int): Float {
                        val t = i.toFloat() / (n - 1).toFloat()
                        return leftPad + (t * w)
                    }

                    var best = 0
                    var bestDist = Float.MAX_VALUE
                    for (i in 0 until n) {
                        val dx = abs(x - xFor(i))
                        if (dx < bestDist) {
                            bestDist = dx
                            best = i
                        }
                    }
                    return best
                }

                detectTapGestures(
                    onTap = { tap ->
                        onSelectedIndex(nearestIndex(tap.x))
                    }
                )
            }
            .pointerInput(n, sizePx) {
                fun nearestIndex(x: Float): Int {
                    if (n < 2) return 0

                    val leftPad = 10.dp.toPx()
                    val rightPad = 10.dp.toPx()
                    val w = sizePx.width.toFloat() - leftPad - rightPad

                    fun xFor(i: Int): Float {
                        val t = i.toFloat() / (n - 1).toFloat()
                        return leftPad + (t * w)
                    }

                    var best = 0
                    var bestDist = Float.MAX_VALUE
                    for (i in 0 until n) {
                        val dx = abs(x - xFor(i))
                        if (dx < bestDist) {
                            bestDist = dx
                            best = i
                        }
                    }
                    return best
                }

                detectDragGestures(
                    onDragStart = { start ->
                        onSelectedIndex(nearestIndex(start.x))
                    },
                    onDrag = { change, _ ->
                        onSelectedIndex(nearestIndex(change.position.x))
                    }
                )
            }
    ) {
        // Draw chart
        Canvas(modifier = Modifier.fillMaxSize()) {
            if (n < 2) return@Canvas

            val leftPad = 10.dp.toPx()
            val rightPad = 10.dp.toPx()
            val topPad = 10.dp.toPx()
            val bottomPad = 12.dp.toPx()

            val w = size.width - leftPad - rightPad
            val h = size.height - topPad - bottomPad

            val minY = min(a.minOrNull() ?: 0f, b.minOrNull() ?: 0f)
            val maxY = max(a.maxOrNull() ?: 1f, b.maxOrNull() ?: 1f)
            val range = max(1e-6f, maxY - minY)

            fun xFor(i: Int): Float {
                val t = i.toFloat() / (n - 1).toFloat()
                return leftPad + (t * w)
            }

            fun yFor(v: Float): Float {
                val t = (v - minY) / range
                return topPad + (1f - t) * h
            }

            // Grid (3 horizontals)
            val gridLines = 3
            repeat(gridLines) { idx ->
                val y = topPad + (idx.toFloat() / (gridLines - 1).toFloat()) * h
                drawLine(
                    color = gridColor,
                    start = Offset(leftPad, y),
                    end = Offset(leftPad + w, y),
                    strokeWidth = 1.dp.toPx()
                )
            }

            fun smoothPath(values: List<Float>): Path {
                val path = Path()
                val first = Offset(xFor(0), yFor(values[0]))
                path.moveTo(first.x, first.y)

                for (i in 1 until values.size) {
                    val prev = Offset(xFor(i - 1), yFor(values[i - 1]))
                    val cur = Offset(xFor(i), yFor(values[i]))

                    val midX = (prev.x + cur.x) / 2f
                    val midY = (prev.y + cur.y) / 2f

                    path.quadraticBezierTo(prev.x, prev.y, midX, midY)
                    if (i == values.lastIndex) {
                        path.quadraticBezierTo(midX, midY, cur.x, cur.y)
                    }
                }
                return path
            }

            val pathA = smoothPath(a)
            val pathB = smoothPath(b)

            // Fill under A
            val fillPathA = Path().apply {
                addPath(pathA)
                lineTo(leftPad + w, topPad + h)
                lineTo(leftPad, topPad + h)
                close()
            }

            val fillBrushA = Brush.verticalGradient(
                colors = listOf(fillA, Color.Transparent),
                startY = topPad,
                endY = topPad + h
            )
            drawPath(path = fillPathA, brush = fillBrushA)

            val strokeA = Stroke(
                width = 3.5.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
            val strokeB = Stroke(
                width = 2.5.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )

            drawPath(path = pathB, color = lineB, style = strokeB)
            drawPath(path = pathA, color = lineA, style = strokeA)

            // Selected vertical marker + dots
            selectedIndex?.let { i ->
                val x = xFor(i)
                drawLine(
                    color = gridColor,
                    start = Offset(x, topPad),
                    end = Offset(x, topPad + h),
                    strokeWidth = 1.dp.toPx()
                )

                val yA = yFor(a[i])
                val yB = yFor(b[i])

                drawCircle(color = lineB, radius = 4.dp.toPx(), center = Offset(x, yB))
                drawCircle(color = lineA, radius = 5.dp.toPx(), center = Offset(x, yA))
            }
        }

        // Tooltip overlay (composable bubble)
        selectedIndex?.let { i ->
            val leftPadDp = 10.dp
            val rightPadDp = 10.dp
            val topPadDp = 10.dp
            val bottomPadDp = 12.dp

            // Compute tooltip position in dp using same chart math
            val density = LocalDensity.current

            val xDp = with(density) {
                val leftPadPx = 10.dp.toPx()
                val rightPadPx = 10.dp.toPx()
                val w = sizePx.width.toFloat() - leftPadPx - rightPadPx

                val t = i.toFloat() / (n - 1).toFloat()
                val x = leftPadPx + t * w

                x.toDp()
            }

            // Place near top; clamp left/right a bit
            val chartWidthDp = with(density) { sizePx.width.toDp() }

            val bubbleWidth = 160.dp
            val bubbleHalf = bubbleWidth / 2f

            val bubbleOffsetX =
                (xDp - bubbleHalf).coerceIn(0.dp, (chartWidthDp - bubbleWidth).coerceAtLeast(0.dp))

            // Arrow should point to the selected x, *relative to the bubble's left*
            val arrowCenterX = (xDp - bubbleOffsetX).coerceIn(16.dp, bubbleWidth - 16.dp)

            TooltipBubble(
                title = labelForIndex(i),
                line1 = "This month: £${a[i].toInt()}",
                line2 = "Last month: £${b[i].toInt()}",
                modifier = Modifier.offset(x = bubbleOffsetX, y = 0.dp),
                bg = bubbleBg,
                border = bubbleBorder,
                titleColor = bubbleText,
                textColor = bubbleSubText,
                arrowCenterX = arrowCenterX,
                arrowColor = bubbleBg,
                arrowBorderColor = bubbleBorder,
                onDismiss = { onSelectedIndex(null) }
            )
        }
    }
}

@Composable
private fun TooltipBubble(
    title: String,
    line1: String,
    line2: String,
    modifier: Modifier = Modifier,
    bg: Color,
    border: Color,
    titleColor: Color,
    textColor: Color,
    arrowCenterX: Dp,
    arrowColor: Color,
    arrowBorderColor: Color,
    onDismiss: () -> Unit
) {
    Box(modifier = modifier) {
        // Main bubble
        Surface(
            modifier = Modifier.pointerInput(Unit) { detectTapGestures(onTap = { onDismiss() }) },
            shape = RoundedCornerShape(14.dp),
            color = bg,
            tonalElevation = if (bg.alpha < 1f) 4.dp else 2.dp,
            shadowElevation = if (bg.alpha < 1f) 4.dp else 2.dp,
            border = BorderStroke(1.dp, border)
        ) {
            Column(Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
                Text(title, style = MaterialTheme.typography.labelLarge, color = titleColor)
                Spacer(Modifier.height(4.dp))
                Text(line1, style = MaterialTheme.typography.bodyMedium, color = textColor)
                Text(line2, style = MaterialTheme.typography.bodyMedium, color = textColor)
            }
        }

        // Arrow (little triangle) under bubble, aligned to arrowCenterX
        Canvas(
            modifier = Modifier
                .offset(x = arrowCenterX - 10.dp, y = 44.dp) // y is roughly under title line; tweak if you want
                .size(width = 20.dp, height = 12.dp)
        ) {
            val path = Path().apply {
                moveTo(size.width / 2f, size.height)      // bottom tip
                lineTo(0f, 0f)                             // top-left
                lineTo(size.width, 0f)                     // top-right
                close()
            }

            // Border first (slightly bigger look)
            drawPath(path = path, color = arrowBorderColor)
            // Fill over it, inset a touch
            drawPath(
                path = path,
                color = arrowColor
            )
        }
    }
}