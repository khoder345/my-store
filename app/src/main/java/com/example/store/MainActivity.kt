package com.example.store

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {

    private val vm: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val colors = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
            MaterialTheme(colorScheme = colors) {
                StoreScreen(vm = vm, onAction = { item -> onAppAction(item) })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.refreshInstallStates()
    }

    private fun onAppAction(item: AppItem) {
        if (item.state == InstallState.INSTALLED) {
            val intent = packageManager.getLaunchIntentForPackage(item.app.packageName)
            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "لا يمكن فتح هذا التطبيق", Toast.LENGTH_SHORT).show()
            }
        } else {
            // الخطوة التالية: التحميل + PackageInstaller
            Toast.makeText(this, "التحميل والتثبيت في الخطوة التالية", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun StoreScreen(vm: StoreViewModel, onAction: (AppItem) -> Unit) {
    val state by vm.state.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<String?>(null) }

    val visible = state.items.filter { item ->
        (category == null || item.app.category == category) &&
            (query.isBlank() ||
                item.app.name.contains(query, ignoreCase = true) ||
                item.app.developer.contains(query, ignoreCase = true))
    }

    Scaffold { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                placeholder = { Text("ابحث عن تطبيق أو لعبة") },
                singleLine = true
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = category == null,
                        onClick = { category = null },
                        label = { Text("الكل") }
                    )
                }
                items(state.categories, key = { it.id }) { c ->
                    FilterChip(
                        selected = category == c.id,
                        onClick = { category = c.id },
                        label = { Text(c.name) }
                    )
                }
            }

            state.message?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(12.dp)
                )
            }

            if (state.loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(visible, key = { it.app.packageName }) { item ->
                        AppRow(item, onAction)
                    }
                }
            }
        }
    }
}

@Composable
fun AppRow(item: AppItem, onAction: (AppItem) -> Unit) {
    val app = item.app
    val label = when (item.state) {
        InstallState.NOT_INSTALLED -> "تثبيت"
        InstallState.UPDATE_AVAILABLE -> "تحديث"
        InstallState.INSTALLED -> "فتح"
    }

    Card(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = app.iconUrl,
                contentDescription = app.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = app.developer,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${app.versionName} • ${formatSize(app.sizeBytes)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(Modifier.width(8.dp))
            FilledTonalButton(onClick = { onAction(item) }) { Text(label) }
        }
    }
}

fun formatSize(bytes: Long): String =
    if (bytes <= 0) "—" else "%.1f MB".format(bytes / 1_048_576.0)
