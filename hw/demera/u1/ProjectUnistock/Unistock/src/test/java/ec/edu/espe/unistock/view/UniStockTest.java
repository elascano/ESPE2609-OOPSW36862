package ec.edu.espe.unistock.view;

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class UniStockTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    private static final String USERS_FILE = "users.json";
    private static final String SALES_FILE = "sales.json";
    private static final String BRANDS_FILE = "brands.json";
    private static final String TYPES_FILE = "types.json";
    private static final String SIZES_FILE = "sizes.json";
    private static final String SCHOOLS_FILE = "schools.json";

    @BeforeEach
    void setUp() {
        cleanFiles();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        cleanFiles();
    }

    private void cleanFiles() {
        String[] files = {USERS_FILE, SALES_FILE, BRANDS_FILE, TYPES_FILE, SIZES_FILE, SCHOOLS_FILE};
        for (String f : files) {
            File file = new File(f);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private Object invokePrivateMethod(Object instance, String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
        Method method = instance.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    @Test
    void testMainAndRunExitImmediatelyOnEmptyUser() {
        provideInput("\n");
        assertDoesNotThrow(() -> UniStock.main(new String[]{}));
        assertTrue(outContent.toString().contains("===== UniStock by Byte & Fabric Devs ====="));
        assertTrue(outContent.toString().contains("Hasta pronto."));
    }

    @Test
    void testRunCompletesFullSessionAndExits() {
        provideInput("admin\nadmin123\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("MENU PRINCIPAL"));
    }

    @Test
    void testRunMultipleLoginsThenExit() {
        provideInput("invalid\npass\nadmin\nadmin123\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("Credenciales incorrectas."));
    }

    @Test
    void testRunExecutesAsSalesperson() {
        provideInput("admin\nadmin123\n6\n1\nseller1\npass123\n2\n0\nseller1\npass123\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("seller1 (Vendedor)"));
    }

    @Test
    void testRunExecutesAsCustomer() {
        provideInput("admin\nadmin123\n6\n1\nclient1\npass123\n3\n0\nclient1\npass123\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("client1 (Cliente)"));
    }

    @Test
    void testRunHandlesInvalidMenuOption() {
        provideInput("admin\nadmin123\n99\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testRunHandlesMultipleInvalidOptions() {
        provideInput("admin\nadmin123\n88\n77\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testRunExecutesProductListingOption() {
        provideInput("admin\nadmin123\n1\n0\n\n");
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            Method runMethod = UniStock.class.getDeclaredMethod("run");
            runMethod.setAccessible(true);
            runMethod.invoke(uniStock);
        });
        assertTrue(outContent.toString().contains("=== CATALOGO DE PRODUCTOS ==="));
    }

    @Test
    void testRunPrintsWelcomeHeader() {
        provideInput("\n");
        UniStock.main(new String[]{});
        assertTrue(outContent.toString().startsWith("===== UniStock by Byte & Fabric Devs ====="));
    }

    @Test
    void testRunInstanceCreationNotNull() {
        UniStock uniStock = new UniStock();
        assertNotNull(uniStock);
    }

    @Test
    void testLoginSuccessfulAdmin() throws Exception {
        provideInput("admin\nadmin123\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void testLoginEmptyUsernameReturnsNull() throws Exception {
        provideInput("\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNull(user);
    }

    @Test
    void testLoginBlankUsernameReturnsNull() throws Exception {
        provideInput("   \n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNull(user);
    }

    @Test
    void testLoginInvalidCredentialsThenValid() throws Exception {
        provideInput("admin\nwrong\nadmin\nadmin123\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
        assertTrue(outContent.toString().contains("Credenciales incorrectas."));
    }

    @Test
    void testLoginMultipleInvalidCredentialsThenExit() throws Exception {
        provideInput("user1\npass1\nuser2\npass2\n\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNull(user);
    }

    @Test
    void testLoginCaseSensitivePasswordFails() throws Exception {
        provideInput("admin\nADMIN123\n\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNull(user);
    }

    @Test
    void testLoginCaseSensitiveUsernameFails() throws Exception {
        provideInput("ADMIN\nadmin123\n\n");
        UniStock uniStock = new UniStock();
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNull(user);
    }

    @Test
    void testLoginCreatedSalesperson() throws Exception {
        provideInput("admin\nadmin123\n6\n1\nseller\npass\n2\n0\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        provideInput("seller\npass\n");
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNotNull(user);
        assertEquals(Role.SALESPERSON, user.getRole());
    }

    @Test
    void testLoginCreatedCustomer() throws Exception {
        provideInput("admin\nadmin123\n6\n1\nclient\npass\n3\n0\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        provideInput("client\npass\n");
        User user = (User) invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertNotNull(user);
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    void testLoginPromptTextPrinted() throws Exception {
        provideInput("\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "login", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- Iniciar sesion (usuario vacio para salir) ---"));
    }

    @Test
    void testOpenSessionAdminTriggersLowStockCheck() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin);
        assertTrue(outContent.toString().contains("===== MENU PRINCIPAL - admin (Administrador) ====="));
    }

    @Test
    void testOpenSessionSalespersonSkipsLowStockCheckOnStart() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("seller", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, seller);
        assertFalse(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
    }

    @Test
    void testOpenSessionCustomerSkipsLowStockCheckOnStart() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User customer = new User("client", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, customer);
        assertFalse(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
    }

    @Test
    void testOpenSessionLoopTerminatesOnZero() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        assertDoesNotThrow(() -> invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin));
    }

    @Test
    void testOpenSessionExecutesOptionThenExits() throws Exception {
        provideInput("1\n0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin);
        assertTrue(outContent.toString().contains("=== CATALOGO DE PRODUCTOS ==="));
    }

    @Test
    void testOpenSessionExecutesMultipleOptionsBeforeExit() throws Exception {
        provideInput("1\n1\n0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin);
        assertTrue(outContent.toString().contains("=== CATALOGO DE PRODUCTOS ==="));
    }

    @Test
    void testOpenSessionAdminMenuOptionsDisplayed() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin);
        String output = outContent.toString();
        assertTrue(output.contains("2. Registrar producto"));
        assertTrue(output.contains("4. Registrar venta"));
        assertTrue(output.contains("6. Gestionar usuarios / Exportar reporte"));
    }

    @Test
    void testOpenSessionSalespersonMenuOptionsDisplayed() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("seller", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, seller);
        String output = outContent.toString();
        assertFalse(output.contains("2. Registrar producto"));
        assertTrue(output.contains("4. Registrar venta"));
        assertFalse(output.contains("6. Gestionar usuarios / Exportar reporte"));
    }

    @Test
    void testOpenSessionCustomerMenuOptionsDisplayed() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User customer = new User("client", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, customer);
        String output = outContent.toString();
        assertFalse(output.contains("2. Registrar producto"));
        assertFalse(output.contains("4. Registrar venta"));
        assertFalse(output.contains("6. Gestionar usuarios / Exportar reporte"));
        assertTrue(output.contains("1. Ver / buscar productos"));
    }

    @Test
    void testOpenSessionWithInvalidInputRetry() throws Exception {
        provideInput("abc\n0\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "openSession", new Class<?>[]{User.class}, admin);
        assertTrue(outContent.toString().contains("Ingrese un numero entero valido."));
    }

    @Test
    void testPrintMainMenuAdmin() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, admin);
        String output = outContent.toString();
        assertTrue(output.contains("1. Ver / buscar productos"));
        assertTrue(output.contains("2. Registrar producto"));
        assertTrue(output.contains("3. Eliminar producto"));
        assertTrue(output.contains("4. Registrar venta"));
        assertTrue(output.contains("5. Ver / Exportar ventas realizadas"));
        assertTrue(output.contains("6. Gestionar usuarios / Exportar reporte"));
        assertTrue(output.contains("7. Gestionar catalogos"));
        assertTrue(output.contains("8. Consultar alertas de stock bajo"));
        assertTrue(output.contains("9. Actualizar inventario (Reabastecer)"));
        assertTrue(output.contains("0. Cerrar sesion"));
    }

    @Test
    void testPrintMainMenuSalesperson() throws Exception {
        UniStock uniStock = new UniStock();
        User seller = new User("vendedor1", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, seller);
        String output = outContent.toString();
        assertTrue(output.contains("1. Ver / buscar productos"));
        assertFalse(output.contains("2. Registrar producto"));
        assertFalse(output.contains("3. Eliminar producto"));
        assertTrue(output.contains("4. Registrar venta"));
        assertTrue(output.contains("5. Ver / Exportar ventas realizadas"));
        assertFalse(output.contains("6. Gestionar usuarios / Exportar reporte"));
        assertFalse(output.contains("7. Gestionar catalogos"));
        assertFalse(output.contains("8. Consultar alertas de stock bajo"));
        assertFalse(output.contains("9. Actualizar inventario (Reabastecer)"));
        assertTrue(output.contains("0. Cerrar sesion"));
    }

    @Test
    void testPrintMainMenuCustomer() throws Exception {
        UniStock uniStock = new UniStock();
        User customer = new User("cliente1", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, customer);
        String output = outContent.toString();
        assertTrue(output.contains("1. Ver / buscar productos"));
        assertFalse(output.contains("2. Registrar producto"));
        assertFalse(output.contains("3. Eliminar producto"));
        assertFalse(output.contains("4. Registrar venta"));
        assertFalse(output.contains("5. Ver / Exportar ventas realizadas"));
        assertFalse(output.contains("6. Gestionar usuarios / Exportar reporte"));
        assertFalse(output.contains("7. Gestionar catalogos"));
        assertFalse(output.contains("8. Consultar alertas de stock bajo"));
        assertFalse(output.contains("9. Actualizar inventario (Reabastecer)"));
        assertTrue(output.contains("0. Cerrar sesion"));
    }

    @Test
    void testPrintMainMenuContainsUserRepresentation() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, admin);
        assertTrue(outContent.toString().contains("MENU PRINCIPAL - admin (Administrador)"));
    }

    @Test
    void testPrintMainMenuNotNullOutput() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, admin);
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    void testPrintMainMenuConsistentCall() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, admin);
        String out1 = outContent.toString();
        outContent.reset();
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, admin);
        String out2 = outContent.toString();
        assertEquals(out1, out2);
    }

    @Test
    void testPrintMainMenuCustomUsername() throws Exception {
        UniStock uniStock = new UniStock();
        User user = new User("dennis", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, user);
        assertTrue(outContent.toString().contains("dennis (Vendedor)"));
    }

    @Test
    void testPrintMainMenuIncludesZeroToExit() throws Exception {
        UniStock uniStock = new UniStock();
        User user = new User("user", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, user);
        assertTrue(outContent.toString().contains("0. Cerrar sesion"));
    }

    @Test
    void testPrintMainMenuIncludesOptionOneForEveryone() throws Exception {
        UniStock uniStock = new UniStock();
        User user = new User("user", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, user);
        assertTrue(outContent.toString().contains("1. Ver / buscar productos"));
    }

    @Test
    void testPrintMainMenuHeaderFormat() throws Exception {
        UniStock uniStock = new UniStock();
        User user = new User("user", "pass", Role.CUSTOMER);
        invokePrivateMethod(uniStock, "printMainMenu", new Class<?>[]{User.class}, user);
        assertTrue(outContent.toString().startsWith("\n===== MENU PRINCIPAL"));
    }

    @Test
    void testHandleOptionOptionZeroReturnsFalse() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, 0);
        assertFalse(result);
    }

    @Test
    void testHandleOptionOptionOneReturnsTrue() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, 1);
        assertTrue(result);
    }

    @Test
    void testHandleOptionOptionTwoAllowedForAdmin() throws Exception {
        provideInput("3\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, 2);
        assertTrue(result);
    }

    @Test
    void testHandleOptionOptionTwoDeniedForCustomer() throws Exception {
        UniStock uniStock = new UniStock();
        User customer = new User("client", "pass", Role.CUSTOMER);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, customer, 2);
        assertTrue(result);
        assertTrue(outContent.toString().contains("Acceso denegado para su rol."));
    }

    @Test
    void testHandleOptionOptionFourAllowedForSalesperson() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("seller", "pass", Role.SALESPERSON);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, seller, 4);
        assertTrue(result);
    }

    @Test
    void testHandleOptionOptionFourDeniedForCustomer() throws Exception {
        UniStock uniStock = new UniStock();
        User customer = new User("client", "pass", Role.CUSTOMER);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, customer, 4);
        assertTrue(result);
        assertTrue(outContent.toString().contains("Acceso denegado para su rol."));
    }

    @Test
    void testHandleOptionOptionSixAllowedForAdmin() throws Exception {
        provideInput("2\n");
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, 6);
        assertTrue(result);
    }

    @Test
    void testHandleOptionOptionSixDeniedForSalesperson() throws Exception {
        UniStock uniStock = new UniStock();
        User seller = new User("seller", "pass", Role.SALESPERSON);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, seller, 6);
        assertTrue(result);
        assertTrue(outContent.toString().contains("Acceso denegado para su rol."));
    }

    @Test
    void testHandleOptionInvalidOptionReturnsTrue() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, 99);
        assertTrue(result);
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testHandleOptionNegativeOptionReturnsTrue() throws Exception {
        UniStock uniStock = new UniStock();
        User admin = new User("admin", "admin123", Role.ADMINISTRATOR);
        Boolean result = (Boolean) invokePrivateMethod(uniStock, "handleOption", new Class<?>[]{User.class, int.class}, admin, -1);
        assertTrue(result);
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testRunIfAllowedExecutesActionWhenAllowed() throws Exception {
        UniStock uniStock = new UniStock();
        boolean[] executed = {false};
        Runnable action = () -> executed[0] = true;

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertTrue(executed[0]);
    }

    @Test
    void testRunIfAllowedPrintsDeniedWhenNotAllowed() throws Exception {
        UniStock uniStock = new UniStock();
        boolean[] executed = {false};
        Runnable action = () -> executed[0] = true;

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, false, action);
        assertFalse(executed[0]);
        assertTrue(outContent.toString().contains("Acceso denegado para su rol."));
    }

    @Test
    void testRunIfAllowedCatchesRuntimeException() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {
            throw new RuntimeException("Error simulado");
        };

        assertDoesNotThrow(() ->
            invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action)
        );
        assertTrue(outContent.toString().contains("Error: Error simulado"));
    }

    @Test
    void testRunIfAllowedCatchesIllegalArgumentException() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {
            throw new IllegalArgumentException("Argumento invalido");
        };

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertTrue(outContent.toString().contains("Error: Argumento invalido"));
    }

    @Test
    void testRunIfAllowedCatchesIllegalStateException() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {
            throw new IllegalStateException("Estado invalido");
        };

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertTrue(outContent.toString().contains("Error: Estado invalido"));
    }

    @Test
    void testRunIfAllowedDoesNotExecuteActionWhenNotAllowed() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {
            throw new RuntimeException("No debe ejecutarse");
        };

        assertDoesNotThrow(() ->
            invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, false, action)
        );
    }

    @Test
    void testRunIfAllowedWithNullMessageException() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {
            throw new RuntimeException((String) null);
        };

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertTrue(outContent.toString().contains("Error: null"));
    }

    @Test
    void testRunIfAllowedSequentialInvocations() throws Exception {
        UniStock uniStock = new UniStock();
        int[] counter = {0};
        Runnable action = () -> counter[0]++;

        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertEquals(2, counter[0]);
    }

    @Test
    void testRunIfAllowedAllowedFalseDoesNotPrintErrorMessage() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {};
        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, false, action);
        assertFalse(outContent.toString().contains("Error:"));
    }

    @Test
    void testRunIfAllowedAllowedTrueDoesNotPrintDeniedMessage() throws Exception {
        UniStock uniStock = new UniStock();
        Runnable action = () -> {};
        invokePrivateMethod(uniStock, "runIfAllowed", new Class<?>[]{boolean.class, Runnable.class}, true, action);
        assertFalse(outContent.toString().contains("Acceso denegado"));
    }

    @Test
    void testRegisterSaleMenuCancelImmediateWithZero() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Venta cancelada (sin items)."));
    }

    @Test
    void testRegisterSaleMenuInvalidProductThenCancel() throws Exception {
        provideInput("999\n1\n0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Error: El producto no existe."));
        assertTrue(outContent.toString().contains("Venta cancelada (sin items)."));
    }

    @Test
    void testRegisterSaleMenuSuccessfulSaleConfirmation() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n2\n0\nS\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("registrada con exito."));
    }

    @Test
    void testRegisterSaleMenuSaleCancellationOnN() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n2\n0\nN\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Venta cancelada."));
    }

    @Test
    void testRegisterSaleMenuInsufficientStockError() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n5\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n100\n0\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Error: Stock insuficiente."));
    }

    @Test
    void testRegisterSaleMenuDisplaysSubtotalTaxAndTotal() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n4\n0\nN\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        String output = outContent.toString();
        assertTrue(output.contains("Subtotal: $10.00"));
        assertTrue(output.contains("IVA (15%): $1.50"));
        assertTrue(output.contains("Total: $11.50"));
    }

    @Test
    void testRegisterSaleMenuMultipleProductsAdded() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno 1\n2.50\n50\nNorma\nCuaderno\n2\nCuaderno 2\n3.00\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n1\n2\n1\n0\nS\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Producto agregado a la venta."));
    }

    @Test
    void testRegisterSaleMenuHeaderPrinted() throws Exception {
        provideInput("0\n");
        UniStock uniStock = new UniStock();
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("--- REGISTRAR VENTA ---"));
    }

    @Test
    void testRegisterSaleMenuCaseInsensitiveConfirmS() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n1\n0\ns\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("registrada con exito."));
    }

    @Test
    void testRegisterSaleMenuInvalidConfirmationInputCancels() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n1\n0\nXYZ\n");
        User seller = new User("vendedor", "pass", Role.SALESPERSON);
        invokePrivateMethod(uniStock, "registerSaleMenu", new Class<?>[]{User.class}, seller);
        assertTrue(outContent.toString().contains("Venta cancelada."));
    }

    @Test
    void testViewSalesMenuListEmpty() throws Exception {
        provideInput("1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("No hay ventas registradas."));
    }

    @Test
    void testViewSalesMenuListWithSales() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno\n2.50\n50\nNorma\nCuaderno\n4\n1\n2\n0\nS\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("1\n");
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Venta #1"));
    }

    @Test
    void testViewSalesMenuExportCsvDefaultFileName() throws Exception {
        provideInput("2\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Proceso de exportacion de ventas finalizado."));
        assertTrue(new File("ventas.csv").exists());
    }

    @Test
    void testViewSalesMenuExportCsvCustomFileName() throws Exception {
        provideInput("2\ncustom_ventas.csv\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Proceso de exportacion de ventas finalizado."));
        assertTrue(new File("custom_ventas.csv").exists());
        new File("custom_ventas.csv").delete();
    }

    @Test
    void testViewSalesMenuInvalidOption() throws Exception {
        provideInput("9\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testViewSalesMenuNegativeOption() throws Exception {
        provideInput("-1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testViewSalesMenuHeaderPrinted() throws Exception {
        provideInput("1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- CONSULTA DE VENTAS ---"));
    }

    @Test
    void testViewSalesMenuOptionsDisplayed() throws Exception {
        provideInput("1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        String output = outContent.toString();
        assertTrue(output.contains("1. Listar historial de ventas"));
        assertTrue(output.contains("2. Exportar reporte de ventas a CSV"));
    }

    @Test
    void testViewSalesMenuExportCsvAutoAppendsExtension() throws Exception {
        provideInput("2\nventasReport\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(new File("ventasReport.csv").exists());
        new File("ventasReport.csv").delete();
    }

    @Test
    void testViewSalesMenuMultipleInvocations() throws Exception {
        provideInput("1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        provideInput("1\n");
        invokePrivateMethod(uniStock, "viewSalesMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("No hay ventas registradas."));
    }

    @Test
    void testManageUsersMenuRegisterAdministrator() throws Exception {
        provideInput("1\nadmin2\npass123\n1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Usuario registrado exitosamente."));
    }

    @Test
    void testManageUsersMenuRegisterSalesperson() throws Exception {
        provideInput("1\nvendedor1\npass123\n2\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Usuario registrado exitosamente."));
    }

    @Test
    void testManageUsersMenuRegisterCustomerByDefault() throws Exception {
        provideInput("1\ncliente1\npass123\n3\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Usuario registrado exitosamente."));
    }

    @Test
    void testManageUsersMenuRegisterDuplicateUserError() throws Exception {
        provideInput("1\nadmin\nadmin123\n1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Error al registrar usuario: El usuario ya existe."));
    }

    @Test
    void testManageUsersMenuListUsers() throws Exception {
        provideInput("2\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains(" - admin (Administrador)"));
    }

    @Test
    void testManageUsersMenuExportCsvDefaultFileName() throws Exception {
        provideInput("3\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Proceso de exportacion de usuarios finalizado."));
        assertTrue(new File("usuarios.csv").exists());
    }

    @Test
    void testManageUsersMenuExportCsvCustomFileName() throws Exception {
        provideInput("3\ncustom_usuarios.csv\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Proceso de exportacion de usuarios finalizado."));
        assertTrue(new File("custom_usuarios.csv").exists());
        new File("custom_usuarios.csv").delete();
    }

    @Test
    void testManageUsersMenuInvalidOption() throws Exception {
        provideInput("9\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Opcion invalida."));
    }

    @Test
    void testManageUsersMenuHeaderPrinted() throws Exception {
        provideInput("2\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- GESTION DE USUARIOS ---"));
    }

    @Test
    void testManageUsersMenuRoleSelectionPromptPrinted() throws Exception {
        provideInput("1\nuser1\npass1\n1\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "manageUsersMenu", new Class<?>[]{});
        assertTrue(outContent.toString().contains("1. ADMINISTRATOR  2. SALESPERSON  3. CUSTOMER"));
    }

    @Test
    void testCheckLowStockAlertsNoAlertsWhenEmpty() throws Exception {
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertFalse(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
    }

    @Test
    void testCheckLowStockAlertsDisplaysLowStockProducts() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n3\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
        assertTrue(outContent.toString().contains("Producto: Cuaderno Norma"));
    }

    @Test
    void testCheckLowStockAlertsIgnoresSufficientStock() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n20\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertFalse(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
    }

    @Test
    void testCheckLowStockAlertsExactThresholdIncluded() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n5\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
        assertTrue(outContent.toString().contains("Stock: 5"));
    }

    @Test
    void testCheckLowStockAlertsZeroStockIncluded() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n0\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Stock: 0"));
    }

    @Test
    void testCheckLowStockAlertsMultipleLowStockProducts() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nItem 1\n2.50\n2\nNorma\nCuaderno\n2\nItem 2\n3.00\n1\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Producto: Item 1"));
        assertTrue(outContent.toString().contains("Producto: Item 2"));
    }

    @Test
    void testCheckLowStockAlertsExecutesOnAdminLogin() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n2\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        provideInput("admin\nadmin123\n0\n\n");
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});
        assertTrue(outContent.toString().contains("--- ALERTA DE STOCK BAJO ---"));
    }

    @Test
    void testCheckLowStockAlertsDoesNotCrashWhenCalledMultipleTimes() throws Exception {
        UniStock uniStock = new UniStock();
        assertDoesNotThrow(() -> {
            invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
            invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        });
    }

    @Test
    void testCheckLowStockAlertsFormatContainsIdAndStock() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n2\nCuaderno Norma\n2.50\n3\nNorma\nCuaderno\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("(ID: 1) - Stock: 3"));
    }

    @Test
    void testCheckLowStockAlertsStationeryAndUniforms() throws Exception {
        provideInput("admin\nadmin123\n7\n1\nNorma\n7\n3\nCuaderno\n7\n2\nM\n7\n4\nSan Gabriel\n2\nCuaderno\n2.50\n2\nNorma\nCuaderno\n1\nCamisa\n12.00\n1\nM\nSan Gabriel\n0\n\n");
        UniStock uniStock = new UniStock();
        invokePrivateMethod(uniStock, "run", new Class<?>[]{});

        outContent.reset();
        invokePrivateMethod(uniStock, "checkLowStockAlerts", new Class<?>[]{});
        assertTrue(outContent.toString().contains("Producto: Cuaderno"));
        assertTrue(outContent.toString().contains("Producto: Camisa"));
    }
}