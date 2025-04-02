package lab5;

import java.time.LocalDate;
import java.io.File;
import java.util.List;

public record ImageItem(
    String name, LocalDate date,
    List<String> tags, File location) {
}
