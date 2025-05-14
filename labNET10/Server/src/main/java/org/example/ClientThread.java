package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

  private Socket socket;
  private Server server;

  public ClientThread(Socket socket, Server server) {
    this.socket = socket;
    this.server = server;
  }

  @Override
  public void run() {

    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

      String request;
      while ((request = in.readLine()) != null) {
        System.out.println("Received: " + request);
        if ("stop".equalsIgnoreCase(request)) {
          server.stopServer();
          out.println("Server stopped");
          break;
        } else {
          out.println("Server received the request: " + request);
        }
      }

    } catch (IOException e) {
      System.out.println("Client error: " + e.getMessage());
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
