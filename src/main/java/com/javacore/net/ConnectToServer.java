package com.javacore.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author ll
 */
public class ConnectToServer {
    public static void main(String[] args) throws IOException {
        ConnectToServer con = new ConnectToServer();
        /*
         telnet: 控制面板->程序->启用或关闭windows功能->选择telnet客户端  即可开启windows的telnet
         */
        // 获取当前铯原子钟时间:  telnet time-a.nist.gov 13
        //

        con.connectServerUsingJava();
        con.connectAndSetTimeout();
        con.connectUseInetAddress();
    }

    /**
     * 连接服务器获取当前铯原子钟时间
     */
    public void connectServerUsingJava() throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13);) {
            Scanner in = new Scanner(s.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }

    /**
     *  设置超时时间
     */
    public void connectAndSetTimeout() throws IOException {
        try (Socket s = new Socket()) {
            int timeout = 10000;
            // 由于构造函数 Socket("time-a.nist.gov", 13)在建立连接前会一直阻塞, 所以我们需要通过connect方法设置超时时间
            s.connect(new InetSocketAddress("time-a.nist.gov", 13), timeout);
            // 在数据可读之前, 读操作会一直阻塞, 所以我们也需要设置超时时间(读请求超时时间)
            s.setSoTimeout(timeout);

            InputStream socketStream = s.getInputStream();
            Scanner in = new Scanner(socketStream, String.valueOf(StandardCharsets.UTF_8));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
            in.close();
        }
    }

    /**
     * 当给定的地址是域名时, 需要使用InetAddress类来转换为IP地址
     */
    public void connectUseInetAddress() throws UnknownHostException {
        // 通过域名获取某台服务器的ip
        InetAddress address = InetAddress.getByName("time-a.nist.gov");
        InetAddress[] allByName = InetAddress.getAllByName("google.com");

        System.out.println(address.getHostAddress());
        byte[] address1 = address.getAddress();
        for (InetAddress inetAddress : allByName) {
            System.out.println(inetAddress.getHostAddress());
        }

        // 获取本地主机地址(局域网)
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
    }

}
