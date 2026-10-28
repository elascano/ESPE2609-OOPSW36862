package ec.edu.espe.provstock.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * A person with an account in the system (Table B6). (RF01)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class User {

    public static final int MINIMUM_PASSWORD_LENGTH = 8;

    private int id;
    private String username;
    private String fullName;
    private String passwordHash;
    private String salt;
    private Role role;
    private boolean active;

    public User(int id, String username, String fullName, Role role, String initialPassword) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.active = true;
        resetPassword(initialPassword);
    }

    /**
     * Restores a user read from the data files. The password is NOT hashed again:
     * the saved hash and salt are kept as they are. Only FileManager uses it.
     */
    public User(int id, String username, String fullName, Role role, String passwordHash, String salt,
            boolean active) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.active = active;
    }

    public boolean verifyPassword(String plainPassword) {
        if (plainPassword == null) {
            return false;
        }
        return passwordHash.equals(hashPassword(plainPassword, salt));
    }

    public boolean changePassword(String current, String newPass, String confirm) {
        if (!verifyPassword(current)) {
            return false;
        }
        if (newPass == null || newPass.length() < MINIMUM_PASSWORD_LENGTH) {
            return false;
        }
        if (!newPass.equals(confirm)) {
            return false;
        }
        resetPassword(newPass);
        return true;
    }

    public void resetPassword(String newPassword) {
        //NFR-02: the password must have at least 8 characters
        if (newPassword == null || newPassword.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("La contraseña debe tener al menos "
                    + MINIMUM_PASSWORD_LENGTH + " caracteres");
        }
        this.salt = generateSalt();
        this.passwordHash = hashPassword(newPassword, salt);
    }

    public boolean hasPermission(String permission) {
        return active && role != null && role.hasPermission(permission);
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    private String hashPassword(String plain, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((salt + plain).getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 no está disponible", e);
        }
    }

    private static String generateSalt() {
        byte[] bytes = new byte[16];
        new SecureRandom().nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", fullName=" + fullName
                + ", role=" + (role == null ? null : role.getName()) + ", active=" + active + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the passwordHash (needed to save the user in a file)
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @return the salt (needed to save the user in a file)
     */
    public String getSalt() {
        return salt;
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
