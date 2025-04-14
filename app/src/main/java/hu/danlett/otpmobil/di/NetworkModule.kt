package hu.danlett.otpmobil.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.danlett.otpmobil.domain.Constants.FLICKR_BASE_URL
import hu.danlett.otpmobil.domain.picture.gateway.NetworkPictureGateway
import hu.danlett.otpmobil.network.api.ApiFlickr
import hu.danlett.otpmobil.network.picture.gateway.NetworkPictureGatewayImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkPictureGateway(networkPictureGatewayImpl: NetworkPictureGatewayImpl):
            NetworkPictureGateway {
        return networkPictureGatewayImpl
    }


    @Provides
    @Singleton
    fun provideFlickrApi(retrofit: Retrofit): ApiFlickr {
        return retrofit.create(ApiFlickr::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FLICKR_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}