package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class TextStats {

    private Integer totalChars;
    private Integer totalNumericChars;
    private Integer totalAlphaChars;
    private Integer totalUpperCaseAlphaChars;
    private Integer totalLowerCaseAlphaChars;
    private Integer totalWhiteSpace;
    private Integer totalPunctuationSpace;
    private Integer totalWords;

    private List<CharStat> top10;
}
