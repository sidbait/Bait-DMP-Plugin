package com.dmp;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.nz.dmp.DMPManager;
import com.nz.dmp.DMPResponseObserver;

import android.util.Log;

import java.util.Date;

public class DmpPlugin extends CordovaPlugin {
  private static final String TAG = "DmpPlugin";

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    Log.d(TAG, "Initializing DmpPlugin");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
  
    if(action.equals("echo")) {
      String phrase = args.getString(0);
      // Echo back the first argument
      Log.d(TAG, phrase);
      final PluginResult result = new PluginResult(PluginResult.Status.OK, phrase);
      callbackContext.sendPluginResult(result);
    } else if(action.equals("getDate")) {
      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    } else if(action.equals("fetchDMP")) {

    this.cordova.getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {

        DMPManager.getInstance().init(cordova.getActivity(), new DMPResponseObserver() {
          @Override
          public void onResponse(String message) {

                Log.e(TAG, "Plugin-DMP-JSON" + message);
              final PluginResult result = new PluginResult(PluginResult.Status.OK, message);
              callbackContext.sendPluginResult(result);
             }
          });
        }
      });

    } else if(action.equals("initFlurry")) {
      final String flurryId = args.getString(0);
      this.cordova.getActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
  
          DMPManager.getInstance().initFlurry(flurryId);
          }
        });
  
      } else if(action.equals("logAppSessionEvent")) {
        final String appId = args.getString(0);
        this.cordova.getActivity().runOnUiThread(new Runnable() {
          @Override
          public void run() {
    
            DMPManager.getInstance().logAppSessionEvent(appId);
            }
          });
    
        }
    return true;
  }

}
