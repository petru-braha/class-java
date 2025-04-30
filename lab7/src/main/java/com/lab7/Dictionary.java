package com.lab7;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Dictionary {

  private static final Set<String> words = new HashSet<>();

  static {
    try {
      File file = new File("src/main/resources/words.txt");
      Scanner scan = new Scanner(file);
      while (scan.hasNextLine())
        words.add(scan.nextLine());
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static boolean isValid(String word) {
    return words.contains(word.toLowerCase());
  }

  private Dictionary() {
  }
}
