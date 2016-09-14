package com.example.administrator.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg!=null&&msg.what==0){
               String s1= (String) msg.obj;
                Log.e("===","s1==="+s1);
                Messenger messenger1=msg.replyTo;
            }
        }
    };
    Messenger messenger=new Messenger(handler);

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.


        return  messenger.getBinder();
    }

    class MyBinder extends Binder {
        public MyBinder(){}
        public MyService MyService(){
            return MyService.this;
        }



    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
