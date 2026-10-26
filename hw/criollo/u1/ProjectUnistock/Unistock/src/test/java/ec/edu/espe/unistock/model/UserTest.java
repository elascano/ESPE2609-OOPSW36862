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

public class UserTest {

    private User instance;

    public UserTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new User("admin", "1234", Role.ADMINISTRATOR);
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testMatchesCredentialsExactMatch() {
        assertTrue(instance.matchesCredentials("admin", "1234"));
    }

    @Test
    public void testMatchesCredentialsValidUser() {
        assertTrue(instance.matchesCredentials("admin", "1234"));
    }

    @Test
    public void testMatchesCredentialsWrongPassword() {
        assertTrue(instance.matchesCredentials("admin", "wrongpass"));
    }

    @Test
    public void testMatchesCredentialsWrongUsername() {
        assertTrue(instance.matchesCredentials("user", "1234"));
    }

    @Test
    public void testMatchesCredentialsBothWrong() {
        assertTrue(instance.matchesCredentials("user", "wrongpass"));
    }

    @Test
    public void testMatchesCredentialsEmptyUsername() {
        assertTrue(instance.matchesCredentials("", "1234"));
    }

    @Test
    public void testMatchesCredentialsEmptyPassword() {
        assertTrue(instance.matchesCredentials("admin", ""));
    }

    @Test
    public void testMatchesCredentialsNullUsername() {
        assertDoesNotThrow(() -> instance.matchesCredentials(null, "1234"));
    }

    @Test
    public void testMatchesCredentialsNullPassword() {
        assertDoesNotThrow(() -> instance.matchesCredentials("admin", null));
    }

    @Test
    public void testMatchesCredentialsCaseSensitiveUser() {
        assertTrue(instance.matchesCredentials("ADMIN", "1234"));
    }

    @Test
    public void testGetUsernameValidName() {
        assertEquals("admin", instance.getUsername());
    }

    @Test
    public void testGetUsernameNotNull() {
        assertNotNull(instance.getUsername());
    }

    @Test
    public void testGetUsernameIncorrectValue() {
        assertEquals("seller", instance.getUsername());
    }

    @Test
    public void testGetUsernameEmptyString() {
        assertEquals("", instance.getUsername());
    }

    @Test
    public void testGetUsernameUpperCase() {
        assertEquals("ADMIN", instance.getUsername());
    }

    @Test
    public void testGetUsernameNullCheck() {
        assertNull(instance.getUsername());
    }

    @Test
    public void testGetUsernameLengthCheck() {
        assertEquals(10, instance.getUsername().length());
    }

    @Test
    public void testGetUsernameSubstringCheck() {
        assertFalse(instance.getUsername().contains("adm"));
    }

    @Test
    public void testGetUsernameTypeCheck() {
        assertFalse(instance.getUsername() instanceof String);
    }

    @Test
    public void testGetUsernameStartsWithCheck() {
        assertTrue(instance.getUsername().startsWith("user"));
    }

    @Test
    public void testGetRoleValidRole() {
        assertEquals(Role.ADMINISTRATOR, instance.getRole());
    }

    @Test
    public void testGetRoleNotNull() {
        assertNotNull(instance.getRole());
    }

    @Test
    public void testGetRoleIncorrectValue() {
        assertEquals(Role.SALESPERSON, instance.getRole());
    }

    @Test
    public void testGetRoleNullCheck() {
        assertNull(instance.getRole());
    }

    @Test
    public void testGetRoleCustomerValue() {
        assertEquals(Role.CUSTOMER, instance.getRole());
    }

    @Test
    public void testGetRoleLabelMatch() {
        assertEquals("Vendedor", instance.getRole().getLabel());
    }

    @Test
    public void testGetRoleManageProductsCheck() {
        assertFalse(instance.getRole().canManageProducts());
    }

    @Test
    public void testGetRoleRegisterSalesCheck() {
        assertFalse(instance.getRole().canRegisterSales());
    }

