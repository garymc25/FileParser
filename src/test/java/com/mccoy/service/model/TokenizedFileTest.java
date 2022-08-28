package com.mccoy.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests encompassing the TokenizedFile class
 */
public class TokenizedFileTest {
    @Test
    public void addRow_nullRow_noRowsAdded() {
        TokenizedFile newFile = new TokenizedFile();
        newFile.addRow(null);
        Assertions.assertEquals(0, newFile.rowCount());
    }

    @Test
    public void addRow_validRow_oneRowAdded() {
        List<String> rowList = List.of("A", "B", "C");
        TokenizedFile newFile = new TokenizedFile();
        newFile.addRow(rowList);
        Assertions.assertEquals(1, newFile.rowCount());
    }

    @Test
    public void getRow_invalidRowHigh_nullRowReturned() {
        TokenizedFile newFile = new TokenizedFile();
        List<String> foundRow = newFile.getRow(5);

        Assertions.assertNull(foundRow);
    }

    @Test
    public void getRow_invalidRowLow_nullRowReturned() {
        TokenizedFile newFile = new TokenizedFile();
        List<String> foundRow = newFile.getRow(-5);

        Assertions.assertNull(foundRow);
    }

    @Test
    public void getRow_retrieveValidRow_rowFound() {
        List<String> rowList = List.of("A", "B", "C");
        TokenizedFile newFile = new TokenizedFile();
        newFile.addRow(rowList);

        List<String> foundRow = newFile.getRow(0);
        Assertions.assertNotNull(foundRow);
    }

    @Test
    public void rowCount_verifyBeforeAndAfterAdd_rowCountValid() {
        TokenizedFile newFile = new TokenizedFile();
        Assertions.assertEquals(0, newFile.rowCount());

        List<String> rowList = List.of("A", "B", "C");
        newFile.addRow(rowList);
        Assertions.assertEquals(1, newFile.rowCount());
    }

    @Test
    public void columnCountForRow_invalidRowHigh_nullRowReturned() {
        TokenizedFile newFile = new TokenizedFile();
        List<String> rowList = List.of("A", "B", "C");
        newFile.addRow(rowList);

        Integer columnCount = newFile.columnCountForRow(5);
        Assertions.assertNull(columnCount);
    }

    @Test
    public void columnCountForRow_invalidRowLow_nullRowReturned() {
        TokenizedFile newFile = new TokenizedFile();
        List<String> rowList = List.of("A", "B", "C");
        newFile.addRow(rowList);

        Integer columnCount = newFile.columnCountForRow(-5);
        Assertions.assertNull(columnCount);
    }

    @Test
    public void columnCountForRow_retrieveValidRow_rowFound() {
        List<String> rowList = List.of("A", "B", "C");
        TokenizedFile newFile = new TokenizedFile();
        newFile.addRow(rowList);

        Integer columnCount = newFile.columnCountForRow(0);
        Assertions.assertNotNull(columnCount);
        Assertions.assertEquals(3, columnCount);
    }

}