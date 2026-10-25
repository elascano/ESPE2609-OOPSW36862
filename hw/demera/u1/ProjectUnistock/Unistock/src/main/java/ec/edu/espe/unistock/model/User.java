package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

public class User {

    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, Role role) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Usuario y contraseña son obligatorios.");
        }
        this.username = username.trim();
        this.password = password;
        this.role = role;
    }

    public boolean matchesCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof User user && user.username.equals(username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return username + " (" + role.getLabel() + ")";
    }
}
