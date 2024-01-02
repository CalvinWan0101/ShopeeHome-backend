package com.calvinwan.shopeehomebackend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageBase64Converter {
    public static String imageToBase64(String imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath).toAbsolutePath());
        String base64String = Base64.getEncoder().encodeToString(imageBytes);
        String imageType = getImageType(imagePath);
        return "data:image/" + imageType + ";base64," + base64String;
    }

    private static String getImageType(String imagePath) {
        String extension = "";

        int i = imagePath.lastIndexOf('.');
        if (i > 0) {
            extension = imagePath.substring(i + 1);
        }
        return extension.toLowerCase();
    }
}
