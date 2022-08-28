package com.mccoy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Tests for the FileService class
 */
public class FileServiceIntegrationTest {
    private final FileService fileService = new FileService();

    private String getAbsolutePathForFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        return file.getAbsolutePath();
    }

    @Test
    public void readFileLines_validFile_fileContentReturns() {
        List<String> fileList = fileService.readFileLines(getAbsolutePathForFile("test.csv"));
        Assertions.assertNotEquals(0, fileList.size());
    }

    @Test
    public void readFileLines_missingFile_emptyArrayReturns() {
        List<String> fileList = fileService.readFileLines("test.xyz");
        Assertions.assertEquals(0, fileList.size());
    }
}