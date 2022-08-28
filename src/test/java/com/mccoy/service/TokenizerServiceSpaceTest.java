package com.mccoy.service;

import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.SpaceTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests for the Tokenizer Service for spaced inputs
 */
public class TokenizerServiceSpaceTest {
    private final TokenizerService tokenizerService = new TokenizerService(new SpaceTokenizer());

    @Test
    public void parseList_spaceDelimitedLine_1Row3ColumnsWithValues() {
        String inputString = "ABC 123 XYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));
        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("123", tokenizedFileResult.getRow(0).get(1));
    }

    @Test
    public void parseList_spaceDelimitedLineEmptySecondValue_1Row3ColumnsWithValues() {
        String inputString = "ABC  XYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));
        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("", tokenizedFileResult.getRow(0).get(1));
    }
}