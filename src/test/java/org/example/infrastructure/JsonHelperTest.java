package org.example.infrastructure;

import org.example.domain.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonHelperTest {

    private JsonHelper helper;

    @BeforeEach
    void setUp() {

        helper = new JsonHelper();
    }

    @Test
    void toJson() {

        Car c = new Car()
                .brand("mazda")
                .doors(5);

        String json = helper.toJson(c);

        Assertions.assertEquals("{\"brand\":\"mazda\",\"doors\":5}", json);
    }

    @Test
    void fromJson() {

        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";

        Car car = helper.fromJson(json, Car.class);

        Assertions.assertEquals("Jeep", car.brand());
        Assertions.assertEquals(3, car.doors());
    }
}