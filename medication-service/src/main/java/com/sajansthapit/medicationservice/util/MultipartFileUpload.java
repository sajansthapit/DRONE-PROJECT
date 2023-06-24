package com.sajansthapit.medicationservice.util;

import com.sajansthapit.medicationservice.constants.MedicationConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MultipartFileUpload {

    public static String upload(MultipartFile image, String rootFolder) {
        String rootLoc = MedicationConstants.HOME_FOLDER + File.separator + rootFolder;
        String fileName = image.getOriginalFilename();
        byte[] bytes;

        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Invalid multipart file");
        }

        File directory = new File(rootLoc);

        if (!directory.exists()) {
            try {
                Files.createDirectories(Paths.get(MedicationConstants.HOME_FOLDER + File.separator + rootFolder));
            } catch (IOException e) {
                throw new RuntimeException("Failed to create folder");
            }
        }

        try {
            Path path = Paths.get(rootLoc + File.separator + fileName);
            Files.write(path, bytes);
            return rootFolder + File.separator + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Filed to upload file");
        }

    }
}
