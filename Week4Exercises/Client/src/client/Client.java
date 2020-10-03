/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author DinhSang
 */
public class Client {
    public static void main(String args[]) throws UnknownHostException, IOException {
        SocketClient client = new SocketClient("127.0.0.1", 5000, new SocketClient.Result() {
            @Override
            public void result(String mes) {
                System.err.println(mes);
            }
        });
        
        
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!line.equals("Over")) {
            line = scanner.nextLine();
            client.send(line);
        }
    }
}
