package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleAdapterTest {

    private ConsoleAdapter instance;
    private final InputStream standardIn = System.in;

    public ConsoleAdapterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        instance = new ConsoleAdapter();
    }

    // --- Tests for readText (10 tests) ---

    @Test
    public void testReadTextStandardWord() {
        provideInput("Hola\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("Hola", result.trim());
    }

    @Test
    public void testReadTextMultipleWords() {
        provideInput("Hola Mundo\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("Hola Mundo", result.trim());
    }

    @Test
    public void testReadTextWithNumbers() {
        provideInput("Texto123\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("Texto123", result.trim());
    }

    @Test
    public void testReadTextSpecialCharacters() {
        provideInput("@#%&*\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("@#%&*", result.trim());
    }

    @Test
    public void testReadTextLeadingTrailingSpaces() {
        provideInput("  Espacios  \n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("Espacios", result.trim());
    }

    @Test
    public void testReadTextEmptyString() {
        provideInput("\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("", result.trim());
    }

    @Test
    public void testReadTextSingleCharacter() {
        provideInput("A\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals("A", result.trim());
    }

    @Test
    public void testReadTextLongString() {
        String longText = "Este es un texto muy largo para probar la capacidad de lectura del adaptador de consola";
        provideInput(longText + "\n");
        String result = instance.readText("Ingrese texto:");
        assertEquals(longText, result.trim());
    }

    @Test
    public void testReadTextNullPrompt() {
        provideInput("Test\n");
        String result = instance.readText(null);
        assertEquals("Test", result.trim());
    }

    @Test
    public void testReadTextEmptyPrompt() {
        provideInput("Test2\n");
        String result = instance.readText("");
        assertEquals("Test2", result.trim());
    }

    // --- Tests for readInt (10 tests) ---

    @Test
    public void testReadIntPositivePositive() {
        provideInput("15\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(15, result);
    }

    @Test
    public void testReadIntNegative() {
        provideInput("-10\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(-10, result);
    }

    @Test
    public void testReadIntZero() {
        provideInput("0\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(0, result);
    }

    @Test
    public void testReadIntMaxInteger() {
        provideInput("2147483647\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void testReadIntMinInteger() {
        provideInput("-2147483648\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    public void testReadIntLeadingZeros() {
        provideInput("007\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(7, result);
    }

    @Test
    public void testReadIntWithPlusSign() {
        provideInput("+25\n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(25, result);
    }

    @Test
    public void testReadIntWithSpaces() {
        provideInput("  42  \n");
        int result = instance.readInt("Ingrese entero:");
        assertEquals(42, result);
    }

    @Test
    public void testReadIntNullPrompt() {
        provideInput("100\n");
        int result = instance.readInt(null);
        assertEquals(100, result);
    }

    @Test
    public void testReadIntEmptyPrompt() {
        provideInput("99\n");
        int result = instance.readInt("");
        assertEquals(99, result);
    }

    // --- Tests for readMoney (10 tests) ---

    @Test
    public void testReadMoneyStandardDecimal() {
        provideInput("10.50\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("10.50"), result);
    }

    @Test
    public void testReadMoneyWholeNumber() {
        provideInput("100\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("100"), result);
    }

    @Test
    public void testReadMoneyZero() {
        provideInput("0.00\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    public void testReadMoneyNoLeadingZero() {
        provideInput(".75\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("0.75"), result);
    }

    @Test
    public void testReadMoneyNegativeAmount() {
        provideInput("-20.50\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("-20.50"), result);
    }

    @Test
    public void testReadMoneyLargeAmount() {
        provideInput("9999999.99\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("9999999.99"), result);
    }

    @Test
    public void testReadMoneyManyDecimals() {
        provideInput("10.12345\n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("10.12345"), result);
    }

    @Test
    public void testReadMoneyWithSpaces() {
        provideInput("  50.25  \n");
        BigDecimal result = instance.readMoney("Ingrese dinero:");
        assertEquals(new BigDecimal("50.25"), result);
    }

    @Test
    public void testReadMoneyNullPrompt() {
        provideInput("5.5\n");
        BigDecimal result = instance.readMoney(null);
        assertEquals(new BigDecimal("5.5"), result);
    }

    @Test
    public void testReadMoneyEmptyPrompt() {
        provideInput("3.14\n");
        BigDecimal result = instance.readMoney("");
        assertEquals(new BigDecimal("3.14"), result);
    }
}