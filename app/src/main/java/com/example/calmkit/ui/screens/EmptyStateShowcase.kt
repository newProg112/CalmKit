package com.example.calmkit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calmkit.ui.components.AppCard
import com.example.calmkit.ui.components.PrimaryButton
import com.example.calmkit.ui.theme.AccentSoft

@Composable
fun EmptyStateShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Empty State",
            style = MaterialTheme.typography.headlineLarge
        )

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // “Illustration” placeholder (Pinterest-style block)
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(
                            color = AccentSoft,
                            shape = RoundedCornerShape(28.dp)
                        )
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "No transactions yet",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "When you add your first expense, it’ll show up here with a clean, calm summary.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    text = "Add expense",
                    onClick = { /* showcase */ },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(10.dp))

                // Secondary action (simple text-button style)
                androidx.compose.material3.TextButton(
                    onClick = { /* showcase */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Import from CSV")
                }
            }
        }

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Text("Design notes", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "• One clear message\n• One primary action\n• One optional alternative\n• Soft illustration placeholder\n• Lots of whitespace",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}