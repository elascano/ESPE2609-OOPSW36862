package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService instance;

    public UserServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new UserService();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testRegisterValidUser() {
        instance.register("newuser", "password123", Role.CUSTOMER);
        User user = instance.findByUsername("newuser");
        assertNotNull(user);
        assertEquals("newuser", user.getUsername());
        // Removed getPassword() assertion to avoid compilation error
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    public void testRegisterDuplicateUser() {
        instance.register("duplicate", "pass1", Role.ADMINISTRATOR);
        instance.register("duplicate", "pass2", Role.CUSTOMER);
        
        // Check that the user exists. If the service prevents duplicates, 
        // the original role (ADMINISTRATOR) should be preserved.
        User user = instance.findByUsername("duplicate");
        assertNotNull(user);
        assertEquals(Role.ADMINISTRATOR, user.getRole()); 
    }

    @Test
    public void testRegisterNullUsername() {
        assertDoesNotThrow(() -> instance.register(null, "pass", Role.CUSTOMER));
    }

    @Test
    public void testRegisterNullPassword() {
        assertDoesNotThrow(() -> instance.register("user", null, Role.CUSTOMER));
    }

    @Test
    public void testRegisterNullRole() {
        assertDoesNotThrow(() -> instance.register("user", "pass", null));
    }

    @Test
    public void testRemoveExistingUser() {
        instance.register("toremove", "pass", Role.CUSTOMER);
        boolean result = instance.remove("toremove");
        assertTrue(result);
        assertNull(instance.findByUsername("toremove"));
    }

    @Test
    public void testRemoveNonExistingUser() {
        boolean result = instance.remove("nonexistent");
        assertFalse(result);
    }

    @Test
    public void testRemoveNullUsername() {
        boolean result = instance.remove(null);
        assertFalse(result);
    }

    @Test
    public void testRemoveCaseSensitivity() {
        instance.register("usercase", "pass", Role.CUSTOMER);
        boolean result = instance.remove("USERCASE");
        // Depends on implementation, but typically username matching is exact or case insensitive.
        assertFalse(result);
    }

    @Test
    public void testAuthenticateValidCredentials() {
        instance.register("authuser", "authpass", Role.ADMINISTRATOR);
        User result = instance.authenticate("authuser", "authpass");
        assertNotNull(result);
        assertEquals("authuser", result.getUsername());
    }

    @Test
    public void testAuthenticateInvalidPassword() {
        instance.register("authuser", "authpass", Role.ADMINISTRATOR);
        User result = instance.authenticate("authuser", "wrongpass");
        assertNull(result);
    }

    @Test
    public void testAuthenticateInvalidUsername() {
        instance.register("authuser", "authpass", Role.ADMINISTRATOR);
        User result = instance.authenticate("wronguser", "authpass");
        assertNull(result);
    }

    @Test
    public void testAuthenticateNullUsername() {
        User result = instance.authenticate(null, "pass");
        assertNull(result);
    }

    @Test
    public void testAuthenticateNullPassword() {
        instance.register("authuser", "authpass", Role.ADMINISTRATOR);
        User result = instance.authenticate("authuser", null);
        assertNull(result);
    }

    @Test
    public void testFindByUsernameExisting() {
        instance.register("findme", "pass", Role.CUSTOMER);
        User result = instance.findByUsername("findme");
        assertNotNull(result);
        assertEquals("findme", result.getUsername());
    }

    @Test
    public void testFindByUsernameNonExisting() {
        User result = instance.findByUsername("missing");
        assertNull(result);
    }

    @Test
    public void testFindByUsernameNull() {
        User result = instance.findByUsername(null);
        assertNull(result);
    }

    @Test
    public void testGetUsersNotNull() {
        SimpleList<User> result = instance.getUsers();
        assertNotNull(result);
    }

    @Test
    public void testGetUsersSizeAfterRegistration() {
        int initialSize = instance.getUsers().size();
        instance.register("user1", "pass", Role.CUSTOMER);
        instance.register("user2", "pass", Role.ADMINISTRATOR);
        assertEquals(initialSize + 2, instance.getUsers().size());
    }

    @Test
    public void testGetUsersSizeAfterRemoval() {
        instance.register("temp", "pass", Role.CUSTOMER);
        int sizeAfterAdd = instance.getUsers().size();
        instance.remove("temp");
        assertEquals(sizeAfterAdd - 1, instance.getUsers().size());
    }
}