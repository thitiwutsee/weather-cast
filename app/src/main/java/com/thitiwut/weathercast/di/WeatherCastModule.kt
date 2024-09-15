package com.thitiwut.weathercast.di

import android.content.Context
import androidx.room.Room
import com.thitiwut.weathercast.data.local.WeatherDb
import com.thitiwut.weathercast.data.remote.WeatherCastApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherCastModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/").client(okHttpClient).build()
    }

    @Provides
    fun provideWeatherCastApi(retrofit: Retrofit): WeatherCastApiService {
        return retrofit.create(WeatherCastApiService::class.java)
    }

    @Provides
    fun buildHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("appid", "42f2c8b7d1109e02a02ca7cf37c407ef")
                .build()
            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): WeatherDb {
        return Room.databaseBuilder(
            appContext,
            WeatherDb::class.java,
            "weathercast_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRoomDao(database: WeatherDb) = database.dao
}