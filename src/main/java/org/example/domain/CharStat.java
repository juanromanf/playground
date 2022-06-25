package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CharStat {

    private String character;

    private Integer count;

    private Double percentage;
}
