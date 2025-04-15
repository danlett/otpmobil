package hu.danlett.otpmobil.domain.settings.gateway

interface LocalSettingsGateway {
	fun getSavedSearchQuery(): String?
	fun storeSearchQuery(query: String)
}