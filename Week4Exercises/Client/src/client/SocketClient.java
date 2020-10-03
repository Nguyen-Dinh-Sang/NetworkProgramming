/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DinhSang
 */
public class SocketClient {

    private Socket socket = null;
    private BufferedWriter out = null;
    private BufferedReader in = null;
    private Result result;

    public SocketClient(String address, int port, Result result) {
        openSocket(address, port);
        this.result = result;
    }

    private void openSocket(String address, int port) {
        try {
            socket = new Socket(address, port);

            if (socket.isConnected()) {
                System.out.println("Connected");
            }

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeSocket() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Closing connection");
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String message) {
        try {
            System.err.println("Client sent: " + message);
            out.write(message);
            out.newLine();
            out.flush();
            if (message.equals("Over")) {
                closeSocket();
            }
            
            String line = in.readLine();
            if (line != null) {
                System.err.println("!null");
                result.result(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public interface Result {

        public void result(String mes);
    }

}
