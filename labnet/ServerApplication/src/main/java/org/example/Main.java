package org.example;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Main {

  public static final int PORT_DEFAULT = 1234;
  public static final int PORT_MAXIMUM = 65535;

  public static boolean available(int port) {

    if (port < PORT_DEFAULT || port > PORT_MAXIMUM) {
      throw new IllegalArgumentException("Invalid start port: " + port);
    }

    ServerSocket ss = null;
    DatagramSocket ds = null;
    try {
      ss = new ServerSocket(port);
      ss.setReuseAddress(true);
      ds = new DatagramSocket(port);
      ds.setReuseAddress(true);
      return true;
    } catch (IOException e) {
    } finally {
      if (ds != null) {
        ds.close();
      }

      if (ss != null)
        try {
          ss.close();
        } catch (IOException e) {
          System.out.printf("%s%n", e.getMessage());
        }
    }

    return false;
  }

  public static void main(String[] args) {

    int port = PORT_DEFAULT;
    for (; !available(port) && port < PORT_MAXIMUM; port++)
      ;
    if (port >= PORT_MAXIMUM) {
      System.out.printf("error: not port available.%n");
    }

    try {
      GameServer game = new GameServer(PORT_DEFAULT);
      game.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
