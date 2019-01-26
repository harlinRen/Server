package com.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int SERVER_PORT = 12345;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try{
            //   List<User> list = new ArrayList<User>();
            ArrayList<Socket> list = new ArrayList<Socket>();
            System.out.println("Sever:Connecting...");
            ServerSocket severSocket=new ServerSocket(SERVER_PORT);
            while(true)
            {	// 不断地读取客户端发过来的信息
                Socket clientSocket=severSocket.accept();
                System.out.println("Server:Receiving...");

                list.add(clientSocket);
                HandleSocket thread = new HandleSocket(clientSocket, list);
                thread.start();

            }
        }

        catch(Exception e)
        {
            System.out.println("Server:Error");
            e.printStackTrace();
        }
    }
}

