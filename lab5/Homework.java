package lab5;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.swing.*

public class Homework {
  
  public static void main(String[] args) {
    ImageRepository repository = new ImageRepository();
        ImageItem image1 = new ImageItem("Galati", LocalDate.of(2025, 3, 25), Arrays.asList("testoase", "gradina botanica", "chiatra"), "C:/Users/lefco/Desktop/poze aparat/P1030957.JPG", path);
        repository.addImage(image1);

        cmd_add addCommand = new cmd_add(repository);
        cmd_remove removeCommand = new cmd_remove(repository);
        cmd_update updateCommand = new cmd_update(repository);
        cmd_save saveCommand = new cmd_save(repository);
        cmd_load loadCommand = new cmd_load(repository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();

            String[] parts = line.split(" ");
            if (parts.length == 0) {
                continue;
            }

            String cmdName = parts[0];
            String[] cmdArgs = new String[parts.length - 1];
            for (int i = 0; i < cmdArgs.length; i++) {
                cmdArgs[i] = parts[i + 1];
            }

                if (cmdName.equals(addCommand.getName())) {
                    addCommand.exec(cmdArgs);
                } else if (cmdName.equals(removeCommand.getName())) {
                    removeCommand.exec(cmdArgs);
                } else if (cmdName.equals(updateCommand.getName())) {
                    updateCommand.exec(cmdArgs);
                } else if (cmdName.equals(saveCommand.getName())) {
                    saveCommand.exec(cmdArgs);
                } else if (cmdName.equals(loadCommand.getName())) {
                    loadCommand.exec(cmdArgs);
                } else {
                    System.out.printf("unknown command: %s." + cmdName);
                }
                    System.out.println("Images in the repository:");
                    for (int i = 0; i < repository.getImages().size(); i++) {
                        ImageItem image = repository.getImages().get(i);
                        System.out.printf("%d. %s, %s, %s, %s\n", i, image.name(), image.date(), String.join(";", image.tags()), image.location());
        }
    }

  }
}
