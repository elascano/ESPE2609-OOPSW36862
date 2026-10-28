package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Daniel Sangoquiza, SaborCode, @ESPE
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    @Test
    public void testCelsiusToFahrenheitFreezing() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(32.0, instance.celsiusToFahrenheit(0.0), 0.0001);
    }

    @Test
    public void testCelsiusToFahrenheitBoiling() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(212.0, instance.celsiusToFahrenheit(100.0), 0.0001);
    }

    @Test
    public void testCelsiusToFahrenheitCrossPoint() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(-40.0, instance.celsiusToFahrenheit(-40.0), 0.0001);
    }

    @Test
    public void testFahrenheitToCelsiusFreezing() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(0.0, instance.fahrenheitToCelsius(32.0), 0.0001);
    }

    @Test
    public void testFahrenheitToCelsiusBoiling() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(100.0, instance.fahrenheitToCelsius(212.0), 0.0001);
    }

    @Test
    public void testFahrenheitToCelsiusBodyTemp() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(37.0, instance.fahrenheitToCelsius(98.6), 0.0001);
    }

    @Test
    public void testFahrenheitToCelsiusCrossPoint() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(-40.0, instance.fahrenheitToCelsius(-40.0), 0.0001);
    }

    @Test
    public void testCelsiusToFahrenheitIntentionalFail() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(50.0, instance.celsiusToFahrenheit(10.0), 0.0001);
    }

    @Test
    public void testFahrenheitToCelsiusIntentionalFail() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(10.0, instance.fahrenheitToCelsius(50.0), 0.0001);
    }

    @Test
    public void testCelsiusToFahrenheitPrecisionFail() {
        TemperatureConverter instance = new TemperatureConverter();
        assertEquals(98.6, instance.celsiusToFahrenheit(37.0), 0.0001);
    }
}