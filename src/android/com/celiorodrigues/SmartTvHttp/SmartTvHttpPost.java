package com.celiorodrigues.SmartTvHttp;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.cordova.CallbackContext;

public class SmartTvHttpPost implements Runnable {
    
    private String url;
    private String body;
    private Map<String, String> headers;
    private CallbackContext callbackContext;
    
    private OkHttpClient client;
    
    public SmartTvHttpPost(String url, String body, Map<String, String> headers,
            CallbackContext callbackContext) {
        this.url = url;
        this.body = body;
        this.headers = headers;
        this.callbackContext = callbackContext;
        this.client = new OkHttpClient();
    }
    
    @Override
    public void run() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/atom+xml"), this.body);
        Request request = new Request.Builder()
                .url(url).addHeader("Content-Type", "application/atom+xml")
                .post(requestBody).build();

        try {
            Response response = client.newCall(request).execute();
            callbackContext.success(response.body().string());
        } catch(IOException e) {
            callbackContext.error(e.getMessage());
        }

    }
    
}
