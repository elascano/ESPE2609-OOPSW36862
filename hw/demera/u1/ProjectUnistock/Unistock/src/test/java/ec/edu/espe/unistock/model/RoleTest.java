package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    void testGetLabelAdministrator() {
        assertEquals("Administrador", Role.ADMINISTRATOR.getLabel());
    }

    @Test
    void testGetLabelSalesperson() {
        assertEquals("Vendedor", Role.SALESPERSON.getLabel());
    }

    @Test
    void testGetLabelCustomer() {
        assertEquals("Cliente", Role.CUSTOMER.getLabel());
    }

    @Test
    void testGetLabelNotNullAdministrator() {
        assertNotNull(Role.ADMINISTRATOR.getLabel());
    }

    @Test
    void testGetLabelNotNullSalesperson() {
        assertNotNull(Role.SALESPERSON.getLabel());
    }

    @Test
    void testGetLabelNotNullCustomer() {
        assertNotNull(Role.CUSTOMER.getLabel());
    }

    @Test
    void testGetLabelNotEmptyAdministrator() {
        assertFalse(Role.ADMINISTRATOR.getLabel().isEmpty());
    }

    @Test
    void testGetLabelNotEmptySalesperson() {
        assertFalse(Role.SALESPERSON.getLabel().isEmpty());
    }

    @Test
    void testGetLabelNotEmptyCustomer() {
        assertFalse(Role.CUSTOMER.getLabel().isEmpty());
    }

    @Test
    void testGetLabelConsistency() {
        String label1 = Role.ADMINISTRATOR.getLabel();
        String label2 = Role.ADMINISTRATOR.getLabel();
        assertEquals(label1, label2);
    }

    @Test
    void testCanManageProductsAdministratorTrue() {
        assertTrue(Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    void testCanManageProductsSalespersonFalse() {
        assertFalse(Role.SALESPERSON.canManageProducts());
    }

    @Test
    void testCanManageProductsCustomerFalse() {
        assertFalse(Role.CUSTOMER.canManageProducts());
    }

    @Test
    void testCanManageProductsConsistencyAdministrator() {
        assertEquals(Role.ADMINISTRATOR.canManageProducts(), Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    void testCanManageProductsConsistencySalesperson() {
        assertEquals(Role.SALESPERSON.canManageProducts(), Role.SALESPERSON.canManageProducts());
    }

    @Test
    void testCanManageProductsConsistencyCustomer() {
        assertEquals(Role.CUSTOMER.canManageProducts(), Role.CUSTOMER.canManageProducts());
    }

    @Test
    void testCanManageProductsOnlyAdministrator() {
        for (Role role : Role.values()) {
            if (role == Role.ADMINISTRATOR) {
                assertTrue(role.canManageProducts());
            } else {
                assertFalse(role.canManageProducts());
            }
        }
    }

    @Test
    void testCanManageProductsBooleanReturn() {
        assertTrue(Role.ADMINISTRATOR.canManageProducts() || !Role.ADMINISTRATOR.canManageProducts());
    }

    @Test
    void testCanManageProductsMultipleInvocations() {
        for (int i = 0; i < 5; i++) {
            assertTrue(Role.ADMINISTRATOR.canManageProducts());
        }
    }

    @Test
    void testCanManageProductsNonAdminRolesAreFalse() {
        assertFalse(Role.SALESPERSON.canManageProducts() || Role.CUSTOMER.canManageProducts());
    }

    @Test
    void testCanRegisterSalesAdministratorTrue() {
        assertTrue(Role.ADMINISTRATOR.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesSalespersonTrue() {
        assertTrue(Role.SALESPERSON.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesCustomerFalse() {
        assertFalse(Role.CUSTOMER.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesConsistencyAdministrator() {
        assertEquals(Role.ADMINISTRATOR.canRegisterSales(), Role.ADMINISTRATOR.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesConsistencySalesperson() {
        assertEquals(Role.SALESPERSON.canRegisterSales(), Role.SALESPERSON.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesConsistencyCustomer() {
        assertEquals(Role.CUSTOMER.canRegisterSales(), Role.CUSTOMER.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesAdminAndSalespersonEqual() {
        assertEquals(Role.ADMINISTRATOR.canRegisterSales(), Role.SALESPERSON.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesCustomerDifferentFromAdmin() {
        assertNotEquals(Role.CUSTOMER.canRegisterSales(), Role.ADMINISTRATOR.canRegisterSales());
    }

    @Test
    void testCanRegisterSalesMultipleInvocations() {
        for (int i = 0; i < 5; i++) {
            assertTrue(Role.SALESPERSON.canRegisterSales());
        }
    }

    @Test
    void testCanRegisterSalesPermissionCount() {
        int count = 0;
        for (Role role : Role.values()) {
            if (role.canRegisterSales()) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    void testCanManageUsersAdministratorTrue() {
        assertTrue(Role.ADMINISTRATOR.canManageUsers());
    }

    @Test
    void testCanManageUsersSalespersonFalse() {
        assertFalse(Role.SALESPERSON.canManageUsers());
    }

    @Test
    void testCanManageUsersCustomerFalse() {
        assertFalse(Role.CUSTOMER.canManageUsers());
    }

    @Test
    void testCanManageUsersConsistencyAdministrator() {
        assertEquals(Role.ADMINISTRATOR.canManageUsers(), Role.ADMINISTRATOR.canManageUsers());
    }

    @Test
    void testCanManageUsersConsistencySalesperson() {
        assertEquals(Role.SALESPERSON.canManageUsers(), Role.SALESPERSON.canManageUsers());
    }

    @Test
    void testCanManageUsersConsistencyCustomer() {
        assertEquals(Role.CUSTOMER.canManageUsers(), Role.CUSTOMER.canManageUsers());
    }

    @Test
    void testCanManageUsersOnlyAdministrator() {
        for (Role role : Role.values()) {
            if (role == Role.ADMINISTRATOR) {
                assertTrue(role.canManageUsers());
            } else {
                assertFalse(role.canManageUsers());
            }
        }
    }

    @Test
    void testCanManageUsersSalespersonAndCustomerEqual() {
        assertEquals(Role.SALESPERSON.canManageUsers(), Role.CUSTOMER.canManageUsers());
    }

    @Test
    void testCanManageUsersMultipleInvocations() {
        for (int i = 0; i < 5; i++) {
            assertTrue(Role.ADMINISTRATOR.canManageUsers());
        }
    }

    @Test
    void testCanManageUsersPermissionCount() {
        int count = 0;
        for (Role role : Role.values()) {
            if (role.canManageUsers()) {
                count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    void testValuesLength() {
        assertEquals(3, Role.values().length);
    }

    @Test
    void testValuesContainsAdministrator() {
        Role[] roles = Role.values();
        assertEquals(Role.ADMINISTRATOR, roles[0]);
    }

    @Test
    void testValuesContainsSalesperson() {
        Role[] roles = Role.values();
        assertEquals(Role.SALESPERSON, roles[1]);
    }

    @Test
    void testValuesContainsCustomer() {
        Role[] roles = Role.values();
        assertEquals(Role.CUSTOMER, roles[2]);
    }

    @Test
    void testValuesArrayNotNull() {
        assertNotNull(Role.values());
    }

    @Test
    void testValuesReturnsNewArrayInstance() {
        Role[] array1 = Role.values();
        Role[] array2 = Role.values();
        assertNotSame(array1, array2);
    }

    @Test
    void testValuesOrderIsPreserved() {
        Role[] roles = Role.values();
        assertSame(Role.ADMINISTRATOR, roles[0]);
        assertSame(Role.SALESPERSON, roles[1]);
        assertSame(Role.CUSTOMER, roles[2]);
    }

    @Test
    void testValuesElementsAreNotNull() {
        for (Role role : Role.values()) {
            assertNotNull(role);
        }
    }

    @Test
    void testValuesCanBeIterated() {
        int count = 0;
        for (Role role : Role.values()) {
            assertNotNull(role.getLabel());
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void testValuesMatchesEnumCount() {
        assertEquals(Role.class.getEnumConstants().length, Role.values().length);
    }

    @Test
    void testValueOfAdministrator() {
        assertEquals(Role.ADMINISTRATOR, Role.valueOf("ADMINISTRATOR"));
    }

    @Test
    void testValueOfSalesperson() {
        assertEquals(Role.SALESPERSON, Role.valueOf("SALESPERSON"));
    }

    @Test
    void testValueOfCustomer() {
        assertEquals(Role.CUSTOMER, Role.valueOf("CUSTOMER"));
    }

    @Test
    void testValueOfInvalidNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf("INVALID"));
    }

    @Test
    void testValueOfNullThrowsException() {
        assertThrows(NullPointerException.class, () -> Role.valueOf(null));
    }

    @Test
    void testValueOfLowerCaseThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf("administrator"));
    }

    @Test
    void testValueOfWithSpacesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf(" ADMINISTRATOR "));
    }

    @Test
    void testValueOfReturnsSameInstance() {
        assertSame(Role.ADMINISTRATOR, Role.valueOf("ADMINISTRATOR"));
    }

    @Test
    void testValueOfLabelNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf("Administrador"));
    }

    @Test
    void testValueOfConsistency() {
        Role role1 = Role.valueOf("SALESPERSON");
        Role role2 = Role.valueOf("SALESPERSON");
        assertEquals(role1, role2);
    }
}