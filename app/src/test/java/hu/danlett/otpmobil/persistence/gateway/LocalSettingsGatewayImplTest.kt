package hu.danlett.otpmobil.persistence.gateway

import android.content.SharedPreferences
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalSettingsGatewayImplTest {
	private val sharedPreferences = mock<SharedPreferences>()
	private val localSettingsGateway = LocalSettingsGatewayImpl(sharedPreferences)

	@Test
	fun `given no saved query when reading saved query then return null`() {
		givenNoSavedQuery()

		val result = localSettingsGateway.getSavedSearchQuery()
		assertNull(result)
	}

	@Test
	fun `given saved query when reading saved query then return saved query`() {
		givenSavedQueryAvailable()

		val result = localSettingsGateway.getSavedSearchQuery()
		Assert.assertEquals(SAVED_QUERY, result)
	}

	@Test
	fun `when storing query then query is saved`() {
		givenSharedPreferencesCanSaveQuery()

		localSettingsGateway.storeSearchQuery(SAVED_QUERY)

		verify(sharedPreferences).edit()
		verify(SHARED_PREF_EDITOR).putString(eq(QUERY_KEY), eq(SAVED_QUERY))
	}

	private fun givenNoSavedQuery() {
		whenever(sharedPreferences.getString(eq(QUERY_KEY), anyOrNull())).thenReturn(null)
	}

	private fun givenSavedQueryAvailable() {
		whenever(sharedPreferences.getString(eq(QUERY_KEY), anyOrNull())).thenReturn(SAVED_QUERY)
	}

	private fun givenSharedPreferencesCanSaveQuery() {
		whenever(sharedPreferences.edit()).thenReturn(SHARED_PREF_EDITOR)
		whenever(SHARED_PREF_EDITOR.putString(eq(QUERY_KEY), any())).thenReturn(SHARED_PREF_EDITOR)
	}

	companion object {
		private val SHARED_PREF_EDITOR = mock<SharedPreferences.Editor>()
		private const val QUERY_KEY = "search_query"
		private const val SAVED_QUERY = "testQuery"
	}
}