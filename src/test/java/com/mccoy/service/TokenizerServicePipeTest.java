package com.mccoy.service;

import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.PipeTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests for the Tokenizer Service for piped inputs
 */
public class TokenizerServicePipeTest {
    private final TokenizerService tokenizerService = new TokenizerService(new PipeTokenizer());

    @Test
    public void parseList_pipeDelimitedLine_1Row3ColumnsWithValues() {
        String inputString = "ABC|123|XYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));

        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("123", tokenizedFileResult.getRow(0).get(1));
    }

    @Test
    public void parseList_pipeDelimitedLineEmptySecondValue_1Row3ColumnsWithValues() {
        String inputString = "ABC||XYZ";

        TokenizedFile tokenizedFileResult = tokenizerService.parseList(List.of(inputString));

        Assertions.assertNotNull(tokenizedFileResult);
        Assertions.assertEquals(1, tokenizedFileResult.rowCount()); // Row count
        Assertions.assertEquals(3, tokenizedFileResult.columnCountForRow(0)); // Column count
        Assertions.assertEquals("", tokenizedFileResult.getRow(0).get(1));
    }
}