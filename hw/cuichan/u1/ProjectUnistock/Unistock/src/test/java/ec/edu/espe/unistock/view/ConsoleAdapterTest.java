package ec.edu.espe.unistock.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleAdapterTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testReadTextStandardInput() {
        provideInput("Hola Mundo\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("Hola Mundo", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextTrimsLeadingAndTrailingWhitespace() {
        provideInput("   Texto con espacios   \n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("Texto con espacios", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextEmptyInputReturnsEmptyString() {
        provideInput("\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextBlankInputReturnsEmptyString() {
        provideInput("     \n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextSpecialCharacters() {
        provideInput("admin@espe.edu.ec#2026\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("admin@espe.edu.ec#2026", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextNumericInputAsString() {
        provideInput("12345\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("12345", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextSingleCharacter() {
        provideInput("A\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("A", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextConsecutiveReads() {
        provideInput("Primera Linea\nSegunda Linea\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("Primera Linea", adapter.readText("Prompt 1: "));
        assertEquals("Segunda Linea", adapter.readText("Prompt 2: "));
    }

    @Test
    void testReadTextNotNullResult() {
        provideInput("Prueba\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertNotNull(adapter.readText("Ingrese: "));
    }

    @Test
    void testReadTextPreservesInternalSpaces() {
        provideInput("  Cuaderno   Universitario  \n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals("Cuaderno   Universitario", adapter.readText("Ingrese: "));
    }

    @Test
    void testReadIntValidPositiveNumber() {
        provideInput("25\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(25, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntValidNegativeNumber() {
        provideInput("-10\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(-10, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntZero() {
        provideInput("0\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(0, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntWithSurroundingSpaces() {
        provideInput("   50   \n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(50, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntInvalidThenValidInput() {
        provideInput("abc\n15\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(15, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntDecimalThenValidInput() {
        provideInput("12.34\n8\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(8, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntEmptyThenValidInput() {
        provideInput("\n99\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(99, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntMultipleInvalidInputsBeforeValid() {
        provideInput("texto\n--5\n12.5.6\n100\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(100, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntMaxInteger() {
        provideInput(Integer.MAX_VALUE + "\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(Integer.MAX_VALUE, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadIntMinInteger() {
        provideInput(Integer.MIN_VALUE + "\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(Integer.MIN_VALUE, adapter.readInt("Ingrese entero: "));
    }

    @Test
    void testReadMoneyValidIntegerAmount() {
        provideInput("100\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("100"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyValidDecimalAmount() {
        provideInput("12.50\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("12.50"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyZero() {
        provideInput("0.00\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("0.00"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyNegativeAmount() {
        provideInput("-5.75\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("-5.75"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyWithSurroundingSpaces() {
        provideInput("   45.99   \n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("45.99"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyInvalidTextThenValidAmount() {
        provideInput("gratis\n15.25\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("15.25"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyEmptyThenValidAmount() {
        provideInput("\n3.50\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("3.50"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyMultipleInvalidInputsBeforeValid() {
        provideInput("abc\n$10.00\n10,00\n10.00\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("10.00"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyHighPrecisionDecimal() {
        provideInput("0.123456789\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertEquals(new BigDecimal("0.123456789"), adapter.readMoney("Ingrese monto: "));
    }

    @Test
    void testReadMoneyNotNullResult() {
        provideInput("2.50\n");
        ConsoleAdapter adapter = new ConsoleAdapter();
        assertNotNull(adapter.readMoney("Ingrese monto: "));
    }
}