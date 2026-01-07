package com.example.calmkit.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.components.AppTextField
import com.example.calmkit.ui.components.PrimaryButton
import com.example.calmkit.ui.theme.CalmKitTheme

private enum class PreviewKind { FINANCE, SETTINGS, EMPTY, ONBOARDING, PROFILE, PRICING, NOTIFICATIONS }

private data class GalleryItem(
    val title: String,
    val subtitle: String,
    val route: String,
    val previewHeight: Int,
    val kind: PreviewKind,
    val showDarkBadge: Boolean = false
)

@Composable
fun UiKitGalleryScreen() {
    val (screen, setScreen) = remember { mutableStateOf("gallery") }

    if (screen == "finance") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            FinanceDashboardShowcase()
        }
        return
    }

    if (screen == "finance_dark") {
        CalmKitTheme(darkTheme = true) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    TextButton(onClick = { setScreen("gallery") }) {
                        Text("← Back")
                    }
                    Spacer(Modifier.height(8.dp))
                    FinanceDashboardShowcase()
                }
            }
        }
        return
    }

    if (screen == "empty_state") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            EmptyStateShowcase()
        }
        return
    }

    if (screen == "settings") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            SettingsShowcase()
        }
        return
    }

    if (screen == "settings_dark") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            SettingsDarkShowcase()
        }
        return
    }

    if (screen == "onboarding") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            OnboardingShowcase()
        }
        return
    }

    if (screen == "profile") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            ProfileShowcase()
        }
        return
    }

    if (screen == "pricing") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            PricingShowcase()
        }
        return
    }

    if (screen == "notifications") {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            TextButton(onClick = { setScreen("gallery") }) { Text("← Back") }
            Spacer(Modifier.height(8.dp))
            NotificationsShowcase()
        }
        return
    }

    // existing gallery UI...
    val (name, setName) = remember { mutableStateOf("") }

    val items = listOf(
        GalleryItem(
            title = "Finance Dashboard",
            subtitle = "Vibrant charts + premium fintech feel.",
            route = "finance",
            previewHeight = 140,
            kind = PreviewKind.FINANCE
        ),
        GalleryItem(
            title = "Finance Dashboard (Dark)",
            subtitle = "Forced dark theme preview.",
            route = "finance_dark",
            previewHeight = 170,
            kind = PreviewKind.FINANCE,
            showDarkBadge = true
        ),
        GalleryItem(
            title = "Empty State",
            subtitle = "Calm empty UI + clear actions.",
            route = "empty_state",
            previewHeight = 160,
            kind = PreviewKind.EMPTY
        ),
        GalleryItem(
            title = "Settings + Danger Zone",
            subtitle = "Toggles, sections, destructive actions.",
            route = "settings",
            previewHeight = 150,
            kind = PreviewKind.SETTINGS
        ),
        GalleryItem(
            title = "Settings (Dark)",
            subtitle = "Forced dark theme preview.",
            route = "settings_dark",
            previewHeight = 185,
            kind = PreviewKind.SETTINGS,
            showDarkBadge = true
        ),
        GalleryItem(
            title = "Onboarding Kit",
            subtitle = "3 screens with dots + next/back.",
            route = "onboarding",
            previewHeight = 155,
            kind = PreviewKind.ONBOARDING
        ),
        GalleryItem(
            title = "Profile",
            subtitle = "Avatar, stats chips, quick actions.",
            route = "profile",
            previewHeight = 175,
            kind = PreviewKind.PROFILE
        ),
        GalleryItem(
            title = "Pricing",
            subtitle = "Plan cards + toggle + CTA.",
            route = "pricing",
            previewHeight = 165,
            kind = PreviewKind.PRICING
        ),
        GalleryItem(
            title = "Notifications",
            subtitle = "Toggles + recent list.",
            route = "notifications",
            previewHeight = 180,
            kind = PreviewKind.NOTIFICATIONS
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("CalmKit", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(6.dp))
        Text(
            "A Pinterest-style UI kit gallery. Tap a card to preview.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(14.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                AppCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { setScreen(item.route) }
                ) {
                    // Preview block (gives Pinterest “tile” feel)
                    PreviewTile(
                        kind = item.kind,
                        heightDp = item.previewHeight,
                        modifier = Modifier.fillMaxWidth(),
                        showDarkBadge = item.showDarkBadge
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        item.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        item.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewTile(
    kind: PreviewKind,
    heightDp: Int,
    modifier: Modifier = Modifier,
    showDarkBadge: Boolean = false
) {
    val bg = MaterialTheme.colorScheme.surfaceVariant
    val outline = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    val accent = MaterialTheme.colorScheme.primary
    val accent2 = MaterialTheme.colorScheme.tertiary
    val textSoft = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)

    Box(
        modifier = modifier
            .height(heightDp.dp)
            .background(bg, RoundedCornerShape(18.dp))
            .padding(12.dp)
    ) {
        when (kind) {
            PreviewKind.FINANCE -> FinancePreviewDoodle(
                grid = outline,
                line1 = accent,
                line2 = accent2,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.SETTINGS -> SettingsPreviewDoodle(
                track = outline,
                fill = accent,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.EMPTY -> EmptyPreviewDoodle(
                outline = outline,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.ONBOARDING -> OnboardingPreviewDoodle(
                outline = outline,
                dot = accent,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.PROFILE -> ProfilePreviewDoodle(
                outline = outline,
                accent = accent,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.PRICING -> PricingPreviewDoodle(
                outline = outline,
                accent = accent,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )

            PreviewKind.NOTIFICATIONS -> NotificationsPreviewDoodle(
                outline = outline,
                accent = accent,
                soft = textSoft,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (showDarkBadge) {
            Surface(
                modifier = Modifier.align(Alignment.TopEnd),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.10f),
                shape = RoundedCornerShape(999.dp),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                border = androidx.compose.foundation.BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
                )
            ) {
                Text(
                    text = "Dark",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun FinancePreviewDoodle(
    grid: androidx.compose.ui.graphics.Color,
    line1: androidx.compose.ui.graphics.Color,
    line2: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    // Header hint
    Column(modifier) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(Modifier.size(width = 52.dp, height = 10.dp).background(soft, RoundedCornerShape(999.dp)))
            Box(Modifier.size(18.dp).background(soft, RoundedCornerShape(6.dp)))
        }
        Spacer(Modifier.height(10.dp))

        // Chart area
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(soft.copy(alpha = 0.10f), RoundedCornerShape(14.dp))
                .padding(10.dp)
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val strokeGrid = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round)
                val strokeA = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                val strokeB = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)

                // subtle grid lines
                val y1 = size.height * 0.25f
                val y2 = size.height * 0.55f
                val y3 = size.height * 0.85f
                drawLine(grid, Offset(0f, y1), Offset(size.width, y1), strokeWidth = strokeGrid.width)
                drawLine(grid, Offset(0f, y2), Offset(size.width, y2), strokeWidth = strokeGrid.width)
                drawLine(grid, Offset(0f, y3), Offset(size.width, y3), strokeWidth = strokeGrid.width)

                // line 1 (higher)
                val p1 = listOf(
                    Offset(0f, size.height * 0.70f),
                    Offset(size.width * 0.25f, size.height * 0.55f),
                    Offset(size.width * 0.55f, size.height * 0.62f),
                    Offset(size.width * 0.80f, size.height * 0.35f),
                    Offset(size.width, size.height * 0.42f)
                )
                for (i in 0 until p1.lastIndex) {
                    drawLine(line1, p1[i], p1[i + 1], strokeWidth = strokeA.width, cap = StrokeCap.Round)
                }

                // line 2 (comparison)
                val p2 = listOf(
                    Offset(0f, size.height * 0.78f),
                    Offset(size.width * 0.30f, size.height * 0.68f),
                    Offset(size.width * 0.60f, size.height * 0.72f),
                    Offset(size.width * 0.82f, size.height * 0.52f),
                    Offset(size.width, size.height * 0.58f)
                )
                for (i in 0 until p2.lastIndex) {
                    drawLine(line2.copy(alpha = 0.75f), p2[i], p2[i + 1], strokeWidth = strokeB.width, cap = StrokeCap.Round)
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // Mini stats hint
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.weight(1f).height(10.dp).background(soft, RoundedCornerShape(999.dp)))
            Box(Modifier.weight(1f).height(10.dp).background(soft.copy(alpha = 0.75f), RoundedCornerShape(999.dp)))
        }
    }
}

@Composable
private fun SettingsPreviewDoodle(
    track: androidx.compose.ui.graphics.Color,
    fill: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Fake section title
        Box(Modifier.size(width = 90.dp, height = 12.dp).background(soft, RoundedCornerShape(999.dp)))

        repeat(3) { idx ->
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Box(Modifier.size(width = 120.dp, height = 10.dp).background(soft, RoundedCornerShape(999.dp)))
                    Spacer(Modifier.height(6.dp))
                    Box(Modifier.size(width = 170.dp, height = 8.dp).background(soft.copy(alpha = 0.65f), RoundedCornerShape(999.dp)))
                }
                Spacer(Modifier.width(10.dp))

                // switch doodle
                val on = idx != 1
                Box(
                    Modifier
                        .size(width = 42.dp, height = 24.dp)
                        .background(if (on) fill.copy(alpha = 0.35f) else track, RoundedCornerShape(999.dp))
                        .padding(3.dp)
                ) {
                    Box(
                        Modifier
                            .size(18.dp)
                            .align(if (on) Alignment.CenterEnd else Alignment.CenterStart)
                            .background(if (on) fill else soft, CircleShape)
                    )
                }
            }

            if (idx != 2) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(track)
                )
            }
        }

        Spacer(Modifier.height(4.dp))
        // Danger zone hint
        Box(Modifier.size(width = 110.dp, height = 10.dp).background(soft.copy(alpha = 0.70f), RoundedCornerShape(999.dp)))
    }
}

@Composable
private fun EmptyPreviewDoodle(
    outline: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        // Large illustration placeholder
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 90.dp)
                .align(Alignment.TopCenter)
                .background(soft.copy(alpha = 0.12f), RoundedCornerShape(18.dp))
        )

        // Text lines
        Column(
            Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.size(width = 140.dp, height = 12.dp).background(soft, RoundedCornerShape(999.dp)))
            Spacer(Modifier.height(10.dp))
            Box(Modifier.size(width = 190.dp, height = 8.dp).background(soft.copy(alpha = 0.70f), RoundedCornerShape(999.dp)))
            Spacer(Modifier.height(6.dp))
            Box(Modifier.size(width = 160.dp, height = 8.dp).background(soft.copy(alpha = 0.70f), RoundedCornerShape(999.dp)))
            Spacer(Modifier.height(14.dp))
            Box(
                Modifier
                    .size(width = 130.dp, height = 34.dp)
                    .background(outline, RoundedCornerShape(999.dp))
            )
        }
    }
}

