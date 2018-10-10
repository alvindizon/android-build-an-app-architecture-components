package com.example.android.sunshine.data.database;

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
    WeatherEntry getWeatherByDate(Date date);
}