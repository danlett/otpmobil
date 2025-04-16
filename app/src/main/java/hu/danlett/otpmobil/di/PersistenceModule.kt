package hu.danlett.otpmobil.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.danlett.otpmobil.domain.settings.gateway.LocalSettingsGateway
import hu.danlett.otpmobil.persistence.gateway.LocalSettingsGatewayImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	@Provides
	@Singleton
	fun provideLocalSettingsGateway(localSettingsGatewayImpl: LocalSettingsGatewayImpl):
			LocalSettingsGateway {
		return localSettingsGatewayImpl
	}

	@Provides
	@Singleton
	fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
		return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
	}

	private const val PREF_FILE_NAME = "settings_preferences"
}