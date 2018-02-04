package com.androidpractice.antroid.boundserviceex;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BoundService extends Service {
    public BoundService() {
    }

    private final IBinder localBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {


        return localBinder;

    }


    public int randomGenerator(){

        Random randomNumber = new Random();

        int luckyNumber = randomNumber.nextInt();

        return luckyNumber;

    }

    public class MyBinder extends Binder {

      public   BoundService getService() {
            return BoundService.this;

        }
    }
}
