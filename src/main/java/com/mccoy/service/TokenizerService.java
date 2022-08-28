package com.mccoy.service;

import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.TokenizerStrategy;

import java.util.List;

/**
 * Service methods for tokenization of strings
 */
public class TokenizerService {

    private final TokenizerStrategy lineTokenizer;

    public TokenizerService(TokenizerStrategy lineTokenizer) {
        this.lineTokenizer = lineTokenizer;
    }

    /**
     * Parse an array of incoming strings into a TokenizedFile containing rows of tokenized elements
     *
     * @param sourceListToTokenize A list of strings to tokenize
     * @return Object representation of the resulting file rows and columns
     */
    public TokenizedFile parseList(List<String> sourceListToTokenize) {
        TokenizedFile resultFile = new TokenizedFile();

        for (String currentRow : sourceListToTokenize) {
            resultFile.addRow(lineTokenizer.parse(currentRow));
        }

        return resultFile;
    }
}