package com.example.akshata.hanchu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.Toast;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Activity mActivity;
    String instance;

    public static final String tag = "abcdbroadcastreceiver";

    public WifiDirectBroadcastReceiver() {

    }

    public WifiDirectBroadcastReceiver(WifiP2pManager mManager, WifiP2pManager.Channel mChannel,
                                       Activity mActivity, String instance) {
        this.mManager = mManager;
        this.mChannel = mChannel;
        this.mActivity = mActivity;
        this.instance = instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.i(tag, "hit onreceive in wdbr");

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "Wifi is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Wifi is OFF", Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            //do something
            if (mManager != null) {
                if (instance.equals("sender"))
                    mManager.requestPeers(mChannel, ((SendActivity) mActivity).peerListListener);
                else if (instance.equals("receiver"))
                    mManager.requestPeers(mChannel, ((ReceiveActivity) mActivity).peerListListener);
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            //do something
            if (mManager == null) {
                return;
            }
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {
                if (instance.equals("sender"))
                    mManager.requestConnectionInfo(mChannel, ((SendActivity) mActivity)
                            .connectionInfoListener);
                else if (instance.equals("receiver"))
                    mManager.requestConnectionInfo(mChannel, ((ReceiveActivity) mActivity).connectionInfoListener);
            }

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            //do something
        }

    }
}
