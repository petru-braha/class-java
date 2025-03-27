package junk;

import java.io.IOException;

public class TestExceptions {

  private static void f(int value)
      throws IOException {
    if (1 == value)
      throw new IOException();
    else
      throw new NullPointerException();
  }

  public static void main(String[] args) {

    try {
      f(5);
    } catch (IOException e) {

    }
  }
}
