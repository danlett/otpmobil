package hu.danlett.otpmobil.persistence.gateway

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.danlett.otpmobil.domain.settings.gateway.LocalSettingsGateway
import javax.inject.Inject

class LocalSettingsGatewayImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : LocalSettingsGateway {
	private val sharedPreferences: SharedPreferences =
		context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

	override fun getSavedSearchQuery(): String? {
		return sharedPreferences.getString(PREF_KEY_SEARCH_QUERY, null)
	}

	override fun storeSearchQuery(query: String) {
		sharedPreferences.edit { putString(PREF_KEY_SEARCH_QUERY, query) }
	}

	companion object {
		private const val PREF_FILE_NAME = "settings_preferences"
		private const val PREF_KEY_SEARCH_QUERY = "search_query"
	}
}