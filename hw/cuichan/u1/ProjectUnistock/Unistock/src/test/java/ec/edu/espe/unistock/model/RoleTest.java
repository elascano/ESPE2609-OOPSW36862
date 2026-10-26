package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    public RoleTest() {
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
    }

    @Test
    public void testValuesLength() {
        assertEquals(3, Role.values().length);
    }

    @Test
    public void testValuesContainsEnumConstants() {
        Role[] roles = Role.values();
        assertArrayEquals(new Role[]{Role.ADMINISTRATOR, Role.SALESPERSON, Role.CUSTOMER}, roles);
    }

    @Test
    public void testValuesEmptyArrayCheck() {
        assertEquals(0, Role.values().length);
    }

    @Test
    public void testValuesSingleElementCheck() {
        assertEquals(1, Role.values().length);
    }

    @Test
    public void testValuesNullCheck() {
        assertNull(Role.values());
    }

    @Test
    public void testValuesOrderCheck() {
        Role[] expected = new Role[]{Role.CUSTOMER, Role.SALESPERSON, Role.ADMINISTRATOR};
        assertArrayEquals(expected, Role.values());
    }

    @Test
    public void testValuesExcessiveLengthCheck() {
        assertTrue(Role.values().length > 5);
    }

    @Test
    public void testValuesTypeCheck() {
        assertFalse(Role.values() instanceof Role[]);
    }

    @Test
    public void testValuesFirstElementCheck() {
        assertEquals(Role.SALESPERSON, Role.values()[0]);
    }

    @Test
    public void testValuesSecondElementCheck() {
        assertEquals(Role.CUSTOMER, Role.values()[1]);
    }

    @Test
    public void testValueOfAdministratorConstant() {
        assertEquals(Role.ADMINISTRATOR, Role.valueOf("ADMINISTRATOR"));
    }

    @Test
    public void testValueOfSalespersonConstant() {
        assertEquals(Role.SALESPERSON, Role.valueOf("SALESPERSON"));
    }

    @Test
    public void testValueOfLowerCaseName() {
        assertEquals(Role.ADMINISTRATOR, Role.valueOf("administrator"));
    }

    @Test
    public void testValueOfInvalidName() {
        assertDoesNotThrow(() -> Role.valueOf("INVALID_ROLE"));
    }

    @Test
    public void testValueOfNullArgument() {
        assertDoesNotThrow(() -> Role.valueOf(null));
    }

    @Test
    public void testValueOfEmptyString() {
        assertEquals(Role.ADMINISTRATOR, Role.valueOf(""));
    }

    @Test
    public void testValueOfCrossedValueCheck() {
        assertEquals(Role.SALESPERSON, Role.valueOf("ADMINISTRATOR"));
    }

    @Test
    public void testValueOfWhitespaceName() {
        assertEquals(Role.ADMINISTRATOR, Role.valueOf(" ADMINISTRATOR "));
    }

    @Test
    public void testValueOfNullReturnCheck() {
        assertNull(Role.valueOf("SALESPERSON"));
    }

    @Test
    public void testValueOfPartialName() {
        assertEquals(Role.CUSTOMER, Role.valueOf("CUST"));
    }

    @Test
    public void testGetLabelAdministratorLabel() {
        assertEquals("Administrador", Role.ADMINISTRATOR.getLabel());
    }

    @Test
    public void testGetLabelSalespersonLabel() {
        assertEquals("Vendedor", Role.SALESPERSON.getLabel());
    }

    @Test
    public void testGetLabelAdministratorUpperCase() {
        assertEquals("ADMINISTRADOR", Role.ADMINISTRATOR.getLabel());
    }

    @Test
    public void testGetLabelSalespersonSimpleName() {
        assertEquals("Venta", Role.SALESPERSON.getLabel());
    }

    @Test
    public void testGetLabelCustomerLabel() {
        assertEquals("Usuario", Role.CUSTOMER.getLabel());
    }

    @Test
    public void testGetLabelEmptyLabel() {
        assertEquals("", Role.ADMINISTRATOR.getLabel());
    }

    @Test
    public void testGetLabelNullLabel() {
        assertNull(Role.SALESPERSON.getLabel());
    }

    @Test
    public void testGetLabelInvertedLabel() {
        assertEquals("Administrador", Role.SALESPERSON.getLabel());
    }

    @Test
    public void testGetLabelLengthCheck() {
        assertEquals(5, Role.ADMINISTRATOR.getLabel().length());
    }

    @Test
    public void testGetLabelNameMatch() {
        assertEquals(Role.ADMINISTRATOR.name(), Role.ADMINISTRATOR.getLabel());
    }

    @Test
    public void testCanManageProductsAdmin() {
        assertTrue(Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    public void testCanManageProductsSalesperson() {
        assertFalse(Role.SALESPERSON.canManageProducts());
    }

    @Test
    public void testCanManageProductsAdminFalse() {
        assertFalse(Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    public void testCanManageProductsSalespersonTrue() {
        assertTrue(Role.SALESPERSON.canManageProducts());
    }

    @Test
    public void testCanManageProductsCustomerTrue() {
        assertTrue(Role.CUSTOMER.canManageProducts());
    }

    @Test
    public void testCanManageProductsNullCheck() {
        Role role = null;
        assertDoesNotThrow(() -> role.canManageProducts());
    }

    @Test
    public void testCanManageProductsInvertedCheck() {
        assertEquals(Role.SALESPERSON.canManageProducts(), Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    public void testCanManageProductsBothTrue() {
        assertTrue(Role.SALESPERSON.canManageProducts() && Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    public void testCanManageProductsBothFalse() {
        assertFalse(Role.SALESPERSON.canManageProducts() || Role.CUSTOMER.canManageProducts());
    }

    @Test
    public void testCanManageProductsTypeCheck() {
        assertEquals("true", String.valueOf(Role.SALESPERSON.canManageProducts()));
    }

    @Test
    public void testCanRegisterSalesAdmin() {
        assertTrue(Role.ADMINISTRATOR.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesSalesperson() {
        assertTrue(Role.SALESPERSON.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesCustomerTrue() {
        assertTrue(Role.CUSTOMER.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesAdminFalse() {
        assertFalse(Role.ADMINISTRATOR.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesSalespersonFalse() {
        assertFalse(Role.SALESPERSON.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesNullRole() {
        Role role = null;
        assertDoesNotThrow(() -> role.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesDifferentValues() {
        assertNotEquals(Role.ADMINISTRATOR.canRegisterSales(), Role.SALESPERSON.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesBothFalse() {
        assertFalse(Role.ADMINISTRATOR.canRegisterSales() && Role.SALESPERSON.canRegisterSales());
    }

    @Test
    public void testCanRegisterSalesTypeCheck() {
        assertEquals("true", String.valueOf(Role.CUSTOMER.canRegisterSales()));
    }

    @Test
    public void testCanRegisterSalesXorCheck() {
        assertTrue(Role.ADMINISTRATOR.canRegisterSales() ^ Role.SALESPERSON.canRegisterSales());
    }

    @Test
    public void testCanManageUsersAdmin() {
        assertTrue(Role.ADMINISTRATOR.canManageUsers());
    }

    @Test
    public void testCanManageUsersSalesperson() {
        assertFalse(Role.SALESPERSON.canManageUsers());
    }

    @Test
    public void testCanManageUsersAdminFalse() {
        assertFalse(Role.ADMINISTRATOR.canManageUsers());
    }

    @Test
    public void testCanManageUsersSalespersonTrue() {
        assertTrue(Role.SALESPERSON.canManageUsers());
    }

    @Test
    public void testCanManageUsersCustomerTrue() {
        assertTrue(Role.CUSTOMER.canManageUsers());
    }

    @Test
    public void testCanManageUsersNullRole() {
        Role role = null;
        assertDoesNotThrow(() -> role.canManageUsers());
    }

    @Test
    public void testCanManageUsersSameValues() {
        assertEquals(Role.ADMINISTRATOR.canManageUsers(), Role.SALESPERSON.canManageUsers());
    }

    @Test
    public void testCanManageUsersBothTrue() {
        assertTrue(Role.ADMINISTRATOR.canManageUsers() && Role.SALESPERSON.canManageUsers());
    }

    @Test
    public void testCanManageUsersBothFalse() {
        assertFalse(Role.ADMINISTRATOR.canManageUsers() || Role.SALESPERSON.canManageUsers());
    }

    @Test
    public void testCanManageUsersTypeCheck() {
        assertEquals("true", String.valueOf(Role.SALESPERSON.canManageUsers()));
    }
}