package com.example.store

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.data.CATALOG_URL
import com.example.store.data.Catalog
import com.example.store.data.Category
import com.example.store.data.StoreApi
import com.example.store.data.StoreApp
import com.example.store.data.catalogJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class InstallState { NOT_INSTALLED, UPDATE_AVAILABLE, INSTALLED }

data class AppItem(val app: StoreApp, val state: InstallState)

data class StoreUiState(
    val loading: Boolean = true,
    val items: List<AppItem> = emptyList(),
    val categories: List<Category> = emptyList(),
    val message: String? = null
)

private class LoadResult(val catalog: Catalog, val message: String?)

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(StoreUiState())
    val state: StateFlow<StoreUiState> = _state.asStateFlow()

    private var apps: List<StoreApp> = emptyList()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, message = null) }

            val result = withContext(Dispatchers.IO) {
                try {
                    LoadResult(StoreApi.service.getCatalog(CATALOG_URL), null)
                } catch (e: Exception) {
                    try {
                        LoadResult(
                            readSample(),
                            "تعذّر الاتصال بالخادم، تُعرض بيانات تجريبية محلية."
                        )
                    } catch (e2: Exception) {
                        LoadResult(Catalog(), "تعذّر تحميل القائمة.")
                    }
                }
            }

            apps = result.catalog.apps
            _state.value = StoreUiState(
                loading = false,
                items = apps.map { a -> AppItem(a, stateOf(a)) },
                categories = result.catalog.categories,
                message = result.message
            )
        }
    }

    /** تُستدعى عند العودة للتطبيق لتحديث أزرار (تثبيت / تحديث / فتح). */
    fun refreshInstallStates() {
        _state.update { s ->
            s.copy(items = apps.map { a -> AppItem(a, stateOf(a)) })
        }
    }

    private fun readSample(): Catalog {
        val text = getApplication<Application>().assets
            .open("apps.json")
            .bufferedReader()
            .use { it.readText() }
        return catalogJson.decodeFromString(Catalog.serializer(), text)
    }

    private fun stateOf(app: StoreApp): InstallState {
        val pm = getApplication<Application>().packageManager
        return try {
            val installed = versionCodeOf(pm, app.packageName)
            if (installed < app.versionCode) InstallState.UPDATE_AVAILABLE
            else InstallState.INSTALLED
        } catch (e: PackageManager.NameNotFoundException) {
            InstallState.NOT_INSTALLED
        }
    }

    @Suppress("DEPRECATION")
    private fun versionCodeOf(pm: PackageManager, packageName: String): Long {
        val info = pm.getPackageInfo(packageName, 0)
        return if (Build.VERSION.SDK_INT >= 28) info.longVersionCode
        else info.versionCode.toLong()
    }
}
