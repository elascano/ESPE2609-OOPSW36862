package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;

public class UserService {

    private static final String FILE_PATH = "users.json";
    private final SimpleList<User> users;

    public UserService() {
        SimpleList<User> loadedUsers = FileHandler.loadListFromJson(FILE_PATH, User.class);
        if (loadedUsers == null || loadedUsers.isEmpty()) {
            this.users = new SimpleList<>();
            this.users.add(new User("admin", "admin123", Role.ADMINISTRATOR));
            saveUsers();
        } else {
            this.users = loadedUsers;
        }
    }

    public void registerUser(String username, String password, Role role) {
        if (findByUsername(username) != null) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }
        users.add(new User(username, password, role));
        saveUsers();
    }

    public boolean remove(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }
        users.remove(user);
        saveUsers();
        return true;
    }

    public User authenticate(String username, String password) {
        users.resetIteration();
        while (users.hasNext()) {
            User user = users.next();
            if (user.matchesCredentials(username, password)) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        users.resetIteration();
        while (users.hasNext()) {
            User user = users.next();
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public SimpleList<User> getUsers() {
        return users;
    }

    private void saveUsers() {
        FileHandler.saveListToJson(FILE_PATH, users);
    }

    /**
     * Exporta el listado de usuarios a un archivo en formato CSV.
     */
    public void exportReportCSV(String filePath) {
        String header = "Usuario,Rol";
        SimpleList<String> rows = new SimpleList<>();

        users.resetIteration();
        while (users.hasNext()) {
            User u = users.next();
            String row = String.format("%s,%s", u.getUsername(), u.getRole());
            rows.add(row);
        }

        FileHandler.exportToCSV(filePath, header, rows);
    }
}