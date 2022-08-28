package com.mccoy.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * File that's been tokenized
 */
public class TokenizedFile {
    private final List<List<String>> tokenizedResultList = new ArrayList<>();

    /**
     * Add a non-null new row to the file
     *
     * @param newRow Array of token values to add to the file
     */
    public void addRow(List<String> newRow) {
        if (newRow != null) {
            tokenizedResultList.add(newRow);
        }
    }

    /**
     * Retrieve a particular row
     *
     * @param row Row number to obtain
     * @return An array of values for the row; null for invalid rows
     */
    public List<String> getRow(int row) {
        if (row < 0 ||
            row > tokenizedResultList.size()) {
            return null;
        }

        return List.copyOf(tokenizedResultList.get(row));
    }

    /**
     * Retrieve a count of rows
     *
     * @return The total count of rows
     */
    public int rowCount() {
        return tokenizedResultList.size();
    }

    /**
     * Retrieve a column count for a particular row
     *
     * @param row Desired row number
     * @return The number of columns for the specified row
     */
    public Integer columnCountForRow(int row) {
        if (row < 0 ||
            row > tokenizedResultList.size()) {
            return null;
        }

        return tokenizedResultList.get(row).size();
    }

    /**
     * Returns the string representation
     * NOTE: From instructions, assuming it's okay to include oxford comma and disregard the word
     *       and between the last 2 elements  :)
     *
     * @return String representation of this
     */
    @Override
    public String toString() {
        StringBuilder outputBuilder = new StringBuilder();

        for (List<String> currentRow : tokenizedResultList) {
            outputBuilder.append("[");
            for (String columnValue : currentRow) {
                outputBuilder.append("\"")
                        .append(columnValue)
                        .append("\"")
                        .append(",");
            }
            outputBuilder.deleteCharAt(outputBuilder.length() - 1);
            outputBuilder.append("]\r\n");
        }
        outputBuilder.deleteCharAt(outputBuilder.length() - 1);

        return outputBuilder.toString();
    }
}