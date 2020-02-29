package com.muxi.xlibrary.utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.muxi.xlibrary.NetworkManager;
import com.muxi.xlibrary.type.NetType;
public class NetworkUtils {
    private static ConnectivityManager mConnManager;
    /*
    *   网络是否可用
    * */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvailable(){
        mConnManager = (ConnectivityManager)NetworkManager.getDefault().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(mConnManager==null) return false;
        NetworkInfo[] infos = mConnManager.getAllNetworkInfo();
        if(infos!=null){
            for(NetworkInfo info : infos){
                if(info.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

    /*
     *   获取当前网络类型
     * */
    @SuppressLint("MissingPermission")
    public static NetType getNetType(){
        if(mConnManager==null) return NetType.NONE;
        NetworkInfo info = mConnManager.getActiveNetworkInfo();
        if(info==null){
            return NetType.NONE;
        }
        int nType = info.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            if(info.getExtraInfo().toLowerCase().equals("cmnet")){
                return NetType.CMNET;
            }else {
                return NetType.CMWAP;
            }
        }else if(nType==ConnectivityManager.TYPE_WIFI){
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

    /*
    *   打开网络设置界面
    * */
    public static void openSetting(Context context,int requestCode){
        Intent i = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings","com.android.setting.wirelessSettings");
        i.setComponent(cm);
        i.setAction("android.intent.action.VIEW");
        ((Activity)context).startActivityForResult(i,requestCode);
    }
}
