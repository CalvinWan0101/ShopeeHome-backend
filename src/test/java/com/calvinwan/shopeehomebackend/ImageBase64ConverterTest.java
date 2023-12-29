package com.calvinwan.shopeehomebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageBase64ConverterTest {
    @Test
    public void convert_image_to_base64() throws IOException {
        String expect = ImageBase64Converter.imageToBase64("src/test/resources/img/test/test.JPG");
        String result = ImageBase64Converter.imageToBase64("src/test/resources/img/test/test_copy.JPG");
        assertEquals(expect, result);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new java.io.File("src/test/resources/img/test/test.json"), expect);
    }

    @Test
    public void convert_base64_to_image() throws IOException {
        String expect = ImageBase64Converter.imageToBase64("src/test/resources/img/test/test.JPG");
        ImageBase64Converter.base64ToImage(expect, "src/test/resources/img/test/test_program_copy.JPG");
        String result = ImageBase64Converter.imageToBase64("src/test/resources/img/test/test_program_copy.JPG");
        assertEquals(expect, result);
    }

}