package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private static final String FILE_PATH = "users.json";
    private static final String TEST_DIR = "testUserServiceOutput";
    private UserService userService;

    @BeforeEach
    void setUp() {
        deleteJsonFile();
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        userService = new UserService();
    }

    @AfterEach
    void tearDown() {
        deleteJsonFile();
        File dir = new File(TEST_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    private void deleteJsonFile() {
        File jsonFile = new File(FILE_PATH);
        if (jsonFile.exists()) {
            jsonFile.delete();
        }
    }

    @Test
    void testConstructorCreatesDefaultAdminWhenFileMissing() {
        assertEquals(1, userService.getUsers().size());
        User admin = userService.findByUsername("admin");
        assertNotNull(admin);
        assertEquals(Role.ADMINISTRATOR, admin.getRole());
    }

    @Test
    void testConstructorSavesDefaultAdminFileWhenMissing() {
        File file = new File(FILE_PATH);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testConstructorLoadsExistingUsersFromFile() {
        userService.registerUser("vendedor1", "pass123", Role.SALESPERSON);
        UserService newService = new UserService();
        assertEquals(2, newService.getUsers().size());
        assertNotNull(newService.findByUsername("vendedor1"));
    }

    @Test
    void testConstructorDoesNotDuplicateAdminIfFileExists() {
        UserService service2 = new UserService();
        assertEquals(1, service2.getUsers().size());
    }

    @Test
    void testConstructorHandlesEmptyJsonList() {
        FileHandler.saveListToJson(FILE_PATH, new SimpleList<User>());
        UserService service = new UserService();
        assertEquals(1, service.getUsers().size());
        assertNotNull(service.findByUsername("admin"));
    }

    @Test
    void testConstructorNotNullInstance() {
        assertNotNull(userService);
    }

    @Test
    void testConstructorNotNullUsersList() {
        assertNotNull(userService.getUsers());
    }

    @Test
    void testConstructorMultipleSavedUsersLoadedCorrectly() {
        userService.registerUser("user1", "pass1", Role.SALESPERSON);
        userService.registerUser("user2", "pass2", Role.CUSTOMER);
        UserService loadedService = new UserService();
        assertEquals(3, loadedService.getUsers().size());
    }

    @Test
    void testConstructorDefaultAdminCredentialsMatch() {
        assertNotNull(userService.authenticate("admin", "admin123"));
    }

    @Test
    void testConstructorDefaultAdminIsFirstInList() {
        userService.getUsers().resetIteration();
        assertEquals("admin", userService.getUsers().next().getUsername());
    }

    @Test
    void testRegisterUserValidParameters() {
        userService.registerUser("leslye", "leslye123", Role.ADMINISTRATOR);
        assertNotNull(userService.findByUsername("leslye"));
    }

    @Test
    void testRegisterUserExistingUsernameThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser("admin", "admin123", Role.ADMINISTRATOR)
        );
        assertTrue(ex.getMessage().contains("El usuario ya existe"));
    }

    @Test
    void testRegisterUserCaseInsensitiveDuplicateThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser("ADMIN", "pass", Role.SALESPERSON)
        );
    }

    @Test
    void testRegisterUserPersistsToFile() {
        userService.registerUser("dennis", "dennis123", Role.SALESPERSON);
        UserService newService = new UserService();
        assertNotNull(newService.findByUsername("dennis"));
    }

    @Test
    void testRegisterUserIncrementsListSize() {
        assertEquals(1, userService.getUsers().size());
        userService.registerUser("francisco", "fran123", Role.CUSTOMER);
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    void testRegisterUserSalespersonRole() {
        userService.registerUser("seller", "pass", Role.SALESPERSON);
        User user = userService.findByUsername("seller");
        assertEquals(Role.SALESPERSON, user.getRole());
    }

    @Test
    void testRegisterUserCustomerRole() {
        userService.registerUser("client", "pass", Role.CUSTOMER);
        User user = userService.findByUsername("client");
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    void testRegisterUserNullUsernameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser(null, "pass", Role.CUSTOMER)
        );
    }

    @Test
    void testRegisterUserBlankUsernameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser("   ", "pass", Role.CUSTOMER)
        );
    }

    @Test
    void testRegisterUserNullPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            userService.registerUser("userTest", null, Role.CUSTOMER)
        );
    }

    @Test
    void testRemoveValidUserReturnsTrue() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        boolean removed = userService.remove("vendedor");
        assertTrue(removed);
        assertNull(userService.findByUsername("vendedor"));
    }

    @Test
    void testRemoveNonExistentUserReturnsFalse() {
        boolean removed = userService.remove("usuarioInexistente");
        assertFalse(removed);
    }

    @Test
    void testRemoveCaseInsensitive() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        boolean removed = userService.remove("VENDEDOR");
        assertTrue(removed);
        assertNull(userService.findByUsername("vendedor"));
    }

    @Test
    void testRemoveDefaultAdmin() {
        boolean removed = userService.remove("admin");
        assertTrue(removed);
        assertTrue(userService.getUsers().isEmpty());
    }

    @Test
    void testRemovePersistsToFile() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        userService.remove("vendedor");
        UserService newService = new UserService();
        assertNull(newService.findByUsername("vendedor"));
    }

    @Test
    void testRemoveDecreasesListSize() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        assertEquals(2, userService.getUsers().size());
        userService.remove("vendedor");
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void testRemoveNullReturnsFalse() {
        assertFalse(userService.remove(null));
    }

    @Test
    void testRemoveBlankReturnsFalse() {
        assertFalse(userService.remove("   "));
    }

    @Test
    void testRemoveUserTwiceReturnsFalse() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        userService.remove("vendedor");
        assertFalse(userService.remove("vendedor"));
    }

    @Test
    void testRemoveDoesNotAffectOtherUsers() {
        userService.registerUser("vendedor1", "pass", Role.SALESPERSON);
        userService.registerUser("vendedor2", "pass", Role.SALESPERSON);
        userService.remove("vendedor1");
        assertNotNull(userService.findByUsername("vendedor2"));
    }

    @Test
    void testAuthenticateValidCredentialsReturnsUser() {
        User user = userService.authenticate("admin", "admin123");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void testAuthenticateWrongPasswordReturnsNull() {
        assertNull(userService.authenticate("admin", "wrongPass"));
    }

    @Test
    void testAuthenticateWrongUsernameReturnsNull() {
        assertNull(userService.authenticate("wrongUser", "admin123"));
    }

    @Test
    void testAuthenticateNullUsernameReturnsNull() {
        assertNull(userService.authenticate(null, "admin123"));
    }

    @Test
    void testAuthenticateNullPasswordReturnsNull() {
        assertNull(userService.authenticate("admin", null));
    }

    @Test
    void testAuthenticateRegisteredUser() {
        userService.registerUser("dennis", "pass2026", Role.SALESPERSON);
        User user = userService.authenticate("dennis", "pass2026");
        assertNotNull(user);
        assertEquals(Role.SALESPERSON, user.getRole());
    }

    @Test
    void testAuthenticateCaseSensitiveUsername() {
        assertNull(userService.authenticate("ADMIN", "admin123"));
    }

    @Test
    void testAuthenticateCaseSensitivePassword() {
        assertNull(userService.authenticate("admin", "ADMIN123"));
    }

    @Test
    void testAuthenticateAfterUserRemovedReturnsNull() {
        userService.registerUser("dennis", "pass2026", Role.SALESPERSON);
        userService.remove("dennis");
        assertNull(userService.authenticate("dennis", "pass2026"));
    }

    @Test
    void testAuthenticateEmptyCredentialsReturnsNull() {
        assertNull(userService.authenticate("", ""));
    }

    @Test
    void testFindByUsernameExactMatch() {
        User user = userService.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void testFindByUsernameCaseInsensitiveLower() {
        userService.registerUser("Carlos", "pass", Role.CUSTOMER);
        User user = userService.findByUsername("carlos");
        assertNotNull(user);
        assertEquals("Carlos", user.getUsername());
    }

    @Test
    void testFindByUsernameCaseInsensitiveUpper() {
        User user = userService.findByUsername("ADMIN");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void testFindByUsernameNonExistentReturnsNull() {
        assertNull(userService.findByUsername("desconocido"));
    }

    @Test
    void testFindByUsernameNullReturnsNull() {
        assertNull(userService.findByUsername(null));
    }

    @Test
    void testFindByUsernameEmptyReturnsNull() {
        assertNull(userService.findByUsername(""));
    }

    @Test
    void testFindByUsernameBlankReturnsNull() {
        assertNull(userService.findByUsername("   "));
    }

    @Test
    void testFindByUsernameConsistentCall() {
        assertEquals(userService.findByUsername("admin"), userService.findByUsername("admin"));
    }

    @Test
    void testFindByUsernameMultipleUsersSearch() {
        userService.registerUser("user1", "pass", Role.SALESPERSON);
        userService.registerUser("user2", "pass", Role.CUSTOMER);
        assertNotNull(userService.findByUsername("user2"));
    }

    @Test
    void testFindByUsernameAfterStockChange() {
        assertNotNull(userService.findByUsername("admin"));
    }

    @Test
    void testGetUsersNotNull() {
        assertNotNull(userService.getUsers());
    }

    @Test
    void testGetUsersInitialSizeIsOne() {
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void testGetUsersReflectsAdditions() {
        userService.registerUser("vendedor", "pass", Role.SALESPERSON);
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    void testGetUsersReflectsRemovals() {
        userService.remove("admin");
        assertTrue(userService.getUsers().isEmpty());
    }

    @Test
    void testGetUsersSameReference() {
        assertSame(userService.getUsers(), userService.getUsers());
    }

    @Test
    void testGetUsersAllowsIteration() {
        SimpleList<User> users = userService.getUsers();
        users.resetIteration();
        assertTrue(users.hasNext());
        assertEquals("admin", users.next().getUsername());
    }

    @Test
    void testGetUsersConsistentCall() {
        assertEquals(userService.getUsers(), userService.getUsers());
    }

    @Test
    void testGetUsersOrderPreserved() {
        userService.registerUser("user1", "pass", Role.SALESPERSON);
        userService.registerUser("user2", "pass", Role.CUSTOMER);
        SimpleList<User> users = userService.getUsers();
        users.resetIteration();
        assertEquals("admin", users.next().getUsername());
        assertEquals("user1", users.next().getUsername());
        assertEquals("user2", users.next().getUsername());
    }

    @Test
    void testGetUsersReturnsSimpleListInstance() {
        assertTrue(userService.getUsers() instanceof SimpleList);
    }

    @Test
    void testGetUsersContainsUserInstances() {
        userService.getUsers().resetIteration();
        assertTrue(userService.getUsers().next() instanceof User);
    }

    @Test
    void testExportReportCsvValidFileGeneration() throws IOException {
        String path = TEST_DIR + "/usuarios.csv";
        userService.exportReportCSV(path);

        File file = new File(path);
        assertTrue(file.exists());
        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(2, lines.size());
        assertEquals("Usuario,Rol", lines.get(0));
    }

    @Test
    void testExportReportCsvAutoAppendsExtension() {
        String path = TEST_DIR + "/usuariosReport";
        userService.exportReportCSV(path);

        assertTrue(new File(path + ".csv").exists());
    }

    @Test
    void testExportReportCsvContainsAdminData() throws IOException {
        String path = TEST_DIR + "/adminReport.csv";
        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertTrue(lines.get(1).contains("admin") && lines.get(1).contains("ADMINISTRATOR"));
    }

    @Test
    void testExportReportCsvMultipleUsers() throws IOException {
        String path = TEST_DIR + "/multiUsuarios.csv";
        userService.registerUser("vendedor1", "pass", Role.SALESPERSON);
        userService.registerUser("cliente1", "pass", Role.CUSTOMER);

        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(4, lines.size());
    }

    @Test
    void testExportReportCsvNullPathDoesNotCrash() {
        assertDoesNotThrow(() -> userService.exportReportCSV(null));
    }

    @Test
    void testExportReportCsvBlankPathDoesNotCrash() {
        assertDoesNotThrow(() -> userService.exportReportCSV("   "));
    }

    @Test
    void testExportReportCsvOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwriteUsuarios.csv";
        userService.exportReportCSV(path);

        userService.registerUser("vendedor1", "pass", Role.SALESPERSON);
        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
    }

    @Test
    void testExportReportCsvContainsHeaderLine() throws IOException {
        String path = TEST_DIR + "/headerUserCheck.csv";
        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertFalse(lines.isEmpty());
        assertEquals("Usuario,Rol", lines.get(0));
    }

    @Test
    void testExportReportCsvEmptyUsersList() throws IOException {
        String path = TEST_DIR + "/emptyUsuarios.csv";
        userService.remove("admin");
        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(1, lines.size());
        assertEquals("Usuario,Rol", lines.get(0));
    }

    @Test
    void testExportReportCsvFormatExactMatch() throws IOException {
        String path = TEST_DIR + "/exactFormat.csv";
        userService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals("admin,ADMINISTRATOR", lines.get(1));
    }
}