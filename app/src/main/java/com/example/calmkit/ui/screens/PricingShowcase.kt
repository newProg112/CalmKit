package com.example.calmkit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.components.PrimaryButton
import com.example.calmkit.ui.theme.Accent
import com.example.calmkit.ui.theme.AccentSoft

@Composable
fun PricingShowcase() {
    var yearly by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Pricing", style = MaterialTheme.typography.headlineLarge)

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text("Choose your plan", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TogglePill(text = "Yearly", selected = yearly, onClick = { yearly = true })
                TogglePill(text = "Monthly", selected = !yearly, onClick = { yearly = false })
            }
        }

        PlanCard(
            title = "Free",
            price = "£0",
            note = "Local-only, simple tracking",
            features = listOf("Manual entries", "Basic exports", "Offline mode"),
            highlighted = false
        )

        PlanCard(
            title = "Premium",
            price = if (yearly) "£19/yr" else "£2.99/mo",
            note = "Best for daily use",
            features = listOf("Charts + insights", "Smart reminders", "Cloud sync", "Priority polish"),
            highlighted = true
        )

        PrimaryButton(
            text = "Continue",
            onClick = { /* showcase */ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TogglePill(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(999.dp),
        color = if (selected) AccentSoft else MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 0.dp
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (selected) Accent else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun PlanCard(
    title: String,
    price: String,
    note: String,
    features: List<String>,
    highlighted: Boolean
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = if (highlighted) AccentSoft else MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 0.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, style = MaterialTheme.typography.headlineMedium)
                Text(price, style = MaterialTheme.typography.headlineMedium, color = Accent)
            }
            Spacer(Modifier.height(6.dp))
            Text(note, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(Modifier.height(12.dp))

            features.forEach {
                Text("• $it", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}