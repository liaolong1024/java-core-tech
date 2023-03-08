package com.javacore.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 套接字终止输出, 但是Socket本身保持连接
 *
 * @author ll
 */
public class HalfClosed {
    public static void main(String[] args) {
        try (Socket socket = new Socket("baidu.com", 80)) {
            Scanner scanner = new Scanner(socket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.print("hello");
            printWriter.flush();
            // 关闭输出流表示输出结束
            socket.shutdownOutput();
            // 读取响应数据
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
