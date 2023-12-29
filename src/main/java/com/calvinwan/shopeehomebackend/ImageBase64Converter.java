package com.calvinwan.shopeehomebackend;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageBase64Converter {
    public static String imageToBase64(String imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void base64ToImage(String base64String, String outputPath) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        Files.write(Paths.get(outputPath), imageBytes);
    }

    public static byte[] imageToBytes(String imagePath) throws IOException {
        return Files.readAllBytes(Paths.get(imagePath));
    }

}