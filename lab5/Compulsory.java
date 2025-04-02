package lab5;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

class ImageDisplayException extends Exception {

  public ImageDisplayException(String message) {
    super(message);
  }
}

record ImageItem(
    String name, LocalDate date,
    List<String> tags, File location) {
}

class ImageRepository {

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

public class Compulsory {

  public static void main(String[] args) {

    ImageItem img1 = new ImageItem("Dog", LocalDate.of(2024, 3, 26),
        List.of("happy", "animal"),
        new File("lab5/res/dog1 copy 2.jpg"));
    ImageItem img2 = new ImageItem("Cool Dog", LocalDate.of(2023, 12, 15),
        List.of("not so happy", "lmao"),
        new File("../dog1 copy.jpg"));

    ImageRepository repo = new ImageRepository();
    repo.addImage(img1);
    repo.addImage(img2);
    repo.print();

    try {
      repo.displayImage("Dog");
    } catch (IOException e) {
      System.out.printf("error: image not found.\n");
    } catch (ImageDisplayException e) {
      System.out.printf("%s\n", e.getMessage());
    } finally {
      System.out.println("the source finished running.");
    }
  }
}
