package com.dvt.dvtweatherapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dvt.dvtweatherapp.R;
import com.dvt.dvtweatherapp.implementation.OpenWeatherMapHelper;
import com.dvt.dvtweatherapp.implementation.callback.CurrentWeatherCallback;
import com.dvt.dvtweatherapp.model.currentweather.CurrentWeather;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    TextView tvTemperature, tvMinTemperature, tvCurrent, tvMaxTemperature;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        //Instantiate Class With Your ApiKey As The Parameter
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(getString(R.string.OPEN_WEATHER_MAP_API_KEY));

        //Set Units
        helper.setUnits("Metric");
        helper.getCurrentWeatherByCityName("Cape Town", new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {

                tvTemperature.setText(currentWeather.getMain().getTemp() +" \u2103"+"\n" +
                        currentWeather.getWeather().get(0).getDescription());

                tvMinTemperature.setText(currentWeather.getMain().getTempMin()+" \u2103" +"\n" +"min");
                tvCurrent.setText(currentWeather.getMain().getTemp()+" \u2103" +"\n" +"max");
                tvMaxTemperature.setText(currentWeather.getMain().getTempMax()+" \u2103" +"\n" +"max");
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.v(TAG, throwable.getMessage());
            }
        });



        }
    private void initialize(){

        tvTemperature = findViewById(R.id.tvTemperature);
        tvMinTemperature = findViewById(R.id.tvMinTemperature);
        tvCurrent = findViewById(R.id.tvCurrent);
        tvMaxTemperature = findViewById(R.id.tvMaxTemperature);

    }



}
