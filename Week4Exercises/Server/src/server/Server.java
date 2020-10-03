/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author DinhSang
 */

public class Server {

    public static void main(String args[]) {
        SocketServer server = new SocketServer(5000);
    }
}
