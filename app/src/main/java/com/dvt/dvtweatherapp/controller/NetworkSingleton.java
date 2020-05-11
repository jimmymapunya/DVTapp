package com.dvt.dvtweatherapp.controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkSingleton {

    /*Declare data members*/
    private static NetworkSingleton mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    /*Class constructor*/
    public NetworkSingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    /*RequestQueue function which checks if an instance of RequestQueue exists and returns it.*/
    private RequestQueue getRequestQueue() {

        if( mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    /*NetworkSingleton Instance function which checks if instance exists and returns it. */
    public static synchronized NetworkSingleton getInstance(Context context){

        if (mInstance == null){
            mInstance = new NetworkSingleton(context);
        }

        return mInstance;
    }

    /*Function to add requests to a queue of requests*/
    public <T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
