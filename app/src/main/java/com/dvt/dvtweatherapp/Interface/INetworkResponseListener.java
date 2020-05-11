package com.dvt.dvtweatherapp.Interface;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface INetworkResponseListener {
    void onResponse(JSONObject response);
    void onError(VolleyError error);
}
