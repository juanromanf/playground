package org.example.service;

import org.example.domain.CharStat;
import org.example.domain.TextStats;

import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

    public TextStats analyze(String text) {

        TextStats stats = initializeStats();
        stats.setTotalChars(text.length());
        stats.setTotalWords(countWords(text));

        Map<Character, Integer> top = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);

            if (isAlphaUpperCase(c) || isAlphaLowerCase(c)) {

                accumulatePerCharCount(top, c);
                stats.setTotalAlphaChars(stats.getTotalAlphaChars() + 1);
            }

            if (isNumber(c)) {
                stats.setTotalNumericChars(stats.getTotalNumericChars() + 1);

            } else if (isAlphaUpperCase(c)) {
                stats.setTotalUpperCaseAlphaChars(stats.getTotalUpperCaseAlphaChars() + 1);

            } else if (isAlphaLowerCase(c)) {
                stats.setTotalLowerCaseAlphaChars(stats.getTotalLowerCaseAlphaChars() + 1);

            } else if (isWhiteSpace(c)) {
                stats.setTotalWhiteSpace(stats.getTotalWhiteSpace() + 1);

            } else if (isPunctuation(c)) {
                stats.setTotalPunctuationSpace(stats.getTotalPunctuationSpace() + 1);
            }
        }

        List<CharStat> charStatList = getTopCharStats(stats.getTotalAlphaChars(), top);
        stats.setTop10(charStatList);

        return stats;
    }

    private TextStats initializeStats() {

        return TextStats.builder()
                .totalChars(0) //
                .totalWords(0) //
                .totalNumericChars(0) //
                .totalAlphaChars(0) //
                .totalUpperCaseAlphaChars(0)//
                .totalLowerCaseAlphaChars(0) //
                .totalWhiteSpace(0) //
                .totalPunctuationSpace(0)// . ; - ,
                .top10(List.of()) //
                .build();
    }

    private void accumulatePerCharCount(Map<Character, Integer> top, char c) {

        if (top.containsKey(c)) {
            top.compute(c, (character, count) -> count + 1);

        } else {
            top.put(c, 1);
        }
    }

    private List<CharStat> getTopCharStats(int totalChars, Map<Character, Integer> top) {

        List<CharStat> charStatList = top.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(t -> CharStat.builder()
                        .character(t.getKey().toString())
                        .count(t.getValue())
                        .percentage(((t.getValue().doubleValue() / totalChars) * 100))
                        .build())
                .collect(Collectors.toList());

        return charStatList;
    }

    private int countWords(String sentence) {

        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(sentence);

        return tokens.countTokens();
    }

    private boolean isPunctuation(char c) {
        return c == 45 || c == 46 || c == 59 || c == 4;
    }

    private boolean isWhiteSpace(char c) {
        return c == 32;
    }

    private boolean isAlphaLowerCase(char c) {
        return c > 96 && c < 123;
    }

    private boolean isAlphaUpperCase(char c) {
        return c > 64 && c < 91;
    }

    private boolean isNumber(char c) {
        return c > 47 && c < 58;
    }
}
