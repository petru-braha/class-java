package junk;

import java.io.*;

class MyFilter implements FilenameFilter {

  String extension;

  public MyFilter(String extension) {
    this.extension = extension;
  }

  public boolean accept(File dir, String name) {
    return name.endsWith("." + extension);
  }
}

// does not work
public class FileFilter {

  public static void main(String[] args) {

    File folder = new File(".");
    System.out.println(folder.getAbsolutePath());

    // String[] list = folder.list(new MyFilter("java"));
    String[] list = folder.list(
        new FilenameFilter() {

          public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
          }
        });

    for (int i = 0; i < list.length; i++) {
      System.out.println(list[i]);
    }
  }
}
