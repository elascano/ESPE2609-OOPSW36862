package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.SimpleList;

public class UserService {

    private final SimpleList<User> users = new SimpleList<>();

    public UserService() {
        users.add(new User("admin", "admin123", Role.ADMINISTRATOR));
    }

    public void register(String username, String password, Role role) {
        if (findByUsername(username) != null) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }
        users.add(new User(username, password, role));
    }

    public boolean remove(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }
        users.remove(user);
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
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public SimpleList<User> getUsers() {
        return users;
    }
}
