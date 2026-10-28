package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Logs in and logs out, checks permissions and manages the accounts (UC-01 to UC-04). (RF01)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class AccessManager {

    //default account so the system can be used the first time
    public static final String DEFAULT_OWNER_USERNAME = "owner";
    public static final String DEFAULT_OWNER_PASSWORD = "Owner2026";

    private List<User> users;
    private List<Role> roles;
    private User currentUser;

    public AccessManager() {
        users = new ArrayList<>();
        roles = new ArrayList<>();
        currentUser = null;

        //FR-01.1: the three roles are fixed
        List<String> salespersonPermissions = Arrays.asList(Role.VIEW_PRODUCTS, Role.MANAGE_CUSTOMERS,
                Role.REGISTER_SALE);

        List<String> warehouseClerkPermissions = Arrays.asList(Role.VIEW_PRODUCTS, Role.MANAGE_PRODUCTS,
                Role.VIEW_INVENTORY, Role.ADJUST_STOCK, Role.REGISTER_PURCHASE, Role.RECEIVE_ALERTS);

        //the owner has the permissions of both roles and its own permissions
        List<String> ownerPermissions = Arrays.asList(Role.MANAGE_USERS, Role.MANAGE_CUSTOMERS,
                Role.DEACTIVATE_CUSTOMERS, Role.VIEW_PRODUCTS, Role.MANAGE_PRODUCTS, Role.REGISTER_SALE,
                Role.VOID_SALE, Role.UPDATE_VAT_RATE, Role.ADJUST_STOCK, Role.VIEW_INVENTORY,
                Role.MANAGE_SUPPLIERS, Role.REGISTER_PURCHASE, Role.VIEW_SALES_HISTORY, Role.RECEIVE_ALERTS,
                Role.GENERATE_REPORTS);

        roles.add(new Role(1, Role.SALESPERSON, salespersonPermissions));
        roles.add(new Role(2, Role.WAREHOUSE_CLERK, warehouseClerkPermissions));
        roles.add(new Role(3, Role.OWNER, ownerPermissions));

        users.add(new User(1, DEFAULT_OWNER_USERNAME, "Propietario", findRole(Role.OWNER),
                DEFAULT_OWNER_PASSWORD));
    }

    public boolean login(String username, String password) {
        if (!isRegistered(username)) {
            return false;
        }
        User user = findUser(username);
        if (!user.isActive() || !user.verifyPassword(password)) {
            return false;
        }
        currentUser = user;
        return true;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean checkPermission(String permission) {
        return currentUser != null && currentUser.hasPermission(permission);
    }

    public User createUser(String username, String fullName, Role role, String initialPassword) {
        requirePermission(Role.MANAGE_USERS);
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }
        if (isRegistered(username)) {
            throw new IllegalArgumentException("Ya existe el usuario " + username);
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (role == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        //the constructor checks that the password has at least 8 characters (NFR-02)
        User user = new User(users.size() + 1, username.trim(), fullName, role, initialPassword);
        users.add(user);
        return user;
    }

    public void updateUser(User user) {
        requirePermission(Role.MANAGE_USERS);
        if (!users.contains(user)) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        if (user.getFullName() == null || user.getFullName().isBlank() || user.getRole() == null) {
            throw new IllegalArgumentException("El nombre completo y el rol son obligatorios");
        }
    }

    public void deactivateUser(User user) {
        requirePermission(Role.MANAGE_USERS);
        if (user == currentUser) {
            throw new IllegalArgumentException("No puede desactivar su propia cuenta");
        }
        user.deactivate();
    }

    public void resetPassword(User user, String newPassword) {
        requirePermission(Role.MANAGE_USERS);
        user.resetPassword(newPassword);
    }

    public boolean changeOwnPassword(String current, String newPass, String confirm) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.changePassword(current, newPass, confirm);
    }

    /**
     * @throws NoSuchElementException if no account has that username
     */
    public User findUser(String username) {
        for (User user : users) {
            if (hasUsername(user, username)) {
                return user;
            }
        }
        throw new NoSuchElementException("No existe el usuario " + username + ".");
    }

    public boolean isRegistered(String username) {
        for (User user : users) {
            if (hasUsername(user, username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUsername(User user, String username) {
        return username != null && user.getUsername().equalsIgnoreCase(username.trim());
    }

    /**
     * Replaces the users with the ones read from the data files. If the file had no users,
     * the default owner account is kept so the system can still be used.
     * If somebody is logged in (the data is read again to create a report), the session
     * is kept with the same user read from the file.
     */
    public void restoreUsers(List<User> savedUsers) {
        if (savedUsers == null || savedUsers.isEmpty()) {
            return;
        }
        boolean loggedIn = currentUser != null;
        String currentUsername = loggedIn ? currentUser.getUsername() : "";
        users = new ArrayList<>(savedUsers);
        if (loggedIn && isRegistered(currentUsername)) {
            currentUser = findUser(currentUsername);
        }
    }

    /**
     * Replaces the roles with the ones read from the data files. If the file had no roles,
     * the three fixed roles created in the constructor are kept.
     */
    public void restoreRoles(List<Role> savedRoles) {
        if (savedRoles == null || savedRoles.isEmpty()) {
            return;
        }
        roles = new ArrayList<>(savedRoles);
    }

    /**
     * @throws NoSuchElementException if there is no role with that name
     */
    public Role findRole(String name) {
        for (Role role : roles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new NoSuchElementException("No existe el rol " + name + ".");
    }

    /**
     * Sensitive operations check the permission again (NFR-02, UI-02). The other control
     * classes also use this method, so the rule is written only once.
     *
     * @throws SecurityException if the logged-in user does not have the permission
     */
    public void requirePermission(String permission) {
        if (!checkPermission(permission)) {
            throw new SecurityException("No tiene permiso para esta operación (" + permission + ")");
        }
    }

    /**
     * @return a read-only list of the users
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * @return a read-only list of the roles
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }
}
