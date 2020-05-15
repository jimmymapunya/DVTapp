package com.dvt.dvtweatherapp.implementation.callback;

import com.dvt.dvtweatherapp.model.currentweather.CurrentWeather;

public interface CurrentWeatherCallback{
    void onSuccess(CurrentWeather currentWeather);
    void onFailure(Throwable throwable);
}
