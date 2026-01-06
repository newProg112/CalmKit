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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import com.example.calmkit.ui.theme.AccentSoft

@Composable
fun SettingsShowcase() {
    var notifications by remember { mutableStateOf(true) }
    var reminders by remember { mutableStateOf(false) }
    var faceId by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge
        )

        // Profile / account card
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AccentSoft)
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Adam", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Premium · Sync enabled",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                TextButton(onClick = { /* showcase */ }) { Text("Edit") }
            }
        }

        // Preferences
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text("Preferences", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))

            SettingToggleRow(
                title = "Notifications",
                subtitle = "Get updates for exports and reminders.",
                checked = notifications,
                onCheckedChange = { notifications = it }
            )

            Divider(Modifier.padding(vertical = 10.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            SettingToggleRow(
                title = "Smart reminders",
                subtitle = "Nudge you when expenses look incomplete.",
                checked = reminders,
                onCheckedChange = { reminders = it }
            )

            Divider(Modifier.padding(vertical = 10.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            SettingToggleRow(
                title = "Face ID / Biometrics",
                subtitle = "Require unlock to open the app.",
                checked = faceId,
                onCheckedChange = { faceId = it }
            )
        }

        // Danger zone
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text("Danger zone", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "Actions here are permanent. Keep them hard to tap by accident.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(12.dp))

            DangerRow(
                title = "Clear all data",
                subtitle = "Deletes expenses, mileage and exports.",
                action = "Clear",
                onClick = { /* showcase */ }
            )

            Divider(Modifier.padding(vertical = 10.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            DangerRow(
                title = "Delete account",
                subtitle = "Removes your account and synced data.",
                action = "Delete",
                onClick = { /* showcase */ }
            )
        }

        // Footer hint
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 0.dp
        ) {
            Text(
                text = "Tip: In CalmKit, every screen is a reusable pattern. Keep it minimal and screenshot-friendly.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(14.dp)
            )
        }
    }
}

@Composable
private fun SettingToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(2.dp))
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun DangerRow(
    title: String,
    subtitle: String,
    action: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(2.dp))
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        TextButton(onClick = onClick) {
            Text(
                action,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}