/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DinhSang
 */
public class SocketServer {

    private Socket socket = null;
    private ServerSocket server = null;
    BufferedWriter out = null;
    BufferedReader in = null;

    public SocketServer(int port) {
        openSocket(port);
        listening();
    }

    private void openSocket(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeSocket() {
        try {
            in.close();
            out.close();
            socket.close();
            server.close();
            System.out.println("Closing connection");
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listening() {
        String line = "";
        while (!line.equals("Over")) {
            try {
                line = in.readLine();
                System.out.println("Server received: " + line);
                Thread.sleep(2000);
                out.write("ok");
                out.newLine();
                out.flush();
            } catch (IOException i) {
                System.err.println(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closeSocket();
    }
}
