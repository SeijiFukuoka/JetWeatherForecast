package br.com.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import br.com.jetweatherforecast.data.WeatherDao
import br.com.jetweatherforecast.data.WeatherDatabase
import br.com.jetweatherforecast.network.WeatherApi
import br.com.jetweatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


}