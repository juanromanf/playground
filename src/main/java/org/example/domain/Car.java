package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
public class Car {

    private String brand;

    private Integer doors;
}