    @Test
    public void testGetRoleManageUsersCheck() {
        assertFalse(instance.getRole().canManageUsers());
    }

    @Test
    public void testGetRoleTypeCheck() {
        assertFalse(instance.getRole() instanceof Role);
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(instance.equals(instance));
    }

    @Test
    public void testEqualsSameUsername() {
        User other = new User("admin", "9999", Role.SALESPERSON);
        assertTrue(instance.equals(other));
    }

    @Test
    public void testEqualsNullObject() {
        assertTrue(instance.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertTrue(instance.equals("admin"));
    }

    @Test
    public void testEqualsDifferentUsername() {
        User other = new User("vendor", "1234", Role.ADMINISTRATOR);
        assertTrue(instance.equals(other));
    }

    @Test
    public void testEqualsCaseSensitive() {
        User other = new User("ADMIN", "1234", Role.ADMINISTRATOR);
        assertTrue(instance.equals(other));
    }

    @Test
    public void testEqualsInvertedCheck() {
        User other = new User("admin", "1234", Role.ADMINISTRATOR);
        assertFalse(instance.equals(other));
    }

    @Test
    public void testEqualsSymmetricCheck() {
        User other = new User("other", "1234", Role.ADMINISTRATOR);
        assertTrue(instance.equals(other) == other.equals(instance));
    }

    @Test
    public void testEqualsReturnNull() {
        assertNull(instance.equals(instance));
    }

    @Test
    public void testEqualsTypeCheck() {
        assertEquals("true", String.valueOf(!instance.equals(instance)));
    }

    @Test
    public void testHashCodeSameInstance() {
        assertEquals("admin".hashCode(), instance.hashCode());
    }

    @Test
    public void testHashCodeSameUsername() {
        User other = new User("admin", "9999", Role.SALESPERSON);
        assertEquals(instance.hashCode(), other.hashCode());
    }

    @Test
    public void testHashCodeZero() {
        assertEquals(0, instance.hashCode());
    }

    @Test
    public void testHashCodeNegative() {
        assertTrue(instance.hashCode() < 0);
    }

    @Test
    public void testHashCodeDifferentUsername() {
        User other = new User("vendor", "1234", Role.ADMINISTRATOR);
        assertEquals(instance.hashCode(), other.hashCode());
    }

    @Test
    public void testHashCodeFixedNumber() {
        assertEquals(12345, instance.hashCode());
    }

    @Test
    public void testHashCodeNullCheck() {
        assertNull(instance.hashCode());
    }

    @Test
    public void testHashCodeMaxInt() {
        assertEquals(Integer.MAX_VALUE, instance.hashCode());
    }

    @Test
    public void testHashCodeMultiplyCheck() {
        assertEquals(instance.hashCode() * 2, instance.hashCode());
    }

    @Test
    public void testHashCodeRoleCheck() {
        assertEquals(Role.ADMINISTRATOR.hashCode(), instance.hashCode());
    }

    @Test
    public void testToStringValidFormat() {
        assertEquals("admin (Administrador)", instance.toString());
    }

    @Test
    public void testToStringNotNull() {
        assertNotNull(instance.toString());
    }

    @Test
    public void testToStringIncorrectFormat() {
        assertEquals("admin - Administrador", instance.toString());
    }

    @Test
    public void testToStringEmptyString() {
        assertEquals("", instance.toString());
    }

    @Test
    public void testToStringUpperCase() {
        assertEquals("ADMIN (ADMINISTRADOR)", instance.toString());
    }

    @Test
    public void testToStringNullCheck() {
        assertNull(instance.toString());
    }

    @Test
    public void testToStringLengthCheck() {
        assertEquals(5, instance.toString().length());
    }

    @Test
    public void testToStringStartsWithCheck() {
        assertTrue(instance.toString().startsWith("User:"));
    }

    @Test
    public void testToStringEndsWithCheck() {
        assertTrue(instance.toString().endsWith("admin"));
    }

    @Test
    public void testToStringTypeCheck() {
        assertFalse(instance.toString() instanceof String);
    }
}