@Composable
private fun OnboardingPreviewDoodle(
    outline: androidx.compose.ui.graphics.Color,
    dot: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Image block
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(soft.copy(alpha = 0.12f), RoundedCornerShape(18.dp))
        )

        // Title + body lines
        Box(Modifier.size(width = 150.dp, height = 12.dp).background(soft, RoundedCornerShape(999.dp)))
        Box(Modifier.size(width = 200.dp, height = 8.dp).background(soft.copy(alpha = 0.70f), RoundedCornerShape(999.dp)))
        Box(Modifier.size(width = 170.dp, height = 8.dp).background(soft.copy(alpha = 0.70f), RoundedCornerShape(999.dp)))

        // Dots + button hint
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(Modifier.size(8.dp).background(outline, CircleShape))
                Box(Modifier.size(10.dp).background(dot, CircleShape))
                Box(Modifier.size(8.dp).background(outline, CircleShape))
            }
            Box(
                Modifier
                    .size(width = 74.dp, height = 28.dp)
                    .background(outline, RoundedCornerShape(999.dp))
            )
        }
    }
}

@Composable
private fun ProfilePreviewDoodle(
    outline: androidx.compose.ui.graphics.Color,
    accent: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(34.dp).background(outline, CircleShape))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Box(Modifier.size(width = 90.dp, height = 10.dp).background(soft, RoundedCornerShape(999.dp)))
                Spacer(Modifier.height(6.dp))
                Box(Modifier.size(width = 130.dp, height = 8.dp).background(soft.copy(alpha = 0.7f), RoundedCornerShape(999.dp)))
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(3) {
                Box(
                    Modifier.weight(1f).height(28.dp)
                        .background(outline.copy(alpha = 0.7f), RoundedCornerShape(999.dp))
                )
            }
        }
        Box(
            Modifier.fillMaxWidth().height(40.dp)
                .background(accent.copy(alpha = 0.20f), RoundedCornerShape(16.dp))
        )
    }
}

