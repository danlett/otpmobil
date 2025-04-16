package hu.danlett.otpmobil.persistence.gateway

import android.content.SharedPreferences
import androidx.core.content.edit
import hu.danlett.otpmobil.domain.settings.gateway.LocalSettingsGateway
import javax.inject.Inject

class LocalSettingsGatewayImpl @Inject constructor(
	private val sharedPreferences: SharedPreferences
) : LocalSettingsGateway {

	override fun getSavedSearchQuery(): String? {
		return sharedPreferences.getString(PREF_KEY_SEARCH_QUERY, null)
	}

	override fun storeSearchQuery(query: String) {
		sharedPreferences.edit { putString(PREF_KEY_SEARCH_QUERY, query) }
	}

	companion object {
		private const val PREF_KEY_SEARCH_QUERY = "search_query"
	}
}