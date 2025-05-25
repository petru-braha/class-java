package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    try {
      new Server(1234).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ServerSocket serverSocket;
  private boolean isRunning;

  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    isRunning = true;
  }

  public void start() {
    System.out.println("Server started on port " + serverSocket.getLocalPort());
    while (isRunning) {
      try {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        new ClientThread(clientSocket, this).start();
      } catch (IOException e) {
        if (!isRunning)
          System.out.println("Server stopped.");
        else
          e.printStackTrace();
      }
    }
  }

  public synchronized void stopServer() {
    isRunning = false;
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
