package com.mccoy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * System file-related services
 */
public class FileService {
    /**
     * Reads in the specified fileName and returns the contents in an array of one line per entry consecutively
     *
     * @param sourceFileNameWithPath Fully qualified filename and path
     * @return An array of all file contents; empty list if file specified is invalid/missing
     */
    public List<String> readFileLines(String sourceFileNameWithPath) {
        try {
            return Files.readAllLines(Path.of(sourceFileNameWithPath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}