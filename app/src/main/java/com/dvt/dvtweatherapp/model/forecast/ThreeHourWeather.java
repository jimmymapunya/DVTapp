package com.dvt.dvtweatherapp.model.forecast;

import com.dvt.dvtweatherapp.model.common.Clouds;
import com.dvt.dvtweatherapp.model.common.Main;
import com.dvt.dvtweatherapp.model.common.Sys;
import com.dvt.dvtweatherapp.model.common.Weather;
import com.dvt.dvtweatherapp.model.common.Wind;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThreeHourWeather {

    @SerializedName("dt")
    private Long dt;

    @SerializedName("main")
    private Main mMain;

    @SerializedName("weather")
    private List<Weather> mWeatherArray;

    @SerializedName("clouds")
    private Clouds mClouds;

    @SerializedName("wind")
    private Wind mWind;

    @SerializedName("rain")
    private Rain mRain;

    @SerializedName("snow")
    private Snow mSnow;

    @SerializedName("sys")
    private Sys mSys;

    @SerializedName("dt_txt")
    private String dtTxt;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        mMain = main;
    }

    public List<Weather> getWeatherArray() {
        return mWeatherArray;
    }

    public void setWeatherArray(List<Weather> weatherArray) {
        mWeatherArray = weatherArray;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public void setClouds(Clouds clouds) {
        mClouds = clouds;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public Rain getRain() {
        return mRain;
    }

    public void setRain(Rain rain) {
        mRain = rain;
    }

    public Snow getSnow() {
        return mSnow;
    }

    public void setSnow(Snow snow) {
        mSnow = snow;
    }

    public Sys getSys() {
        return mSys;
    }

    public void setSys(Sys sys) {
        mSys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
