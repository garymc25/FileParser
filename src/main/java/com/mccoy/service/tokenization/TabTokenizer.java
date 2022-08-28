package com.mccoy.service.tokenization;

import java.util.List;

/**
 * Tokenize a string with a tab delimiter
 */
public class TabTokenizer implements TokenizerStrategy {
    @Override
    public List<String> parse(String sourceContent) {
        return List.of(sourceContent.split("\t"));
    }
}