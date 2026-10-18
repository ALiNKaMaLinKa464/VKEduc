package com.hfad.vkeduc.presentation.appdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AppDetailsScreen(
    appId: String,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (ShowcaseApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Для ДЗ: захардкоженный appDetails. В реальном проекте — ViewModel.
    val appDetails = remember(appId) {
        AppDetails(
            id = appId,
            name = "Приложение #$appId",
            developer = "Разработчик",
            category = Category.APP,
            ageRating = 0,
            size = 50f,
            screenshotUrlList = listOf(
                "https://picsum.photos/seed/s1/800/450",
                "https://picsum.photos/seed/s2/800/450",
            ),
            iconUrl = "https://picsum.photos/seed/icon/200",
            description = "Описание приложения #$appId",
        )
    }

    AppDetailsContent(
        appDetails = appDetails,
        onBackClick = onBackClick,
        onShareClick = { },
        onInstallClick = { },
        onReadMoreClick = { },
        onDeveloperClick = { },
        onShowcaseAppClick = onShowcaseAppClick,
        modifier = modifier.fillMaxSize(),
    )
}