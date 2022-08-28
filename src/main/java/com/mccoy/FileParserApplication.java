package com.mccoy;

import com.mccoy.service.FileService;
import com.mccoy.service.TokenizerService;
import com.mccoy.service.model.FileExtension;
import com.mccoy.service.model.TokenizedFile;
import com.mccoy.service.tokenization.CsvTokenizer;
import com.mccoy.service.tokenization.FixedWidthTokenizer;
import com.mccoy.service.tokenization.PipeTokenizer;
import com.mccoy.service.tokenization.SpaceTokenizer;
import com.mccoy.service.tokenization.TabTokenizer;
import com.mccoy.service.tokenization.TokenizerStrategy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Parses a file and return the tokenized contents
 */
public class FileParserApplication {
    /**
     * Program entry point
     *
     * @param args First arg represents a file name
     *             For fixed width files, second arg represents a constant field width for all fields
     */
    public static void main(String[] args) {
        // Validate input arguments and that file exists
        if (args.length < 1 || !Files.exists(Path.of(args[0]))) {
            System.out.println("Please specify an argument for source file to be parsed with a known extension (tab, fixed, pipe, space, csv).");
            System.out.println("For fixed width files, please also enter a second argument specifying the file column width (assumes all columns are one width).");
            System.exit(1);
        }
        String fileName = args[0];

        // Translate the extension and ensure it's supported
        FileExtension sourceExtension = FileExtension.fromString(fileName.substring(fileName.lastIndexOf('.') + 1));
        if (sourceExtension == null) {
            throw new UnsupportedOperationException("Unsupported file extension specified");
        }

        // Retrieve the second argument for fixed width files
        int columnWidth = 0;
        if (sourceExtension == FileExtension.FixedWidth) {
            try {
                //NOTE: Realistically, this should be a comma delimited set of integers since it rarely happens
                //       that columns are all the same width throughout a file
                columnWidth = Integer.parseInt(args[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                System.out.println("For fixed width files, please enter a valid second numeric argument indicating the file column width (assuming all columns are one width).");
                System.exit(1);
            }
        }

        // Find strategy from extension
        TokenizerStrategy lineTokenizer = getTokenizerStrategy(sourceExtension, columnWidth);
        Objects.requireNonNull(lineTokenizer, "Cannot determine tokenizer from the file format");

        // Read file
        FileService fileService = new FileService();
        List<String> fileContentsList = fileService.readFileLines(fileName);

        // Tokenize away
        TokenizerService tokenizerService = new TokenizerService(lineTokenizer);
        TokenizedFile tokenizedFileResult = tokenizerService.parseList(fileContentsList);
        System.out.println(tokenizedFileResult);
    }

    /**
     * Return a tokenizer strategy for the given file extension to use in parsing content
     * NOTE: originally implemented using factory construction, but changed to strategy primarily because
     * of the additional arguments required for scenarios like fixed-width
     *
     * @param sourceExtension File extension
     * @param columnWidth     Column width (only for fixed-width files)
     * @return A new tokenizer strategy instance; null if unknown
     */
    private static TokenizerStrategy getTokenizerStrategy(FileExtension sourceExtension, int columnWidth) {
        switch (sourceExtension) {
            case Tab:
                return new TabTokenizer();
            case FixedWidth:
                return new FixedWidthTokenizer(columnWidth);
            case Pipe:
                return new PipeTokenizer();
            case Csv:
                return new CsvTokenizer();
            case Space:
                return new SpaceTokenizer();
        }
        return null;
    }
}