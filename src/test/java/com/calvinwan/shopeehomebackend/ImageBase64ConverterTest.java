package com.calvinwan.shopeehomebackend;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageBase64ConverterTest {
    @Test
    public void convert_the_image_and_the_copy_image() throws IOException {
        String result = ImageBase64Converter.imageToBase64("src/main/resources/img/test/test.JPG");
        assertEquals(result, ImageBase64Converter.imageToBase64("src/main/resources/img/test/test_copy.JPG"));
//        ImageBase64Converter.base64ToImage(result, "src/main/resources/img/3_tissue/tissue_1_copy.webp");
    }

}