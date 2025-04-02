package lab5;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import lab5.err.*;

public class ImageRepository {

  private static final Desktop desktop = Desktop.getDesktop();
  private final List<ImageItem> images;

  public ImageRepository() {
    images = new ArrayList<>();
  }

  public boolean addImage(ImageItem image) {
    return images.add(image);
  }

  public void displayImage(String imageName)
      throws IOException, ImageDisplayException {

    if (false == Desktop.isDesktopSupported())
      throw new ImageDisplayException("desktop not supported.");

    for (int i = 0; i < images.size(); i++) {

      if (false == images.get(i).name().equals(imageName))
        continue;

      File file = images.get(i).location();
      if (false == file.exists())
        throw new IOException();

      desktop.open(file);
      return;
    }

    System.out.println("Image not found.");
  }

  public void print() {
    for (int i = 0; i < images.size(); i++)
      System.out.println(images.get(i));
    System.out.printf("\n");
  }
}
