package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

  public static final int USER_COUNT = 2;
  public static final int GAME_COUNT = 5;

  private ServerSocket serverSocket;
  private boolean isRunning;

  public GameServer(final int port) throws IOException {
    serverSocket = new ServerSocket(port, USER_COUNT * GAME_COUNT);
    isRunning = true;
  }

  public void start() {

    System.out.printf("GameServer started on port %d %n%n",
        serverSocket.getLocalPort());

    while (isRunning) {

      try {
        new ClientThread(serverSocket.accept(), this).start();

      } catch (IOException e) {
        if (!isRunning)
          System.out.printf("error: GameServer stopped.%n");
        else
          e.printStackTrace();
      }
    }
  }

  public synchronized void stop() {

    isRunning = false;
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