@Composable
private fun PricingPreviewDoodle(
    outline: androidx.compose.ui.graphics.Color,
    accent: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.width(56.dp).height(18.dp).background(outline, RoundedCornerShape(999.dp)))
            Box(Modifier.width(56.dp).height(18.dp).background(accent.copy(alpha = 0.25f), RoundedCornerShape(999.dp)))
        }
        Box(Modifier.fillMaxWidth().height(64.dp).background(outline.copy(alpha = 0.7f), RoundedCornerShape(18.dp)))
        Box(Modifier.fillMaxWidth().height(74.dp).background(accent.copy(alpha = 0.18f), RoundedCornerShape(18.dp)))
        Box(Modifier.fillMaxWidth().height(34.dp).background(outline, RoundedCornerShape(999.dp)))
    }
}

@Composable
private fun NotificationsPreviewDoodle(
    outline: androidx.compose.ui.graphics.Color,
    accent: androidx.compose.ui.graphics.Color,
    soft: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // toggle hints
        repeat(2) { i ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Box(Modifier.size(width = 120.dp, height = 10.dp).background(soft, RoundedCornerShape(999.dp)))
                    Spacer(Modifier.height(6.dp))
                    Box(Modifier.size(width = 160.dp, height = 8.dp).background(soft.copy(alpha = 0.7f), RoundedCornerShape(999.dp)))
                }
                Spacer(Modifier.width(10.dp))
                Box(
                    Modifier.size(width = 40.dp, height = 22.dp)
                        .background(if (i == 0) accent.copy(alpha = 0.25f) else outline, RoundedCornerShape(999.dp))
                )
            }
            Box(Modifier.fillMaxWidth().height(1.dp).background(outline))
        }
        // list hints
        repeat(2) {
            Box(Modifier.fillMaxWidth().height(10.dp).background(soft.copy(alpha = 0.7f), RoundedCornerShape(999.dp)))
        }
    }
}