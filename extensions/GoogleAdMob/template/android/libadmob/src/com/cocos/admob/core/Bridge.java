package com.cocos.admob.core;

import android.util.Log;

import com.cocos.admob.AdServiceHub;
import com.cocos.admob.proto.VersionREQ;
import com.cocos.admob.proto.appopen.IsAdAvailableREQ;
import com.cocos.admob.proto.appopen.LoadAppOpenAdREQ;
import com.cocos.admob.proto.appopen.ShowAppOpenAdREQ;
import com.cocos.admob.proto.banner.DestroyBannerREQ;
import com.cocos.admob.proto.banner.LoadBannerREQ;
import com.cocos.admob.proto.banner.ShowBannerREQ;
import com.cocos.admob.proto.interstitial.LoadInterstitialAdREQ;
import com.cocos.admob.proto.interstitial.ShowInterstitialAdREQ;
import com.cocos.admob.proto.nativead.DestroyNativeAdREQ;
import com.cocos.admob.proto.nativead.LoadNativeAdREQ;
import com.cocos.admob.proto.rewarded.LoadRewardedAdREQ;
import com.cocos.admob.proto.rewarded.ShowRewardedAdREQ;
import com.cocos.admob.proto.rewardedinterstitial.LoadRewardedInterstitialAdREQ;
import com.cocos.admob.proto.rewardedinterstitial.ShowRewardedInterstitialAdREQ;
import com.cocos.lib.JsbBridge;
import com.cocos.lib.JsbBridgeWrapper;

public class Bridge {
    private static final String TAG = "Bridge";

    private Route route = new Route();

    private Codec codec = null;

    public Route getRoute() {
        return route;
    }

    public Bridge() {

    }

    public void init(AdServiceHub adServiceHub, Codec codec) {
        Log.d(TAG, "init");
        this.codec = codec;
        route.init(adServiceHub, codec);
        overwriteCallback();
    }

    public void destroy() {
        Log.d(TAG, "destroy");
        route.destroy();
    }

    private void overwriteCallback() {
        Log.d(TAG, "addCallback");
        addScriptEventListener(VersionREQ.class.getSimpleName());
        addScriptEventListener(LoadAppOpenAdREQ.class.getSimpleName());
        addScriptEventListener(ShowAppOpenAdREQ.class.getSimpleName());
        addScriptEventListener(IsAdAvailableREQ.class.getSimpleName());
        addScriptEventListener(LoadBannerREQ.class.getSimpleName());
        addScriptEventListener(ShowBannerREQ.class.getSimpleName());
        addScriptEventListener(DestroyBannerREQ.class.getSimpleName());
        addScriptEventListener(LoadInterstitialAdREQ.class.getSimpleName());
        addScriptEventListener(ShowInterstitialAdREQ.class.getSimpleName());
        addScriptEventListener(LoadNativeAdREQ.class.getSimpleName());
        addScriptEventListener(DestroyNativeAdREQ.class.getSimpleName());
        addScriptEventListener(LoadRewardedAdREQ.class.getSimpleName());
        addScriptEventListener(ShowRewardedAdREQ.class.getSimpleName());
        addScriptEventListener(LoadRewardedInterstitialAdREQ.class.getSimpleName());
        addScriptEventListener(ShowRewardedInterstitialAdREQ.class.getSimpleName());
    }

    private void addScriptEventListener(String eventName) {
        JsbBridgeWrapper.getInstance().addScriptEventListener(eventName, new JsbBridgeWrapper.OnScriptEventListener() {
            @Override
            public void onScriptEvent(String arg) {
                Log.d(TAG, "onScript: " + eventName + " | " + arg);
                route.dispatch(eventName, arg);
            }
        });
    }

    public void sendToScript(String arg0, Object src) {
        Log.d(TAG, "sendToScript, message: method: " + arg0);
        JsbBridge.sendToScript(arg0, codec.encode(src));
    }
}
