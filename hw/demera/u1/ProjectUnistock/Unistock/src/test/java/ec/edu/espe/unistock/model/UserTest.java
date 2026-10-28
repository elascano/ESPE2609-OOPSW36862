package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("admin", "admin123", Role.ADMINISTRATOR);
    }

    @Test
    void testConstructorValidParameters() {
        User user = new User("vendedor1", "pass2026", Role.SALESPERSON);
        assertNotNull(user);
    }

    @Test
    void testConstructorNullUsernameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "pass123", Role.CUSTOMER));
    }

    @Test
    void testConstructorBlankUsernameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("   ", "pass123", Role.CUSTOMER));
    }

    @Test
    void testConstructorNullPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("user1", null, Role.CUSTOMER));
    }

    @Test
    void testConstructorBlankPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("user1", "   ", Role.CUSTOMER));
    }

    @Test
    void testConstructorTrimsUsername() {
        User user = new User("  carlos  ", "pass123", Role.SALESPERSON);
        assertEquals("carlos", user.getUsername());
    }

    @Test
    void testConstructorAssignsRoleCorrectly() {
        User user = new User("cliente1", "pass123", Role.CUSTOMER);
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    void testConstructorNullRoleAllowed() {
        User user = new User("userNullRole", "pass123", null);
        assertNull(user.getRole());
    }

    @Test
    void testConstructorSingleCharacterCredentials() {
        User user = new User("u", "p", Role.CUSTOMER);
        assertEquals("u", user.getUsername());
    }

    @Test
    void testConstructorSpecialCharactersInCredentials() {
        User user = new User("user_123", "p@ss#2026", Role.SALESPERSON);
        assertEquals("user_123", user.getUsername());
    }

    @Test
    void testMatchesCredentialsExactMatchReturnsTrue() {
        assertTrue(testUser.matchesCredentials("admin", "admin123"));
    }

    @Test
    void testMatchesCredentialsWrongUsernameReturnsFalse() {
        assertFalse(testUser.matchesCredentials("wrongUser", "admin123"));
    }

    @Test
    void testMatchesCredentialsWrongPasswordReturnsFalse() {
        assertFalse(testUser.matchesCredentials("admin", "wrongPass"));
    }

    @Test
    void testMatchesCredentialsBothWrongReturnsFalse() {
        assertFalse(testUser.matchesCredentials("invalid", "invalid"));
    }

    @Test
    void testMatchesCredentialsCaseSensitiveUsername() {
        assertFalse(testUser.matchesCredentials("ADMIN", "admin123"));
    }

    @Test
    void testMatchesCredentialsCaseSensitivePassword() {
        assertFalse(testUser.matchesCredentials("admin", "ADMIN123"));
    }

    @Test
    void testMatchesCredentialsNullUsernameReturnsFalse() {
        assertFalse(testUser.matchesCredentials(null, "admin123"));
    }

    @Test
    void testMatchesCredentialsNullPasswordReturnsFalse() {
        assertFalse(testUser.matchesCredentials("admin", null));
    }

    @Test
    void testMatchesCredentialsEmptyStringsReturnsFalse() {
        assertFalse(testUser.matchesCredentials("", ""));
    }

    @Test
    void testMatchesCredentialsWithUntrimmedInputReturnsFalse() {
        assertFalse(testUser.matchesCredentials(" admin ", "admin123"));
    }

    @Test
    void testGetUsernameReturnsCorrectName() {
        assertEquals("admin", testUser.getUsername());
    }

    @Test
    void testGetUsernameTrimmedOnCreation() {
        User user = new User("  leslye  ", "pass", Role.ADMINISTRATOR);
        assertEquals("leslye", user.getUsername());
    }

    @Test
    void testGetUsernameNotNull() {
        assertNotNull(testUser.getUsername());
    }

    @Test
    void testGetUsernameNotEmpty() {
        assertFalse(testUser.getUsername().isEmpty());
    }

    @Test
    void testGetUsernameImmutable() {
        String username = testUser.getUsername();
        assertEquals("admin", username);
    }

    @Test
    void testGetUsernameConsistentCall() {
        assertEquals(testUser.getUsername(), testUser.getUsername());
    }

    @Test
    void testGetUsernameSalesperson() {
        User user = new User("dennis", "pass", Role.SALESPERSON);
        assertEquals("dennis", user.getUsername());
    }

    @Test
    void testGetUsernameCustomer() {
        User user = new User("francisco", "pass", Role.CUSTOMER);
        assertEquals("francisco", user.getUsername());
    }

    @Test
    void testGetUsernameWithNumbers() {
        User user = new User("user2026", "pass", Role.CUSTOMER);
        assertEquals("user2026", user.getUsername());
    }

    @Test
    void testGetUsernameWithSymbols() {
        User user = new User("dev_espe", "pass", Role.ADMINISTRATOR);
        assertEquals("dev_espe", user.getUsername());
    }

    @Test
    void testGetRoleAdministrator() {
        assertEquals(Role.ADMINISTRATOR, testUser.getRole());
    }

    @Test
    void testGetRoleSalesperson() {
        User user = new User("vendedor", "pass", Role.SALESPERSON);
        assertEquals(Role.SALESPERSON, user.getRole());
    }

    @Test
    void testGetRoleCustomer() {
        User user = new User("cliente", "pass", Role.CUSTOMER);
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    void testGetRoleNullIfAssignedNull() {
        User user = new User("user", "pass", null);
        assertNull(user.getRole());
    }

    @Test
    void testGetRoleConsistentCall() {
        assertEquals(testUser.getRole(), testUser.getRole());
    }

    @Test
    void testGetRoleSameEnumReference() {
        assertSame(Role.ADMINISTRATOR, testUser.getRole());
    }

    @Test
    void testGetRoleComparison() {
        User user = new User("user", "pass", Role.CUSTOMER);
        assertNotEquals(testUser.getRole(), user.getRole());
    }

    @Test
    void testGetRoleLabelAccess() {
        assertEquals("Administrador", testUser.getRole().getLabel());
    }

    @Test
    void testGetRolePermissionsCheck() {
        assertTrue(testUser.getRole().canManageUsers());
    }

    @Test
    void testGetRoleNotNull() {
        assertNotNull(testUser.getRole());
    }

    @Test
    void testEqualsSameObjectReturnsTrue() {
        assertTrue(testUser.equals(testUser));
    }

    @Test
    void testEqualsSameUsernameReturnsTrue() {
        User other = new User("admin", "differentPassword", Role.CUSTOMER);
        assertTrue(testUser.equals(other));
    }

    @Test
    void testEqualsDifferentUsernameReturnsFalse() {
        User other = new User("user2", "admin123", Role.ADMINISTRATOR);
        assertFalse(testUser.equals(other));
    }

    @Test
    void testEqualsNullReturnsFalse() {
        assertFalse(testUser.equals(null));
    }

    @Test
    void testEqualsDifferentClassReturnsFalse() {
        assertFalse(testUser.equals("admin"));
    }

    @Test
    void testEqualsSymmetric() {
        User other = new User("admin", "pass", Role.SALESPERSON);
        assertTrue(testUser.equals(other) && other.equals(testUser));
    }

    @Test
    void testEqualsTransitive() {
        User u1 = new User("admin", "p1", Role.ADMINISTRATOR);
        User u2 = new User("admin", "p2", Role.SALESPERSON);
        User u3 = new User("admin", "p3", Role.CUSTOMER);
        assertTrue(u1.equals(u2) && u2.equals(u3) && u1.equals(u3));
    }

    @Test
    void testEqualsConsistent() {
        User other = new User("admin", "pass", Role.ADMINISTRATOR);
        assertTrue(testUser.equals(other));
        assertTrue(testUser.equals(other));
    }

    @Test
    void testEqualsCaseSensitiveUsername() {
        User other = new User("ADMIN", "admin123", Role.ADMINISTRATOR);
        assertFalse(testUser.equals(other));
    }

    @Test
    void testEqualsDifferentRoleSameUsernameReturnsTrue() {
        User other = new User("admin", "admin123", Role.CUSTOMER);
        assertTrue(testUser.equals(other));
    }

    @Test
    void testHashCodeSameForEqualObjects() {
        User other = new User("admin", "diffPass", Role.CUSTOMER);
        assertEquals(testUser.hashCode(), other.hashCode());
    }

    @Test
    void testHashCodeConsistent() {
        int initialHashCode = testUser.hashCode();
        assertEquals(initialHashCode, testUser.hashCode());
    }

    @Test
    void testHashCodeDifferentForDifferentUsernames() {
        User other = new User("user2", "admin123", Role.ADMINISTRATOR);
        assertNotEquals(testUser.hashCode(), other.hashCode());
    }

    @Test
    void testHashCodeEqualsStringHashCode() {
        assertEquals("admin".hashCode(), testUser.hashCode());
    }

    @Test
    void testHashCodeMultipleInvocations() {
        for (int i = 0; i < 5; i++) {
            assertEquals("admin".hashCode(), testUser.hashCode());
        }
    }

    @Test
    void testHashCodeSameUsernameDifferentRoles() {
        User u1 = new User("carlos", "p1", Role.ADMINISTRATOR);
        User u2 = new User("carlos", "p2", Role.SALESPERSON);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testHashCodeSameUsernameDifferentPasswords() {
        User u1 = new User("dennis", "pass1", Role.CUSTOMER);
        User u2 = new User("dennis", "pass2", Role.CUSTOMER);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testHashCodeWithNumbersInUsername() {
        User user = new User("user123", "pass", Role.CUSTOMER);
        assertEquals("user123".hashCode(), user.hashCode());
    }

    @Test
    void testHashCodeWithSymbolsInUsername() {
        User user = new User("user_name", "pass", Role.CUSTOMER);
        assertEquals("user_name".hashCode(), user.hashCode());
    }

    @Test
    void testHashCodeNotNullOrZeroForStandardInput() {
        assertNotEquals(0, testUser.hashCode());
    }

    @Test
    void testToStringContainsUsername() {
        assertTrue(testUser.toString().contains("admin"));
    }

    @Test
    void testToStringContainsRoleLabel() {
        assertTrue(testUser.toString().contains("Administrador"));
    }

    @Test
    void testToStringFormatExactMatch() {
        assertEquals("admin (Administrador)", testUser.toString());
    }

    @Test
    void testToStringSalespersonFormat() {
        User user = new User("vendedor1", "pass", Role.SALESPERSON);
        assertEquals("vendedor1 (Vendedor)", user.toString());
    }

    @Test
    void testToStringCustomerFormat() {
        User user = new User("cliente1", "pass", Role.CUSTOMER);
        assertEquals("cliente1 (Cliente)", user.toString());
    }

    @Test
    void testToStringNotNull() {
        assertNotNull(testUser.toString());
    }

    @Test
    void testToStringNotEmpty() {
        assertFalse(testUser.toString().isEmpty());
    }

    @Test
    void testToStringConsistency() {
        String str1 = testUser.toString();
        String str2 = testUser.toString();
        assertEquals(str1, str2);
    }

    @Test
    void testToStringDoesNotContainPassword() {
        assertFalse(testUser.toString().contains("admin123"));
    }

    @Test
    void testToStringContainsParentheses() {
        assertTrue(testUser.toString().contains("(") && testUser.toString().contains(")"));
    }
}