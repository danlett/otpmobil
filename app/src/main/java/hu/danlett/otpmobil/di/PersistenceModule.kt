package hu.danlett.otpmobil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}