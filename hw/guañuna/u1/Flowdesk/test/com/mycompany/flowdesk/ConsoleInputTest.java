package com.mycompany.flowdesk;

import enums.Role;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import models.Category;
import models.Customer;
import models.Product;
import models.User;
import models.WorkOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConsoleInput;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputTest {
    @BeforeEach
    void resetData() {
        Customer.setAll(new ArrayList<>());
        Category.setAll(new ArrayList<>());
        Product.setAll(new ArrayList<>());
        WorkOrder.setAll(new ArrayList<>());
    }

    @Test
    void retriesMalformedOverflowAndOutOfRangeOptions() {
        Scanner input = new Scanner("abc\n9999999999999\n-1\n8\n 2 \n");
        assertEquals(2, ConsoleInput.readOption(input, "Opcion: ", 0, 3));
    }

    @Test
    void retriesNonPositiveQuantities() {
        assertEquals(3, ConsoleInput.readPositiveInt(new Scanner("0\n-2\nabc\n3\n"), "Cantidad: "));
    }

    @Test
    void rejectsNonFiniteNegativeAndMalformedAmounts() {
        Scanner input = new Scanner("NaN\nInfinity\n1e999\n-1\nabc\n12.50\n");
        assertEquals(12.5, ConsoleInput.readAmount(input, "Precio: "));
        assertEquals(0, ConsoleInput.readAmount(new Scanner("0\n"), "Precio: "));
    }

    @Test
    void positiveAmountRetriesZero() {
        assertEquals(5, ConsoleInput.readPositiveAmount(new Scanner("0\n5\n"), "Monto: "));
    }

    @Test
    void requiredTextRetriesBlankAndPreservesPasswordCharacters() {
        assertEquals(" secret ", ConsoleInput.readRequiredText(new Scanner("\n   \n secret \n"), "Texto: "));
    }

    @Test
    void cancellationWorksForEveryReader() {
        assertThrows(CancellationException.class,
                () -> ConsoleInput.readRequiredText(new Scanner(":cancelar\n"), "Texto: "));
        assertThrows(CancellationException.class,
                () -> ConsoleInput.readPositiveInt(new Scanner(":cancelar\n"), "Cantidad: "));
        assertThrows(CancellationException.class,
                () -> ConsoleInput.readAmount(new Scanner(":cancelar\n"), "Monto: "));
    }

    @Test
    void exhaustedInputDoesNotRetryForever() {
        assertThrows(NoSuchElementException.class,
                () -> ConsoleInput.readInt(new Scanner("abc\n"), "Opcion: "));
    }

    @Test
    void optionBoundsAreInclusiveAndDoNotConsumeFollowingField() {
        Scanner input = new Scanner("0\n3\nAna\n");
        assertEquals(0, ConsoleInput.readOption(input, "Opcion: ", 0, 3));
        assertEquals(3, ConsoleInput.readOption(input, "Opcion: ", 0, 3));
        assertEquals("Ana", ConsoleInput.readRequiredText(input, "Nombre: "));
    }

    @Test
    void integerLimitsAreAcceptedAfterOverflowIsRejected() {
        Scanner input = new Scanner("2147483648\n2147483647\n-2147483649\n-2147483648\n");
        assertEquals(Integer.MAX_VALUE, ConsoleInput.readInt(input, "Entero: "));
        assertEquals(Integer.MIN_VALUE, ConsoleInput.readInt(input, "Entero: "));
    }

    @Test
    void cancellationAfterInvalidAmountLeavesFollowingMenuOptionAvailable() {
        Scanner input = new Scanner("texto\n-1\n :CANCELAR \n0\n");
        assertThrows(CancellationException.class,
                () -> ConsoleInput.readPositiveAmount(input, "Monto: "));
        assertEquals(0, ConsoleInput.readOption(input, "Opcion: ", 0, 3));
    }

    @Test
    void cancelledCustomerFormReturnsToMenuWithoutSaving() {
        Customer.showManagementMenu(new Scanner("1\nC1\n:cancelar\n0\n"));
        assertTrue(Customer.getAll().isEmpty());
    }

    @Test
    void cancelledCatalogFormReturnsToCatalogWithoutSaving() {
        Flowdesk.showCatalogMenu(new Scanner("1\nCAT1\n:cancelar\n0\n"));
        assertTrue(Category.getAll().isEmpty());
    }

    @Test
    void orderCreationRetriesInvalidQuantityAndMenuOption() {
        new Customer("C1", "Ana", "099", "171", "Quito").register();
        Category category = new Category("CAT1", "Impresion", "Catalogo");
        new Product("P1", "Volante", "A4", 2.5, category).register();
        User employee = new User("U1", "ana", "secret", Role.EMPLOYEE);
        WorkOrder.registerFromConsole(new Scanner("C1\n9\n1\nP1\nabc\n0\n2\n0\n"), employee);
        assertEquals(1, WorkOrder.getAll().size());
        assertEquals(5, WorkOrder.getAll().get(0).getTotal());
        assertEquals(2, WorkOrder.getAll().get(0).getDetails().get(0).getQuantity());
    }
}
