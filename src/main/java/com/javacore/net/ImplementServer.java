package com.javacore.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author ll
 */
public class ImplementServer {
    public static void main(String[] args) throws IOException {
        // SimpleServer();

        ThreadServer();
    }

    /**
     * 一个线程处理一个请求
     * @throws IOException
     */
    private static void ThreadServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8188);
        int i = 1;
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println(i);
            ThreadHandler threadHandler = new ThreadHandler(accept);
            Thread thread = new Thread(threadHandler);
            thread.start();
            i++;
        }
    }

    /**
     * 简单服务器实现
     * @throws IOException
     */
    private static void SimpleServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8189);
        try (Socket accept = serverSocket.accept()) {
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {
                printWriter.println("Hello! Enter BYE to exit.");
            }
        }
    }

    static class ThreadHandler implements Runnable{
        private Socket socket;

        public ThreadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (OutputStream outputStream = socket.getOutputStream();
                 PrintWriter printWriter = new PrintWriter(outputStream, true);) {
                printWriter.println("Hello!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
