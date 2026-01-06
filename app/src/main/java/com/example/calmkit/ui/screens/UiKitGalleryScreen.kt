package com.example.calmkit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.components.AppTextField
import com.example.calmkit.ui.components.PrimaryButton
import com.example.calmkit.ui.theme.CalmKitTheme

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

    // existing gallery UI...
    val (name, setName) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("CalmKit", style = MaterialTheme.typography.headlineLarge)
        Text(
            "A tiny UI kit showcase. No pressure. Just craft.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("finance") }
        ) {
            Text("Finance Dashboard", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Vibrant charts + premium fintech feel.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("finance_dark") }
        ) {
            Text("Finance Dashboard (Dark)", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Same layout, forced dark scheme.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("empty_state") }
        ) {
            Text("Empty State", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Calm empty UI + clear actions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("settings") }
        ) {
            Text("Settings + Danger Zone", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Toggles, sections, and destructive actions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("settings_dark") }
        ) {
            Text("Settings (Dark)", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Forced dark theme preview.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setScreen("onboarding") }
        ) {
            Text("Onboarding Kit", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "3-screen flow with dots + next/back.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // keep your other cards below
        AppCard(modifier = Modifier.fillMaxWidth()) {
            AppTextField(
                label = "Name",
                value = name,
                onValueChange = setName,
                placeholder = "Type something…",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            PrimaryButton(
                text = if (name.isBlank()) "Continue" else "Continue, $name",
                onClick = { /* no-op */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}