package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.User;

/**
 * UC-03 Change password and UC-04 Manage user accounts. (RF01)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class UserMenu {

    private static final String NEW_PASSWORD_PROMPT = "Nueva contraseña (mínimo "
            + User.MINIMUM_PASSWORD_LENGTH + " caracteres): ";

    private final InputReader input;
    private final AccessManager accessManager;
    private final ConsoleHelper console;

    public UserMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.accessManager = context.getAccessManager();
        this.console = console;
    }

    public void changePassword() {
        String current = input.readText("Contraseña actual: ");
        String newPassword = input.readText(NEW_PASSWORD_PROMPT);
        String confirm = input.readText("Confirme la nueva contraseña: ");
        if (accessManager.changeOwnPassword(current, newPassword, confirm)) {
            System.out.println("Contraseña cambiada correctamente.");
        } else {
            System.out.println("No se cambió la contraseña: la actual es incorrecta, la nueva tiene menos "
                    + "de " + User.MINIMUM_PASSWORD_LENGTH + " caracteres o la confirmación no coincide.");
        }
    }

    public void manageUsers() {
        while (true) {
            int option = console.showMenu("Gestionar usuarios", "Volver", "Crear usuario", "Actualizar usuario",
                    "Desactivar usuario", "Restablecer contraseña", "Listar usuarios");
            switch (option) {
                case 1 -> console.runSafely(this::createUser);
                case 2 -> console.runSafely(this::updateUser);
                case 3 -> console.runSafely(this::deactivateUser);
                case 4 -> console.runSafely(this::resetUserPassword);
                case 5 -> listUsers();
                case 0 -> {
                    return;
                }
                default -> console.showInvalidOption();
            }
        }
    }

    private void createUser() {
        String username = input.readText("Nombre de usuario: ");
        String fullName = input.readText("Nombre completo: ");
        Role role = readRole();
        String password = input.readText("Contraseña inicial (mínimo " + User.MINIMUM_PASSWORD_LENGTH
                + " caracteres): ");
        User user = accessManager.createUser(username, fullName, role, password);
        System.out.println("Usuario creado: " + user.getUsername() + " (id " + user.getId() + ")");
    }

    private void updateUser() {
        User user = findUser();
        user.setFullName(input.readTextOrKeep("Nombre completo", user.getFullName()));
        if (input.readYesNo("¿Cambiar el rol (actual: " + user.getRole().getName() + ")?")) {
            user.setRole(readRole());
        }
        accessManager.updateUser(user);
        System.out.println("Usuario actualizado.");
    }

    private void deactivateUser() {
        User user = findUser();
        if (input.readYesNo("¿Desactivar a " + user.getUsername() + "?")) {
            accessManager.deactivateUser(user);
            System.out.println("Usuario desactivado.");
        }
    }

    private void resetUserPassword() {
        User user = findUser();
        accessManager.resetPassword(user, input.readText(NEW_PASSWORD_PROMPT));
        System.out.println("Contraseña restablecida.");
    }

    private void listUsers() {
        System.out.printf("%-4s %-14s %-28s %-16s %s%n", "Id", "Usuario", "Nombre", "Rol", "Estado");
        for (User user : accessManager.getUsers()) {
            System.out.printf("%-4d %-14s %-28s %-16s %s%n", user.getId(), user.getUsername(), user.getFullName(),
                    user.getRole().getName(), user.isActive() ? "Activo" : "Inactivo");
        }
    }

    private User findUser() {
        return accessManager.findUser(input.readText("Nombre de usuario: "));
    }

    private Role readRole() {
        String[] names = new String[accessManager.getRoles().size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = accessManager.getRoles().get(i).getName();
        }
        return accessManager.findRole(input.readOption("Rol:", names));
    }
}
