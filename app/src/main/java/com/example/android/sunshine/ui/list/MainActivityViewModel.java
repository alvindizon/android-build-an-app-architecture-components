package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.ListViewWeatherEntry;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.List;

/**
 * {@link ViewModel} for {@link MainActivity}
 */
public class MainActivityViewModel extends ViewModel {

    private LiveData<List<ListViewWeatherEntry>> mWeatherEntryList;
    private final SunshineRepository mRepository;

    public MainActivityViewModel(SunshineRepository mRepository) {
        this.mRepository = mRepository;
        mWeatherEntryList = mRepository.getLatestWeatherForecasts();
    }

    public LiveData<List<ListViewWeatherEntry>> getWeatherEntryList(){
        return mWeatherEntryList;
    }
}
