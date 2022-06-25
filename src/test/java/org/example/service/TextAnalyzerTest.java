package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.TextStats;
import org.example.infrastructure.JsonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

class TextAnalyzerTest {

    private TextAnalyzer analyzer;

    @BeforeEach
    void setUp() {

        analyzer = new TextAnalyzer();
    }

    @Test
    void analyze() {

        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 9. Nulla tempus porta ante in 1 dignissim. Donec porta a 90 ; nte vel maximus vehicula.";

        TextStats stats = analyzer.analyze(text);

        Assertions.assertEquals(143, stats.getTotalChars());
        Assertions.assertEquals(4, stats.getTotalNumericChars());
        Assertions.assertEquals(3, stats.getTotalUpperCaseAlphaChars());
        Assertions.assertEquals(24, stats.getTotalWhiteSpace());

    }

    @Test
    void analyzeFile() throws IOException {

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/test.json"));

        JsonHelper jsonHelper = new JsonHelper(true, true);

        TestInput testInput = jsonHelper.fromReader(reader, TestInput.class);

        TextStats stats = analyzer.analyze(testInput.getText());

        System.out.println(jsonHelper.toJson(stats));

        Assertions.assertEquals(2668, stats.getTotalChars());
    }

    @Getter
    @Setter
    class TestInput {

        private String text;
    }

}