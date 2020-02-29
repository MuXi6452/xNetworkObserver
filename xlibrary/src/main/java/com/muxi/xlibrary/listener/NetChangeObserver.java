package com.muxi.xlibrary.listener;
import com.muxi.xlibrary.type.NetType;
public interface NetChangeObserver {
    void onConnect(NetType netType);//有网络连接
    void onDisConnect();    //没有网络连接
}
