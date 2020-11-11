package com.wjf.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wjf.aidlserver.IPersonListener;
import com.wjf.aidlserver.entity.MyPerson;

public class MainActivity extends AppCompatActivity {
    private boolean hasConnected = false;
    private Button id_connect;
    private Button id_add;
    private Button id_fetch;
    private Button id_nums;
    private IPersonListener iPersonListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_connect = findViewById(R.id.id_connect);
        id_add = findViewById(R.id.id_add);
        id_fetch = findViewById(R.id.id_fetch);
        id_nums = findViewById(R.id.id_nums);
        id_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindAIDLServer();
            }
        });
        id_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasConnected && iPersonListener != null) {
                    MyPerson myPerson = new MyPerson();
                    myPerson.setName("赢-坚持-不要沮丧");
                    myPerson.setAge(100);
                    myPerson.setHeight(180);
                    myPerson.setAddress("深圳市福永街道");
                    myPerson.setMarry(true);
                    try {
                        boolean b = iPersonListener.addPerson(myPerson);
                        Toast.makeText(MainActivity.this, b ? "添加成功！" : "添加失败", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        id_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasConnected && iPersonListener != null) {
                    try {
                        Toast.makeText(MainActivity.this, "获取到了：" + iPersonListener.getPersons(), Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        id_nums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasConnected && iPersonListener != null) {
                    try {
                        Toast.makeText(MainActivity.this, "数量：" + iPersonListener.personSize(), Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        bindAIDLServer();
    }

    ServiceConnection connect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            hasConnected = true;
            iPersonListener = IPersonListener.Stub.asInterface(service);
            Toast.makeText(MainActivity.this, "链接成功！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            hasConnected = false;
            // 如果断开了，就定时重连，赖得写了
        }
    };

    private void bindAIDLServer() {
        Intent intent = new Intent();
        intent.setPackage("com.wjf.aidlserver");
        intent.setAction("com.wjf.aidlserver.MyPerson");
        bindService(intent, connect, Context.BIND_AUTO_CREATE);
    }
}
