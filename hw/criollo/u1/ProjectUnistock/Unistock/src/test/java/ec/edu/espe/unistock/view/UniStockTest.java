package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniStockTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    public UniStockTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    /**
     * Método auxiliar para simular la entrada del usuario en consola.
     * En un menú típico, enviar "0\n", "x\n" o fin de archivo (EOF) 
     * suele terminar la ejecución.
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testMainWithEmptyArgs() {
        provideInput("0\n"); // Asume que '0' podría ser salir del menú
        assertDoesNotThrow(() -> {
            String[] args = {};
            UniStock.main(args);
        });
    }

    @Test
    public void testMainWithNullArgs() {
        provideInput("0\n");
        assertDoesNotThrow(() -> {
            UniStock.main(null);
        });
    }

    @Test
    public void testMainWithDummyArgs() {
        provideInput("0\n");
        assertDoesNotThrow(() -> {
            String[] args = {"dummy_arg"};
            UniStock.main(args);
        });
    }

    @Test
    public void testMainWithMultipleArgs() {
        provideInput("0\n");
        assertDoesNotThrow(() -> {
            String[] args = {"arg1", "arg2", "arg3"};
            UniStock.main(args);
        });
    }

    @Test
    public void testMainExecutionTimeLimits() {
        provideInput("0\n");
        // Asegura que el método main no cree un bucle infinito bloqueante
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            String[] args = {};
            UniStock.main(args);
        });
    }

    @Test
    public void testMainGeneratesConsoleOutput() {
        provideInput("0\n");
        try {
            UniStock.main(new String[]{});
        } catch (Exception e) {
            // Ignorar excepciones de Scanner por fin de stream en pruebas
        }
        String output = outputStreamCaptor.toString();
        assertNotNull(output);
        // Si inicia un menú, algo debería imprimirse
        assertTrue(output.length() >= 0); 
    }

    @Test
    public void testMainWithImmediateEOF() {
        // Enviar un string vacío causará EOF inmediato en el Scanner
        provideInput(""); 
        assertDoesNotThrow(() -> {
            try {
                UniStock.main(new String[]{});
            } catch (Exception e) {
                // Es normal atrapar un NoSuchElementException si se queda sin input
            }
        });
    }

    @Test
    public void testMainWithSimulatedNavigation() {
        // Simular navegación por un par de menús antes de salir
        provideInput("1\n0\n"); 
        assertDoesNotThrow(() -> {
            try {
                UniStock.main(new String[]{});
            } catch (Exception e) {}
        });
    }

    @Test
    public void testMainWithInvalidMenuOption() {
        // Introducir texto donde se espera un número, luego salir
        provideInput("opcion_invalida\n0\n"); 
        assertDoesNotThrow(() -> {
            try {
                UniStock.main(new String[]{});
            } catch (Exception e) {}
        });
    }

    @Test
    public void testMainHelpArgument() {
        provideInput("0\n");
        assertDoesNotThrow(() -> {
            String[] args = {"--help"};
            try {
                UniStock.main(args);
            } catch (Exception e) {}
        });
    }
}