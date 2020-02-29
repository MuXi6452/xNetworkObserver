package com.muxi.xlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.muxi.xlibrary.listener.NetChangeObserver;
import com.muxi.xlibrary.type.NetType;
import com.muxi.xlibrary.utils.Constants;
import com.muxi.xlibrary.utils.NetworkUtils;

public class NetStateReceiver extends BroadcastReceiver {
    private NetType netType;
    private NetChangeObserver listener;
    public NetStateReceiver(){
        netType = NetType.NONE; //初始化网络
    }
    public void setListener(NetChangeObserver listener){
        this.listener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent==null||intent.getAction()==null){
            Log.e(Constants.LOG_TAG,"异常...");
            return;
        }
        //处理广播事件
        if(intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)){
            Log.e(Constants.LOG_TAG,"网络发生改变");
            if(NetworkUtils.isNetworkAvailable()){//循环判断后有网络
                Log.e(Constants.LOG_TAG,"网络连接成功");
                netType = NetworkUtils.getNetType();//当前联网的具体网络类型
                if(listener!=null){
                    listener.onConnect(netType);
                }
            }else {
                Log.e(Constants.LOG_TAG,"网络连接失败");
                if(listener!=null){
                    listener.onDisConnect();
                }
            }
        }
    }
}
