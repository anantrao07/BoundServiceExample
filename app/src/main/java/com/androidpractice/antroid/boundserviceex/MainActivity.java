package com.androidpractice.antroid.boundserviceex;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    BoundService boundService;
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this , BoundService.class);
        startService(intent);
        bindService(intent , boundServiceConnection,BIND_AUTO_CREATE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                Toast.makeText(MainActivity.this, String.valueOf(boundService.randomGenerator()),Toast.LENGTH_SHORT).show();

            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable,3000);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(boundServiceConnection);
            isBound = false;


        }
    }

    private ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            BoundService.MyBinder binderBridge = (BoundService.MyBinder) service ;
            boundService = binderBridge.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
            boundService= null;


        }
    };

}
