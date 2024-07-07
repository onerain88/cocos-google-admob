package com.cocos.admob;

import android.content.Context;
import android.util.Log;

import com.cocos.lib.CocosActivity;
import com.cocos.service.SDKWrapper;

public class AdMobService implements SDKWrapper.SDKInterface {
    static final String TAG = "AdMobService";

    @Override
    public void init(Context context) {
        Log.i(TAG, "AdMobService.init");
        AdServiceHub.instance().init((CocosActivity) context);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "AdMobService.onDestroy");
        AdServiceHub.instance().destroy();
    }
}
