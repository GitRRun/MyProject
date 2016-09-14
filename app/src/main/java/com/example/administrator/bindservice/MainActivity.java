package com.example.administrator.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ServiceConnection serviceConnection;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    Messenger Messenger_server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                /*MyService.MyBinder myBinder= (MyService.MyBinder) service;
              int num=  myBinder.sum(1,1);
                Log.e("==","num=="+num);*/
                //服务端的信使
                 Messenger_server=new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }
//创建客户端的信使
    Messenger messenger=new Messenger(handler);
    public void start(View view){
        if (messenger!=null){
            Message mes=new Message();
            mes.what=0;
            mes.obj="客户端发送的消息";
            mes.replyTo=messenger;
            try {
                Messenger_server.send(mes);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent=new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
