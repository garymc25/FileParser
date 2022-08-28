package com.mccoy.service.tokenization;

import java.util.List;

/**
 * Tokenize a string with a comma delimiter
 */
public class CsvTokenizer implements TokenizerStrategy {
    @Override
    public List<String> parse(String sourceContent) {
        return List.of(sourceContent.split(","));
    }
}