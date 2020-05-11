package com.dvt.dvtweatherapp.controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dvt.dvtweatherapp.Interface.INetworkResponseListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkEngine {

    private static final String WeatherURL = "https://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=667d03c9388171e1fb60e7e91947d0cd";

    public static void APICall (Context context, final INetworkResponseListener iNetworkResponseListener){

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, WeatherURL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        iNetworkResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iNetworkResponseListener.onError(error);
                    }
                }){

            @Override
            public Map<String, String> getHeaders() {

                HashMap<String,String> headers = new HashMap<>();

                return headers;
            }
        };

        NetworkSingleton.getInstance(context).addRequestQueue(request);
    }
}