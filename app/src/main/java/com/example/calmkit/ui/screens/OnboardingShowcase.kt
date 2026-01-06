package com.example.calmkit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.components.PrimaryButton
import com.example.calmkit.ui.theme.Accent
import com.example.calmkit.ui.theme.AccentSoft

private data class OnboardingPage(
    val title: String,
    val body: String
)

@Composable
fun OnboardingShowcase() {
    val pages = listOf(
        OnboardingPage(
            title = "Track what matters",
            body = "A calm, clean overview of spending so you always know where you stand."
        ),
        OnboardingPage(
            title = "Patterns, not guilt",
            body = "Spot trends with vibrant charts — without turning your finances into stress."
        ),
        OnboardingPage(
            title = "Stay in control",
            body = "Export anytime, keep data local, and use smart reminders only if you want."
        )
    )

    var index by remember { mutableStateOf(0) }
    val page = pages[index]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Onboarding Kit", style = MaterialTheme.typography.headlineLarge)

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Illustration placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(AccentSoft)
                )

                Spacer(Modifier.height(16.dp))

                Text(page.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    page.body,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                Dots(count = pages.size, active = index)

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { index = (index - 1).coerceAtLeast(0) },
                        enabled = index > 0
                    ) { Text("Back") }

                    PrimaryButton(
                        text = if (index == pages.lastIndex) "Get started" else "Next",
                        onClick = {
                            index = if (index == pages.lastIndex) 0 else index + 1
                        },
                        modifier = Modifier.widthIn(min = 160.dp)
                    )
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 0.dp
        ) {
            Text(
                text = "Tip: Keep onboarding copy short. Big whitespace. One clear next action.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(14.dp)
            )
        }
    }
}

@Composable
private fun Dots(count: Int, active: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(count) { i ->
            Box(
                modifier = Modifier
                    .size(if (i == active) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (i == active) Accent else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
            )
        }
    }
}