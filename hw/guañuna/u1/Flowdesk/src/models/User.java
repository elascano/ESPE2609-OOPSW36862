package models;

import enums.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;
import exceptions.PersistenceException;

public class User {

    private String userId;
    private String username;
    private String protectedPassword;
    private Role role;

    private static List<User> users = new ArrayList<>();

    public User() { }

    public User(String userId, String username, String protectedPassword, Role role) {
        this.userId = userId;
        this.username = username;
        this.protectedPassword = protectedPassword;
        this.role = role;
        validate();
    }

    private void validate() {
        Validation.requireText(userId, "identificador del usuario");
        Validation.requireText(username, "nombre de usuario");
        Validation.requireText(protectedPassword, "contrasena");
        Validation.requireValue(role, "rol");
    }

    public void register() {
        validate();
        if (users.contains(this)) {
            throw new ValidationException("El usuario ya fue registrado.");
        }
        requireUniqueIdentity(userId, username, role);
        users.add(this);
    }

    private void requireUniqueIdentity(String candidateId, String candidateName, Role candidateRole) {
        for (User existing : users) {
            if (existing == this) {
                continue;
            }
            if (Validation.sameIdentifier(existing.userId, candidateId)
                    || Validation.sameIdentifier(existing.username, candidateName)) {
                throw new ValidationException("Ya existe un usuario con ese ID o nombre.");
            }
            if (existing.role == candidateRole) {
                throw new ValidationException("Ya existe una cuenta para ese rol.");
            }
        }
    }

    public static List<User> getAll() {
        return users;
    }

    public static void setAll(List<User> newItems) {
        users = newItems;
    }

    public static User authenticateFromConsole(Role selectedRole, Scanner scanner) {
        return authenticateFromConsole(selectedRole, scanner, () -> { });
    }

    public static User authenticateFromConsole(Role selectedRole, Scanner scanner, Runnable saveAccounts) {
        Validation.requireValue(selectedRole, "rol");
        User existingUser = findByRole(selectedRole);

        if (existingUser == null) {
            System.out.println("\n--- PRIMER INGRESO: CONFIGURAR CUENTA PARA " + selectedRole + " ---");
            String newUsername = ConsoleInput.readRequiredText(scanner, "Cree un nuevo Usuario: ");

            String newPassword = ConsoleInput.readRequiredText(scanner, "Cree una Contrasena: ");

            User newUser = new User(
                    "U_" + selectedRole.name(),
                    newUsername,
                    newPassword,
                    selectedRole
            );

            newUser.register();
            try {
                saveAccounts.run();
            } catch (PersistenceException exception) {
                users.remove(newUser);
                throw exception;
            }

            System.out.println("Cuenta creada con exito!");
            return newUser;

        } else {
            System.out.println("\n--- INICIO DE SESION: " + selectedRole + " ---");
            String userInput = ConsoleInput.readRequiredText(scanner, "Usuario: ");

            String passInput = ConsoleInput.readRequiredText(scanner, "Contrasena: ");

            if (existingUser.validateCredentials(userInput, passInput)) {
                return existingUser;
            } else {
                System.out.println("Acceso denegado. Credenciales incorrectas.\n");
                return null;
            }
        }
    }

    private static User findByRole(Role role) {
        for (User u : users) {
            if (u.getRole() == role) {
                return u;
            }
        }
        return null;
    }

    public boolean validateCredentials(String user, String pass) {
        return this.username.equals(user) && this.protectedPassword.equals(pass);
    }

    public void login() {
        System.out.println("Bienvenido/a, " + this.username + " (" + this.role + ")");
    }

    public void logout() {
        System.out.println("Sesion cerrada correctamente para " + this.username);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        Validation.requireText(userId, "identificador del usuario");
        requireUniqueIdentity(userId, username, role);
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Validation.requireText(username, "nombre de usuario");
        requireUniqueIdentity(userId, username, role);
        this.username = username;
    }

    public String getProtectedPassword() {
        return protectedPassword;
    }

    public void setProtectedPassword(String protectedPassword) {
        Validation.requireText(protectedPassword, "contrasena");
        this.protectedPassword = protectedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        Validation.requireValue(role, "rol");
        requireUniqueIdentity(userId, username, role);
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "identificador=" + userId
                + ", usuario=" + username
                + ", contrasena=[OCULTA]"
                + ", rol=" + role
                + '}';
    }
}
