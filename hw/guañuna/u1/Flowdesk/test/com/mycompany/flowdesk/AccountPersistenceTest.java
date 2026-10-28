package com.mycompany.flowdesk;

import data.DataManager;
import enums.Role;
import exceptions.PersistenceException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

class AccountPersistenceTest {
    @TempDir
    Path directory;
    private String previousDirectory;
    private ArrayList<User> previousUsers;

    @BeforeEach
    void configureTemporaryStorage() {
        previousDirectory = System.getProperty("flowdesk.dataDirectory");
        previousUsers = new ArrayList<>(User.getAll());
        System.setProperty("flowdesk.dataDirectory", directory.toString());
        User.setAll(new ArrayList<>());
    }

    @AfterEach
    void restoreStorage() {
        User.setAll(previousUsers);
        if (previousDirectory == null) {
            System.clearProperty("flowdesk.dataDirectory");
        } else {
            System.setProperty("flowdesk.dataDirectory", previousDirectory);
        }
    }

    @Test
    void accountIsSavedBeforeFirstLoginReturns() throws Exception {
        User user = User.authenticateFromConsole(Role.EMPLOYEE,
                new Scanner("niño\ncontraseña\n"), DataManager::saveUsers);
        assertTrue(Files.exists(directory.resolve("usuarios.json")));
        User.setAll(new ArrayList<>());
        DataManager.loadUsers();
        assertEquals(user.getUserId(), User.getAll().get(0).getUserId());
        assertTrue(User.getAll().get(0).validateCredentials("niño", "contraseña"));
    }

    @Test
    void allRolesAreRecoveredAndAuthenticateWithoutRegistration() {
        User.authenticateFromConsole(Role.MANAGER, new Scanner("manager\nsecret1\n"), DataManager::saveUsers);
        User.authenticateFromConsole(Role.CASHIER, new Scanner("cashier\nsecret2\n"), DataManager::saveUsers);
        User.authenticateFromConsole(Role.EMPLOYEE, new Scanner("employee\nsecret3\n"), DataManager::saveUsers);
        User.setAll(new ArrayList<>());
        DataManager.loadUsers();
        assertNotNull(User.authenticateFromConsole(Role.MANAGER, new Scanner("manager\nsecret1\n")));
        assertNotNull(User.authenticateFromConsole(Role.CASHIER, new Scanner("cashier\nsecret2\n")));
        assertNotNull(User.authenticateFromConsole(Role.EMPLOYEE, new Scanner("employee\nsecret3\n")));
        assertEquals(3, User.getAll().size());
    }

    @Test
    void failedSaveRollsBackAccountCreation() throws Exception {
        Path blockedDirectory = directory.resolve("blocked");
        Files.writeString(blockedDirectory, "not a directory", StandardCharsets.UTF_8);
        System.setProperty("flowdesk.dataDirectory", blockedDirectory.toString());
        assertThrows(PersistenceException.class, () -> User.authenticateFromConsole(Role.MANAGER,
                new Scanner("manager\nsecret\n"), DataManager::saveUsers));
        assertTrue(User.getAll().isEmpty());
        System.setProperty("flowdesk.dataDirectory", directory.toString());
        assertNotNull(User.authenticateFromConsole(Role.MANAGER,
                new Scanner("manager\nsecret\n"), DataManager::saveUsers));
    }

    @Test
    void malformedAccountFileIsPreservedAndExistingMemoryIsRestored() throws Exception {
        new User("U1", "manager", "secret", Role.MANAGER).register();
        Path file = directory.resolve("usuarios.json");
        Files.writeString(file, "{broken", StandardCharsets.UTF_8);
        assertThrows(PersistenceException.class, DataManager::loadUsers);
        assertEquals("{broken", Files.readString(file, StandardCharsets.UTF_8));
        assertEquals(1, User.getAll().size());
        assertTrue(User.getAll().get(0).validateCredentials("manager", "secret"));
    }

    @Test
    void invalidAccountDataIsRejectedWithoutRecreatingAccounts() throws Exception {
        Path file = directory.resolve("usuarios.json");
        Files.writeString(file, "[{}]", StandardCharsets.UTF_8);
        assertThrows(PersistenceException.class, DataManager::loadUsers);
        assertEquals("[{}]", Files.readString(file, StandardCharsets.UTF_8));
        assertTrue(User.getAll().isEmpty());
    }

    @Test
    void missingFileAllowsFirstRun() {
        DataManager.loadUsers();
        assertTrue(User.getAll().isEmpty());
    }

    @Test
    void explicitDataDirectoryDoesNotDependOnWorkingDirectory() {
        String workingDirectory = System.getProperty("user.dir");
        try {
            System.setProperty("user.dir", directory.resolve("other-launch-location").toString());
            assertEquals(directory.toAbsolutePath().normalize(), DataManager.getDataDirectory());
        } finally {
            System.setProperty("user.dir", workingDirectory);
        }
    }
}
