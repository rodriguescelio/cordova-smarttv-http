package com.celiorodrigues;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.cordova.CallbackContext;

public class SmartTvHttpGet implements Runnable {
    
    private String url;
    private Map<String, String> headers;
    private CallbackContext callbackContext;
    
    private OkHttpClient client;
    
    public SmartTvHttpGet(String url, Map<String, String> headers,
            CallbackContext callbackContext) {
        this.url = url;
        this.headers = headers;
        this.callbackContext = callbackContext;
        this.client = new OkHttpClient();
    }

    @Override
    public void run() {
        Request request = new Request.Builder().url(url)
                .addHeader("Content-Type", "application/atom+xml")
                .get().build();

        try {
            Response response = client.newCall(request).execute();
            callbackContext.success(response.body().string());
        } catch(IOException e) {
            callbackContext.error(e.getMessage());
        }
    }
    
}