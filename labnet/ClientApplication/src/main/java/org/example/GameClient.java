package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {

  private static final String SERVER_ADDRESS = "127.0.0.1";
  private static final int PORT = 1234;

  private static final BufferedReader consoleInp = new BufferedReader(new InputStreamReader(System.in));

  public static void printHelp() {
    System.out.printf("""
        %s - %s%n
        %s - %s%n
        %s - %s%n
        %s - %s%n""",
        "help", "prints this message",
        "phex x y", "make a move",
        "exit", "resign/close this app",
        "stop", "close the server");
  }

  public static void main(String[] args) {

    try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

      System.out.printf("connected to the server.%n");
      System.out.printf("type \"help\" to see the commands.%n");

      // loop
      for (String input = ""; !input.equals(
          "exit"); input = consoleInp.readLine()) {

        if (input.equals("help")) {
          printHelp();
          continue;
        }

        out.println(input);
        for (String output = ""; output != null; output = in.readLine())
          System.out.printf("%s", output);
      }

    } catch (IOException e) {
      System.out.printf(
          "error: server connection failed : %s.%n",
          e.getMessage());
    }

    System.out.printf("connection closed.%n");
  }
}
