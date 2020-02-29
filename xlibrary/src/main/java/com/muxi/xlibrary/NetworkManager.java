package com.muxi.xlibrary;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.muxi.xlibrary.listener.NetChangeObserver;
import com.muxi.xlibrary.utils.Constants;

public class NetworkManager {
    private static volatile NetworkManager instance;
    private NetStateReceiver receiver;
    private Application application;

    public NetworkManager() {
        receiver = new NetStateReceiver();
    }

    public static NetworkManager getDefault(){
        if(instance==null){
            synchronized (NetworkManager.class){
                if(instance==null){
                    instance=new NetworkManager();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        return application;
    }

    public void init(Application application){
        this.application = application;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(receiver,intentFilter);
    }

    public void setListener(NetChangeObserver listener) {
        receiver.setListener(listener);
    }
}
