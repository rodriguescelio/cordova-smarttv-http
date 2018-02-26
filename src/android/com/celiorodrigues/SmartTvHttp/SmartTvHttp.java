package com.celiorodrigues.SmartTvHttp;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartTvHttp extends CordovaPlugin {
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }
    
    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {

        String url = args.getString(0);
        
        if(action.equals("get")) {
            
            JSONObject headers = args.getJSONObject(1);
            HashMap<String, String> headersMap = getMapFromJSONObject(headers);

            SmartTvHttpGet get = new SmartTvHttpGet(url, headersMap, callbackContext);
            cordova.getThreadPool().execute(get);

        } else if(action.equals("post")) {
            
            String body = args.getString(1);
            JSONObject headers = args.getJSONObject(2);
            HashMap<String, String> headersMap = getMapFromJSONObject(headers);

            SmartTvHttpPost post = new SmartTvHttpPost(url, body, headersMap, callbackContext);
            cordova.getThreadPool().execute(post);

        } else {
            return false;
        }
        
        return true;

    }
    
    private HashMap<String, String> getMapFromJSONObject(JSONObject object) throws JSONException {
        HashMap<String, String> map = new HashMap<String, String>();
        Iterator<?> i = object.keys();
        while(i.hasNext()) {
            String key = (String) i.next();
            map.put(key, object.getString(key));
        }
        return map;
    }

}
