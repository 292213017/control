package com.example.lhw.myapplication;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientSingle {
    private static Socket s = null;
    private SocketClientSingle()
    {

    }
    public static Socket getSocket(String ip,int port){
        try {
            s = new Socket(ip,port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
