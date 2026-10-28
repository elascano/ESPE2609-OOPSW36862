/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

/**
 *
 * @author CodeLex, @ESPE
 */
public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private boolean active;
    private Role role;

    public User(int userId, String name, String username, String password, Role role) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    public boolean login(String username, String password) {
        if (!active) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("User " + username + " logged out");
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (!password.equals(oldPass)) {
            return false;
        }
        if (newPass.length() < 6 || newPass.equals(oldPass)) {
            return false;
        }
        password = newPass;
        return true;
    }

    public boolean hasPermission(String permission) {
        if (!active) {
            return false;
        }
        if (permission.equals("REGISTER_CASE") || permission.equals("UPDATE_CASE")
                || permission.equals("SEARCH_CASE") || permission.equals("VIEW_ALERTS")) {
            return true;
        }
        if (role == Role.PARTNER) {
            return true;
        }
        if (role == Role.LAWYER) {
            return permission.equals("MANAGE_DEADLINES") || permission.equals("REGISTER_EVIDENCE")
                    || permission.equals("GENERATE_DOCUMENT");
        }
        if (role == Role.LEGAL_ASSISTANT) {
            return permission.equals("IMPORT_CSV");
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name=" + name + ", username=" + username
                + ", role=" + role + ", active=" + active + '}';
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
