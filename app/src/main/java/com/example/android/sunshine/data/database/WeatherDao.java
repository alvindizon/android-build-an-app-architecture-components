package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

@Dao
public interface WeatherDao {
    /**
     * Method that inserts any number of WeatherEntry objects.
     * When Sunshine re-downloads forecasts, old weather forecasts are replaced by new ones.
     * @param weather WeatherEntry from the server.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weather);

    /**
     * Returns the weather forecast for a specific date.
     * @param date Desired date for a forecast
     * @return weather forecast for the specified date
     */
    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    /**
     * Returns the number of weather forecasts with dates
     * that occur after the specified data, inclusive
     * @param date
     * @return
     */
    @Query("SELECT COUNT(id) FROM weather WHERE date >= :date")
    int countAllFutureWeather(Date date);

    /**
     * Deletes data older than specified date
     * @param date
     */
    @Query("DELETE FROM weather WHERE date < :date")
    void deleteOldData(Date date);
}
