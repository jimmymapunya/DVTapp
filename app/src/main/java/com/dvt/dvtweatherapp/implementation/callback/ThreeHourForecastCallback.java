package com.dvt.dvtweatherapp.implementation.callback;

import com.dvt.dvtweatherapp.model.forecast.ThreeHourForecast;

public interface ThreeHourForecastCallback{
    void onSuccess(ThreeHourForecast threeHourForecast);
    void onFailure(Throwable throwable);
}