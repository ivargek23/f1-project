package hr.algebra.backendapi.utils;

import hr.algebra.backendapi.exception.DatabaseOperationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {
    private FileUtils() {}
   

    public static void ensureDirectoryExists(String path) {
        Path directory = Path.of(path);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new DatabaseOperationException("Failed to create directory " + directory, e);
            }
        }
    }

    public static String createFilepath(String directory, String prefix, String extension, DateTimeFormatter timestampFormatter) {
        String filename = prefix + LocalDateTime.now().format(timestampFormatter) + extension;
        return directory + File.separator + filename;
    }
}
