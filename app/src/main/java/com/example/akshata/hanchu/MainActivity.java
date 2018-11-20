package com.example.akshata.hanchu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements Constants {

    private ImageButton sendButton;
    private ImageButton receiveButton;
    private Button recievedFiles;
    private Button shareApp;

    public static final String tag = "abcdmain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doInitialWork();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestUserPermissions();
        }
        setListeners();
    }

    private void doInitialWork() {
        sendButton = findViewById(R.id.imagesend);
        receiveButton = findViewById(R.id.imagerecieve);
        recievedFiles = findViewById(R.id.files);
        shareApp = findViewById(R.id.shareapp);
    }

    private void setListeners() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestUserPermissions() {

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
            Log.i(tag, "[mainActivity] read permission granted");
        } else {
            Log.i(tag, "mainActivity] read permission denied");
        }
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(tag, "mainActivity] write permission granted");
        } else {
            Log.i(tag, "mainActivity] write permission denied");
        }
    }

    public void startSendActivity(View view) {
        Intent i = new Intent(this, SelectItemsActivity.class);
        startActivity(i);
    }

    public void startReceiveActivity(View view) {
        Intent i = new Intent(this, ReceiveActivity.class);
        startActivity(i);
    }

}
