package com.mccoy.service.tokenization;

import java.util.List;

public interface TokenizerStrategy {
    /**
     * Parse a given line and return the tokenized version of that line
     *
     * @param sourceContent Source string of content
     * @return An array of tokenized values
     */
    List<String> parse(String sourceContent);
}