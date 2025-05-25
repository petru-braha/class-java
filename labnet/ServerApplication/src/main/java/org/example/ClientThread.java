package org.example;

import java.io.*;
import java.net.Socket;

import java.util.Set;
import java.util.HashSet;

public class ClientThread extends Thread {

  private Socket socket;
  private GameServer server;
  private static Set<Board> gamespaces;

  static {
    gamespaces = new HashSet<>();
  }

  public ClientThread(Socket socket, GameServer server) {
    this.socket = socket;
    this.server = server;
  }

  @Override
  public void run() {

    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

      System.out.printf("connected client: %s%n",
          socket.getInetAddress());

      // loop
      for (String input = ""; !input.equals(
          CommandList.STOP_STRING); input = in.readLine()) {

        System.out.println("received: " + input);
        // todo operation play with gamespaces
        String result = "hardcoded";
        out.println(result);
      }

      server.stop();
      out.println("Server stopped");

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
