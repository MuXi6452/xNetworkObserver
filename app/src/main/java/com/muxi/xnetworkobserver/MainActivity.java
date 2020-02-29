package com.muxi.xnetworkobserver;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.muxi.xlibrary.NetworkManager;
import com.muxi.xlibrary.listener.NetChangeObserver;
import com.muxi.xlibrary.type.NetType;
import com.muxi.xlibrary.utils.Constants;
public class MainActivity extends AppCompatActivity implements NetChangeObserver {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getDefault().setListener(this);
    }

    @Override
    public void onConnect(NetType netType) {
        String name = netType.name();
        Log.e(Constants.LOG_TAG,"MainActivity >>> "+ name);

    }

    @Override
    public void onDisConnect() {
        Log.e(Constants.LOG_TAG,"MainActivity >>> 没有网络连接");
    }

}
