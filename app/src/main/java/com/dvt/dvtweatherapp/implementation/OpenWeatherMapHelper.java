package com.dvt.dvtweatherapp.implementation;

import androidx.annotation.NonNull;

import com.dvt.dvtweatherapp.implementation.callback.CurrentWeatherCallback;
import com.dvt.dvtweatherapp.implementation.callback.ThreeHourForecastCallback;
import com.dvt.dvtweatherapp.model.currentweather.CurrentWeather;
import com.dvt.dvtweatherapp.model.forecast.ThreeHourForecast;
import com.dvt.dvtweatherapp.network.OpenWeatherMapClient;
import com.dvt.dvtweatherapp.network.OpenWeatherMapService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenWeatherMapHelper {

    private static final String APPID = "appId";
    private static final String UNITS = "units";
    private static final String LANGUAGE = "lang";
    private static final String QUERY = "q";
    private static final String CITY_ID = "id";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
    private static final String ZIP_CODE = "zip";

    private OpenWeatherMapService openWeatherMapService;
    private Map<String, String> options;


    @Deprecated
    public OpenWeatherMapHelper(){
        this("");
    }

    public OpenWeatherMapHelper(String apiKey){
        openWeatherMapService = OpenWeatherMapClient.getClient().create(OpenWeatherMapService.class);
        options = new HashMap<>();
        options.put(APPID, apiKey == null ? "" : apiKey);
    }


    //SETUP METHODS
    @Deprecated
    public void setApiKey(String appId){
        options.put(APPID, appId);
    }
    public void setUnits(String units){
        options.put(UNITS, units);
    }
    public void setLang(String lang) {
        options.put(LANGUAGE, lang);
    }


    private Throwable NoAppIdErrMessage() {
        return new Throwable("UnAuthorized. Please set a valid OpenWeatherMap API KEY.");
    }


    private Throwable NotFoundErrMsg(String str) {
        Throwable throwable = null;
        try {
            JSONObject obj = new JSONObject(str);
            throwable = new Throwable(obj.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (throwable == null){
            throwable = new Throwable("An unexpected error occurred.");
        }


        return throwable;
    }

//    CURRENT WEATHER METHODS
//    START

    //GET CURRENT WEATHER BY CITY NAME
    public void getCurrentWeatherByCityName(String city, final CurrentWeatherCallback callback){
        options.put(QUERY, city);
        openWeatherMapService.getCurrentWeatherByCityName(options).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                handleCurrentWeatherResponse(response, callback);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    //GET CURRENT WEATHER BY CITY ID
    public void getCurrentWeatherByCityID(String id, final CurrentWeatherCallback callback){
        options.put(CITY_ID, id);
        openWeatherMapService.getCurrentWeatherByCityID(options).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                handleCurrentWeatherResponse(response, callback);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });

    }

    //GET CURRENT WEATHER BY GEOGRAPHIC COORDINATES
    public void getCurrentWeatherByGeoCoordinates(double latitude, double longitude, final CurrentWeatherCallback callback){
        options.put(LATITUDE, String.valueOf(latitude));
        options.put(LONGITUDE, String.valueOf(longitude));
        openWeatherMapService.getCurrentWeatherByGeoCoordinates(options).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                handleCurrentWeatherResponse(response, callback);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    //GET CURRENT WEATHER BY ZIP CODE

    public void getCurrentWeatherByZipCode(String zipCode, final CurrentWeatherCallback callback){
        options.put(ZIP_CODE, zipCode);
        openWeatherMapService.getCurrentWeatherByZipCode(options).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                handleCurrentWeatherResponse(response, callback);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    private void handleCurrentWeatherResponse(Response<CurrentWeather> response, CurrentWeatherCallback callback){
        if (response.code() == HttpURLConnection.HTTP_OK){
            callback.onSuccess(response.body());
        }
        else if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
            callback.onFailure(NoAppIdErrMessage());
        }
        else{
            try {
                callback.onFailure(NotFoundErrMsg(response.errorBody().string()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    CURRENT WEATHER METHODS
//    END




//    THREE HOUR FORECAST METHODS
//    START

    //GET THREE HOUR FORECAST BY CITY NAME
    public void getThreeHourForecastByCityName(String city, final ThreeHourForecastCallback callback){
        options.put(QUERY, city);
        openWeatherMapService.getThreeHourForecastByCityName(options)
                .enqueue(new Callback<ThreeHourForecast>() {
                    @Override
                    public void onResponse(@NonNull Call<ThreeHourForecast> call, @NonNull Response<ThreeHourForecast> response) {
                        handleThreeHourForecastResponse(response, callback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ThreeHourForecast> call, @NonNull Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });

    }

    //GET THREE HOUR FORECAST BY CITY ID
    public void getThreeHourForecastByCityID(String id, final ThreeHourForecastCallback callback){
        options.put(CITY_ID, id);
        openWeatherMapService.getThreeHourForecastByCityID(options)
                .enqueue(new Callback<ThreeHourForecast>() {
                    @Override
                    public void onResponse(@NonNull Call<ThreeHourForecast> call, @NonNull Response<ThreeHourForecast> response) {
                        handleThreeHourForecastResponse(response, callback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ThreeHourForecast> call, @NonNull Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });

    }

    //GET THREE HOUR FORECAST BY GEO C0ORDINATES
    public void getThreeHourForecastByGeoCoordinates(double latitude, double longitude, final ThreeHourForecastCallback callback){
        options.put(LATITUDE, String.valueOf(latitude));
        options.put(LONGITUDE, String.valueOf(longitude));
        openWeatherMapService.getThreeHourForecastByGeoCoordinates(options)
                .enqueue(new Callback<ThreeHourForecast>() {
                    @Override
                    public void onResponse(@NonNull Call<ThreeHourForecast> call, @NonNull Response<ThreeHourForecast> response) {
                        handleThreeHourForecastResponse(response, callback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ThreeHourForecast> call, @NonNull Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });

    }

    //GET THREE HOUR FORECAST BY ZIP CODE
    public void getThreeHourForecastByZipCode(String zipCode, final ThreeHourForecastCallback callback){
        options.put(ZIP_CODE, zipCode);
        openWeatherMapService.getThreeHourForecastByZipCode(options)
                .enqueue(new Callback<ThreeHourForecast>() {
                    @Override
                    public void onResponse(@NonNull Call<ThreeHourForecast> call, @NonNull Response<ThreeHourForecast> response) {
                        handleThreeHourForecastResponse(response, callback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ThreeHourForecast> call, @NonNull Throwable throwable) {
                        callback.onFailure(throwable);
                    }
                });

    }

    private void handleThreeHourForecastResponse(Response<ThreeHourForecast> response, ThreeHourForecastCallback callback){
        if (response.code() == HttpURLConnection.HTTP_OK){
            callback.onSuccess(response.body());
        }
        else if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
            callback.onFailure(NoAppIdErrMessage());
        }
        else{
            try {
                callback.onFailure(NotFoundErrMsg(response.errorBody().string()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

