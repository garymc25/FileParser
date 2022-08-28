package com.mccoy.service.tokenization;

import java.util.ArrayList;
import java.util.List;

/**
 * Tokenize a string with a fixed width specified
 */
public class FixedWidthTokenizer implements TokenizerStrategy {
    private final int width;

    public FixedWidthTokenizer(int width) {
        this.width = width;
    }

    @Override
    public List<String> parse(String sourceContent) {
        // Do not miss words at end where spaces might be missing and the word length isn't even divisible width
        int wordCount = sourceContent.length() / width + (sourceContent.length() % width > 0 ? 1 : 0);

        List<String> parsedList = new ArrayList<>();
        for (int wordIndex = 0; wordIndex < wordCount; wordIndex++) {
            int startIndex = wordIndex * width;
            int endIndex = Math.min((wordIndex * width) + width, sourceContent.length());

            parsedList.add(sourceContent.substring(startIndex, endIndex).trim());
        }
        return parsedList;
    }
}