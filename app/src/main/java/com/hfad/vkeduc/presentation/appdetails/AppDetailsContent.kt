package com.hfad.vkeduc.presentation.appdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AppDetailsContent(
    appDetails: AppDetails,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onShowcaseAppClick: (ShowcaseApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = onShareClick,
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            appDetails = appDetails,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        ScreenshotsList(
            screenshotUrlList = appDetails.screenshotUrlList,
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = appDetails.description,
            collapsed = true,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = appDetails.developer,
            onClick = onDeveloperClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )

        // === ВИТРИНА ===
        Spacer(Modifier.height(16.dp))
        ShowcaseSection(
            apps = showcaseApps,
            onAppClick = onShowcaseAppClick,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
    }
}

// ---------- Модель и данные витрины ----------
data class ShowcaseApp(
    val id: String,
    val name: String,
    val iconUrl: String,
)

private val showcaseApps = listOf(
    ShowcaseApp("sc-1", "Витрина 1", "https://picsum.photos/seed/1/200"),
    ShowcaseApp("sc-2", "Витрина 2", "https://picsum.photos/seed/2/200"),
    ShowcaseApp("sc-3", "Витрина 3", "https://picsum.photos/seed/3/200"),
    ShowcaseApp("sc-4", "Витрина 4", "https://picsum.photos/seed/4/200"),
    ShowcaseApp("sc-5", "Витрина 5", "https://picsum.photos/seed/5/200"),
    ShowcaseApp("sc-6", "Витрина 6", "https://picsum.photos/seed/6/200"),
    ShowcaseApp("sc-7", "Витрина 7", "https://picsum.photos/seed/7/200"),
    ShowcaseApp("sc-8", "Витрина 8", "https://picsum.photos/seed/8/200"),
    ShowcaseApp("sc-9", "Витрина 9", "https://picsum.photos/seed/9/200"),
)

// ---------- Блок «Витрина» ----------
@Composable
fun ShowcaseSection(
    apps: List<ShowcaseApp>,
    onAppClick: (ShowcaseApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = apps.chunked(3) // 3 страницы × 3 приложения
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(modifier = modifier) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
        ) { pageIndex ->
            val pageApps = pages[pageIndex]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                pageApps.forEach { app ->
                    ShowcaseAppItem(
                        app = app,
                        onClick = { onAppClick(app) },
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(3 - pageApps.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

// ---------- Карточка витрины ----------
@Composable
private fun ShowcaseAppItem(
    app: ShowcaseApp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = app.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

// ---------- Preview ----------
@Preview(showBackground = true)
@Composable
private fun AppDetailsContentPreview() {
    MaterialTheme {
        AppDetailsContent(
            appDetails = AppDetails(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
                name = "Гильдия Героев: Экшен ММО РПГ",
                developer = "VK Play",
                category = Category.GAME,
                ageRating = 12,
                size = 223.7f,
                screenshotUrlList = listOf(
                    "https://picsum.photos/seed/s1/800/450",
                    "https://picsum.photos/seed/s2/800/450",
                    "https://picsum.photos/seed/s3/800/450",
                ),
                iconUrl = "https://picsum.photos/seed/icon/200",
                description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и сразите мастера подземелья!",
            ),
            onBackClick = {},
            onShareClick = {},
            onInstallClick = {},
            onReadMoreClick = {},
            onDeveloperClick = {},
            onShowcaseAppClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}