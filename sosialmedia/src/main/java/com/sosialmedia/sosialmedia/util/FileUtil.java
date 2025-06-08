package com.sosialmedia.sosialmedia.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@UtilityClass
public class FileUtil {

    public static final String FILE_DIRECTORY = "files";

    public static void saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(FILE_DIRECTORY);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);  // Directory varsa yaratmır, yoxdursa birdən yarat
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException("Faylı yadda saxlamaq mümkün olmadı: " + fileName, ex);
        }
    }

    public static byte[] readFile(String fileName) {
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Faylı oxumaq mümkün olmadı: " + fileName, ex);
        }
    }

    public static void deleteFile(String fileName) {
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Faylı silmək mümkün olmadı: " + fileName, ex);
        }
    }
}
