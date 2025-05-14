package org.example;

import java.io.*;
import java.net.Socket;

public class Client {

  public static void main(String[] args) {

    final String SERVER_ADDRESS = "localhost";
    final int PORT = 1234;

    try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

      System.out.println("Connected to server. Enter commands (type 'exit' to quit):");

      String input;
      while ((input = console.readLine()) != null) {
        if ("exit".equals(input))
          break;
        out.println(input);
        System.out.println("Server: " + in.readLine());
      }
    } catch (IOException e) {
      System.out.println("Server connection error: " + e.getMessage());
    }
  }
}
