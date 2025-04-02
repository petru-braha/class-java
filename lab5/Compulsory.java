package lab5;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import java.util.List;
import lab5.err.*;

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
