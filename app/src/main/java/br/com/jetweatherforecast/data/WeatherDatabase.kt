package br.com.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.jetweatherforecast.model.Favorite
import br.com.jetweatherforecast.model.Unit

@Database(
    entities = [Favorite::class, Unit::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}