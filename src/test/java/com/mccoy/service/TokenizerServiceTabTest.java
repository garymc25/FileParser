package com.mccoy.service;

import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.TabTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests for the Tokenizer Service for tabbed inputs
 */
public class TokenizerServiceTabTest {
    private final TokenizerService tokenizerService = new TokenizerService(new TabTokenizer());

    @Test
    public void parseList_tabDelimitedLine_1Row3ColumnsWithValues() {
        String inputString = "ABC\t123\tXYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));
        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("123", tokenizedFileResult.getRow(0).get(1));
    }

    @Test
    public void parseList_tabDelimitedLineEmptySecondValue_1Row3ColumnsWithValues() {
        String inputString = "ABC\t\tXYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));
        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("", tokenizedFileResult.getRow(0).get(1));
    }
}