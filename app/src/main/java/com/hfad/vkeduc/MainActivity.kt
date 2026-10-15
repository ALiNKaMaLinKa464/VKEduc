package com.hfad.vkeduc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfad.vkeduc.ui.theme.VKEducTheme

// Модель данных приложения
data class AppItem(
    val name: String,
    val description: String,
    val category: String
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Список приложений") },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.Android,
                                    contentDescription = "Иконка приложения",
                                    modifier = Modifier.padding(start = 8.dp, end = 4.dp)
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { innerPadding ->
                    AppList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppList(modifier: Modifier = Modifier) {
    val apps = listOf(
        AppItem("Android", "Мобильная ОС от Google", "Система"),
        AppItem("IOS", "Мобильная ОС от Apple", "Система"),
        AppItem("Игра", "Популярная мобильная игра", "Развлечения"),
        AppItem("Telegram", "Мессенджер", "Общение"),
        AppItem("VK", "Социальная сеть", "Общение"),
        AppItem("YouTube", "Видеохостинг", "Медиа"),
        AppItem("Android", "Мобильная ОС от Google", "Система"),
        AppItem("IOS", "Мобильная ОС от Apple", "Система"),
        AppItem("Игра", "Популярная мобильная игра", "Развлечения"),
        AppItem("Telegram", "Мессенджер", "Общение"),
        AppItem("VK", "Социальная сеть", "Общение"),
        AppItem("YouTube", "Видеохостинг", "Медиа")
    )

    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(apps) { app ->
            AppCard(
                app = app,
                onClick = {
                    Toast.makeText(context, "Открыт: ${app.name}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun AppCard(
    app: AppItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Иконка приложения
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Android,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(32.dp)
                )

                // Image(
                //     painter = painterResource(id = R.drawable.picapp),
                //     contentDescription = app.name,
                //     modifier = Modifier.fillMaxSize(),
                //     contentScale = ContentScale.Crop
                // )
            }

            // Текстовый блок: название, описание, категория
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = app.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCardPreview() {
    VKEducTheme {
        AppCard(
            app = AppItem("Android", "Мобильная ОС от Google", "Система")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    VKEducTheme {
        AppList()
    }
}