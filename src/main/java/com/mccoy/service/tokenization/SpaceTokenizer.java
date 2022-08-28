package com.mccoy.service.tokenization;

import java.util.List;

/**
 * Tokenize a string with a space delimiter
 */
public class SpaceTokenizer implements TokenizerStrategy {
    @Override
    public List<String> parse(String sourceContent) {
        return List.of(sourceContent.split(" "));
    }
}