package com.dvt.dvtweatherapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.dvt.dvtweatherapp.R;
import com.dvt.dvtweatherapp.controller.ForecastAdapter;
import com.dvt.dvtweatherapp.implementation.OpenWeatherMapHelper;
import com.dvt.dvtweatherapp.implementation.callback.CurrentWeatherCallback;
import com.dvt.dvtweatherapp.implementation.callback.ThreeHourForecastCallback;
import com.dvt.dvtweatherapp.model.ForecastData;
import com.dvt.dvtweatherapp.model.currentweather.CurrentWeather;
import com.dvt.dvtweatherapp.model.forecast.ThreeHourForecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView tvTemperature, tvMinTemperature, tvCurrent, tvMaxTemperature;

    private  ListView listForecast;
    private Context context;

    private ArrayList<ForecastData> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

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

        helper.getThreeHourForecastByCityName("Cape Town", new ThreeHourForecastCallback() {
            @Override
            public void onSuccess(ThreeHourForecast threeHourForecast)  {

                for (int x=0; x < threeHourForecast.getList().size(); x++){

                    //Convert date and time to a day
                    String date_ = threeHourForecast.getList().get(x).getDtTxt();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = null;
                    try {
                        date = format.parse(date_);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dayOfTheWeek = (String) DateFormat.format("EEEE", date);

                    dataModels.add(new ForecastData(dayOfTheWeek,  android.R.drawable.sym_def_app_icon, threeHourForecast.getList().get(x).getMain().getTemp()));

                }

                ForecastAdapter adapter = new ForecastAdapter(context, dataModels);
                listForecast.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        }
    private void initialize(){

        //TextView
        tvTemperature = findViewById(R.id.tvTemperature);
        tvMinTemperature = findViewById(R.id.tvMinTemperature);
        tvCurrent = findViewById(R.id.tvCurrent);
        tvMaxTemperature = findViewById(R.id.tvMaxTemperature);

        //ListView
        listForecast = findViewById(R.id.listForecast);

        //List
        dataModels= new ArrayList<>();

    }



}
