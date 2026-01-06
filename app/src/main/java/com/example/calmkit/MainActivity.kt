package com.example.calmkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calmkit.ui.screens.EmptyStateShowcase
import com.example.calmkit.ui.screens.FinanceDashboardShowcase
import com.example.calmkit.ui.screens.OnboardingShowcase
import com.example.calmkit.ui.screens.SettingsDarkShowcase
import com.example.calmkit.ui.screens.SettingsShowcase
import com.example.calmkit.ui.screens.UiKitGalleryScreen
import com.example.calmkit.ui.theme.CalmKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmKitTheme {
                UiKitGalleryScreen()
                // FinanceDashboardShowcase()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    UiKitGalleryScreen()
}

@Preview(showBackground = true)
@Composable
fun UiKitGalleryScreenPreview() {
    CalmKitTheme {
        UiKitGalleryScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceDashboardShowcasePreview() {
    CalmKitTheme {
        FinanceDashboardShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceDashboardDarkShowcasePreview() {
    CalmKitTheme(darkTheme = true) {
        FinanceDashboardShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyStateShowcasePreview() {
    CalmKitTheme {
        EmptyStateShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsShowcasePreview() {
    CalmKitTheme {
        SettingsShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsDarkShowcasePreview() {
    CalmKitTheme {
        SettingsDarkShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingShowcasePreview() {
    CalmKitTheme {
        OnboardingShowcase()
    }
}