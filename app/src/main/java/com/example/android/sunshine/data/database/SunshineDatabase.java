package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * The entities contains a list of all Entity classes.
 * The version property is the version of the database
 */
@Database(entities = {WeatherEntry.class}, version = 1)
public abstract class SunshineDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "weather";

    public abstract WeatherDao weatherDao();

    // Ensure that only one instance of the database is running
    private static final Object LOCK = new Object();
    private static volatile SunshineDatabase sInstance;

    public static SunshineDatabase getInstance(Context context) {
        if(sInstance == null) {
            synchronized (LOCK) {
                if(sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SunshineDatabase.class, SunshineDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }


}
