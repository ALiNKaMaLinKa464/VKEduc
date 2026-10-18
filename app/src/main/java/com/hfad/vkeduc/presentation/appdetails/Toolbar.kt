package com.hfad.vkeduc.presentation.appdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlin.math.roundToInt

// ============================================================
// Локальные модели (захардкожены, чтобы файл был самодостаточным)
// ============================================================

data class AppDetails(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val screenshotUrlList: List<String>,
    val iconUrl: String,
    val description: String,
)

enum class Category {
    APP, GAME, PRODUCTIVITY, SOCIAL, EDUCATION,
    ENTERTAINMENT, MUSIC, VIDEO, PHOTOGRAPHY, HEALTH,
    SPORTS, NEWS, BOOKS, BUSINESS, FINANCE,
    TRAVEL, MAPS, FOOD, SHOPPING, UTILITIES
}

data class SimilarApp(
    val id: String,
    val name: String,
    val iconUrl: String,
)

// Демо-данные для превью
private val sampleAppDetails = AppDetails(
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
)

private val sampleSimilarApps = List(5) { index ->
    SimilarApp(
        id = "similar-$index",
        name = "Похожее приложение $index",
        iconUrl = "https://picsum.photos/seed/sim$index/200",
    )
}

// ============================================================
// 1. Toolbar
// ============================================================

@Composable
internal fun Toolbar(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Назад",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        IconButton(onClick = onShareClick) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Поделиться",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarPreview() {
    MaterialTheme {
        Toolbar(onBackClick = {}, onShareClick = {})
    }
}

// ============================================================
// 2. AppDetailsHeader
// ============================================================

@Composable
fun AppDetailsHeader(
    appDetails: AppDetails,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = appDetails.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = getCategoryText(appDetails.category),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = appDetails.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(4.dp))
            Text(text = appDetails.developer, fontSize = 12.sp)
            Spacer(Modifier.height(4.dp))
            Row {
                Column(Modifier.width(IntrinsicSize.Max)) {
                    Text(
                        text = "${appDetails.ageRating}+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Возраст")
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(text = "${appDetails.size.roundToInt()} MB")
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Размер")
                }
            }
        }
    }
}

@Composable
private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложения"
    Category.GAME -> "Игры"
    Category.PRODUCTIVITY -> "Производительность"
    Category.SOCIAL -> "Социальные сети"
    Category.EDUCATION -> "Образование"
    Category.ENTERTAINMENT -> "Развлечения"
    Category.MUSIC -> "Музыка"
    Category.VIDEO -> "Видео"
    Category.PHOTOGRAPHY -> "Фотография"
    Category.HEALTH -> "Здоровье"
    Category.SPORTS -> "Спорт"
    Category.NEWS -> "Новости"
    Category.BOOKS -> "Книги"
    Category.BUSINESS -> "Бизнес"
    Category.FINANCE -> "Финансы"
    Category.TRAVEL -> "Путешествия"
    Category.MAPS -> "Карты"
    Category.FOOD -> "Еда"
    Category.SHOPPING -> "Покупки"
    Category.UTILITIES -> "Утилиты"
}

@Preview(showBackground = true)
@Composable
private fun AppDetailsHeaderPreview() {
    MaterialTheme {
        AppDetailsHeader(
            appDetails = sampleAppDetails,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

// ============================================================
// 3. InstallButton
// ============================================================

@Composable
fun InstallButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
    ) {
        Text(text = "Установить")
    }
}

@Preview(showBackground = true)
@Composable
private fun InstallButtonPreview() {
    MaterialTheme {
        InstallButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

// ============================================================
// 4. SimilarApps
// ============================================================

@Composable
fun SimilarApps(
    apps: List<SimilarApp>,
    onDownloadClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = "Похожие приложения",
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(Modifier.height(4.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(apps) { app ->
                SimilarAppItem(
                    app = app,
                    onDownloadClick = { onDownloadClick(app) },
                )
            }
        }
    }
}

@Composable
private fun SimilarAppItem(
    app: SimilarApp,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(128.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Text(text = app.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Button(
            onClick = onDownloadClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Скачать")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SimilarAppsPreview() {
    MaterialTheme {
        SimilarApps(
            apps = sampleSimilarApps,
            onDownloadClick = {},
        )
    }
}

// ============================================================
// 5. ScreenshotsList
// ============================================================

@Composable
fun ScreenshotsList(
    screenshotUrlList: List<String>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = "Скриншоты",
            modifier = Modifier.padding(contentPadding),
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = contentPadding,
            pageSpacing = 8.dp,
            state = rememberPagerState { screenshotUrlList.size },
        ) { index ->
            AsyncImage(
                model = screenshotUrlList[index],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(8.dp)),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenshotsListPreview() {
    MaterialTheme {
        ScreenshotsList(
            screenshotUrlList = sampleAppDetails.screenshotUrlList,
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
    }
}

// ============================================================
// 6. AppDescription
// ============================================================

@Composable
fun AppDescription(
    description: String,
    collapsed: Boolean,
    onReadMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Описание")
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            maxLines = if (collapsed) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (!collapsed) {
            TextButton(
                onClick = onReadMoreClick,
                contentPadding = PaddingValues(horizontal = 0.dp),
            ) {
                Text(
                    text = "Читать далее",
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppDescriptionCollapsedPreview() {
    MaterialTheme {
        AppDescription(
            description = sampleAppDetails.description,
            collapsed = false,
            onReadMoreClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppDescriptionExpandedPreview() {
    MaterialTheme {
        AppDescription(
            description = sampleAppDetails.description,
            collapsed = true,
            onReadMoreClick = {},
        )
    }
}

// ============================================================
// 7. Developer
// ============================================================

@Composable
fun Developer(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(text = name)
            Text(text = "Разработчик")
        }
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeveloperPreview() {
    MaterialTheme {
        Developer(
            name = "VK Play",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}