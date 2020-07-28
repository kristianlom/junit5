package junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureCalculatorTest {

    private TemperatureCalculator temperatureCalculator;

    @BeforeEach
    void setUp() {
        temperatureCalculator = new TemperatureCalculator();
    }

    @AfterEach
    void tearDown() {
        temperatureCalculator = null;
    }

    @Test
    void toFahrenheitTest() {
        assertEquals(33.8, temperatureCalculator.toFahrenheit(1), 0.01);
    }

}