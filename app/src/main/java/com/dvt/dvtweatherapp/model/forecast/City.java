package com.dvt.dvtweatherapp.model.forecast;

import com.dvt.dvtweatherapp.model.common.Coord;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coord mCoord;

    @SerializedName("country")
    private String country;

    @SerializedName("population")
    private long population;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return mCoord;
    }

    public void setCoord(Coord coord) {
        mCoord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}