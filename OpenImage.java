import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenImage {
    public static void main(String[] args) {
        // Replace with the actual path of your image file
        File imageFile = new File("lab5/res/dog1 copy 2.jpg");

        // Check if Desktop is supported
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(imageFile);
            } catch (IOException e) {
                System.out.println("Error opening file: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop is not supported on this system.");
        }
    }
}
