package com.muxi.xnetworkobserver;
import android.app.Application;

import com.muxi.xlibrary.NetworkManager;

public class xApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getDefault().init(this);
    }
}
