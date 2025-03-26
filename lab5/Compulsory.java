package lab5;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class ImageDisplayException extends Exception {

  public ImageDisplayException(String message) {
    super(message);
  }
}

class ImageItem {

  private String name;
  private LocalDate date;
  private List<String> tags;
  private Path location;

  public ImageItem(
      String name, LocalDate date,
      List<String> tags, Path location) {
    this.name = name;
    this.date = date;
    this.tags = new ArrayList<>(tags);
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDate() {
    return date;
  }

  public List<String> getTags() {
    return Collections.unmodifiableList(tags);
  }

  public Path getLocation() {
    return location;
  }

  @Override
  public String toString() {
    return name + " - " + date + " - " + tags;
  }
}

class ImageRepository {

  private final List<ImageItem> images;

  public ImageRepository() {
    images = new ArrayList<>();
  }

  public boolean addImage(ImageItem image) {
    return images.add(image);
  }

  public void displayImage(String imageName)
      throws IOException, ImageDisplayException {

    for (int i = 0; i < images.size(); i++) {

      if (false == images.get(i).getName().equals(imageName))
        continue;

      File file = images.get(i).getLocation().toFile();
      if (false == Desktop.isDesktopSupported())
        throw new ImageDisplayException("desktop not supported.");
      if (false == file.exists())
        throw new IOException();

      Desktop.getDesktop().open(file);
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
        Path.of("res/dog1.jpg"));
    ImageItem img2 = new ImageItem("Cool Dog", LocalDate.of(2023, 12, 15),
        List.of("not so happy", "lmao"),
        Path.of("../dog1 copy.jpg"));

    ImageRepository repo = new ImageRepository();
    repo.addImage(img1);
    repo.addImage(img2);
    repo.print();

    try {
      repo.displayImage("Cool Dog");
    } catch (IOException e) {
      System.out.printf("error: image not found.\n");
    } catch (ImageDisplayException e) {
      System.out.printf("%s\n", e.getMessage());
    } finally {
      System.out.println("the source finished running.");
    }
  }
}
