package com.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class HandleSocket extends Thread {
    private Socket socket;
    private List<Socket> list;
    public HandleSocket(Socket socket,List<Socket> list){
        this.socket=socket;
        this.list=list;
    }
    public void run() {
        InetAddress address = socket.getInetAddress();
        String ip = address.getHostAddress();
        System.out.println(ip + "is online!");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "gbk"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String msg = ip + ":" + line;
                System.out.println(msg);
                SendToAll(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("122qwsq1wasx");
        }
    }
    public void SendToAll(String msg){
        synchronized(list){
            for(Socket s:list){
                if(s!=socket)
                {
                    try{
                        PrintStream ps = new PrintStream(s.getOutputStream());
                        ps.println(msg);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
