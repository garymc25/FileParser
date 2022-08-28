package com.mccoy.service;

import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.FixedWidthTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests for the Tokenizer Service for fixed width inputs
 */
public class TokenizerServiceFixedWidthTest {
    @Test
    public void parseList_tabDelimitedLine_1Row3ColumnsWithValues() {
        TokenizerService tokenizerService = new TokenizerService(new FixedWidthTokenizer(6));
        String inputString = "ABC   123   XYZ   ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));

        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("123", tokenizedFileResult.getRow(0).get(1));
    }

    @Test
    public void parseList_tabDelimitedLineFewerEndSpaces_1Row4ColumnsWithValues() {
        TokenizerService tokenizerService = new TokenizerService(new FixedWidthTokenizer(7));
        String inputString = "ABC    123    XYZ    ZZZ ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));

        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(4, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("123", tokenizedFileResult.getRow(0).get(1));
    }

    @Test
    public void parseList_tabDelimitedLineEmptySecondValue_1Row3ColumnsWithValues() {
        TokenizerService tokenizerService = new TokenizerService(new FixedWidthTokenizer(6));
        String inputString = "ABC         XYZ   ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));

        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("", tokenizedFileResult.getRow(0).get(1));
    }
}