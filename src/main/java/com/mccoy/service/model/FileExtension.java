package com.mccoy.service.model;

/**
 * An enumerated file extension
 */
public enum FileExtension {
    Pipe,
    Space,
    Tab,
    FixedWidth,
    Csv;

    /**
     * Translate a string representation of an extension into an enumerated equivalent
     *
     * @param extension A valid file extension
     * @return The translated enumeration extension; otherwise, null
     */
    public static FileExtension fromString(String extension) {
        switch (extension.toLowerCase()) {
            case "tab":
                return FileExtension.Tab;
            case "fixed":
                return FileExtension.FixedWidth;
            case "csv":
                return FileExtension.Csv;
            case "pipe":
                return FileExtension.Pipe;
            case "space":
                return FileExtension.Space;
        }
        return null;
    }
}