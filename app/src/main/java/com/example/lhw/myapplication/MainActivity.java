package com.example.lhw.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    private EditText editText_ip,editText_data;
    private OutputStream outputStream = null;
    private Socket socket = null;
    private String ip;
    private String data;
    private boolean socketStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        editText_ip = (EditText) findViewById(R.id.et_ip);
      //  editText_data = (EditText) findViewById(R.id.et_data);
        Button button_connect = (Button)findViewById(R.id.startID);
        Button button_1 = (Button)findViewById(R.id.con1);
        Button button_2 = (Button)findViewById(R.id.con2);
        Button button_3 = (Button)findViewById(R.id.con3);
        Button button_4 = (Button)findViewById(R.id.con4);
        Button button_5 = (Button)findViewById(R.id.con5);
        Button button_6 = (Button)findViewById(R.id.con6);
        Button button_7 = (Button)findViewById(R.id.con7);
        Button button_8 = (Button)findViewById(R.id.con8);
        Button button_9 = (Button)findViewById(R.id.con9);
        Button button_10 = (Button)findViewById(R.id.con10);
       // Button button_sent = (Button)findViewById(R.id.sendID);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="1";
                send(view);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="2";
                send(view);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="3";
                send(view);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="4";
                send(view);
            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="5";
                send(view);
            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="6";
                send(view);
            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="7";
                send(view);
            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="8";
                send(view);
            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="9";
                send(view);
            }
        });
        button_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data ="10";
                send(view);
            }
        });
        button_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect(view);
            }
        });
        /*button_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send(view);
            }
        });*/
    }


    public void connect(View view){

        ip = editText_ip.getText().toString();
        if(ip == null){
            Toast.makeText(MainActivity.this,"please input Server IP",Toast.LENGTH_SHORT).show();
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                if (!socketStatus) {

                    try {
                        socket = new Socket(ip,8000);
                        if(socket == null){
                        }else {
                            socketStatus = true;
                        }
                        outputStream = socket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        thread.start();
        Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();

    }


    public void send(View view){

        //data = editText_data.getText().toString();
        if(data == null){
            Toast.makeText(MainActivity.this,"please input Sending Data",Toast.LENGTH_SHORT).show();
        }else {
            //在后面加上 '\0' ,是为了在服务端方便我们去解析；
            data = data + '\0';
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if(socketStatus){
                    try {
                        outputStream.write(data.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        thread.start();

    }

    /*当客户端界面返回时，关闭相应的socket资源*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*关闭相应的资源*/
        try {
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}