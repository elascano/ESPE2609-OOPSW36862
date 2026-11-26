package ec.edu.espe.contactsbook.view;

import ec.edu.espe.contactsbook.controller.ContactController;
import ec.edu.espe.contactsbook.model.Contact;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FrmContacts extends JFrame {

    private final ContactController controller;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Columna Izquierda
    private JTextField txtId;
    private JTextField txtFullName;
    private JTextField txtEmail;
    private JTextField txtCellPhoneNumber;
    private JTextField txtInstagram;
    private JTextField txtFacebook;
    private JCheckBox chkKnown;

    // Columna Derecha
    private JComboBox<String> cmbType;
    private JComboBox<String> cmbGender;
    private JTextField txtBirthDate;
    private JList<String> lstHobbies;
    private JTextArea txaComments;

    public FrmContacts() {
        controller = new ContactController();
        initComponents();
    }

    private void initComponents() {
        setTitle("FrmContacts");
        setSize(760, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título superior: CONTACT
        JLabel lblTitle = new JLabel("CONTACT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel central con 2 columnas exactas a la imagen
        JPanel pnlForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Controles Columna Izquierda ---
        txtId = new JTextField(6);
        txtId.setText("1");

        txtFullName = new JTextField(16);
        txtEmail = new JTextField(16);
        txtCellPhoneNumber = new JTextField(16);
        txtInstagram = new JTextField(16);
        txtFacebook = new JTextField(16);
        chkKnown = new JCheckBox("Yes", true);

        // --- Controles Columna Derecha ---
        cmbType = new JComboBox<>(new String[]{"Friend", "Family", "HighSchool", "Job", "Team mate", "Gym"});
        cmbGender = new JComboBox<>(new String[]{"Male", "Female"});
        txtBirthDate = new JTextField(14);

        lstHobbies = new JList<>(new String[]{
            "Play the guitar",
            "Watch movies",
            "Play soccer",
            "Play Basketball",
            "Reading",
            "Cooking",
            "Running",
            "Swimming"
        });
        lstHobbies.setVisibleRowCount(8);
        JScrollPane scrHobbies = new JScrollPane(lstHobbies);

        txaComments = new JTextArea(3, 22);
        txaComments.setLineWrap(true);
        txaComments.setWrapStyleWord(true);
        JScrollPane scrComments = new JScrollPane(
                txaComments,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        // COLUMNA IZQUIERDA (X = 0 y 1)
        addLabelRight(pnlForm, gbc, "Id", 0, 0);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        pnlForm.add(txtId, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelRight(pnlForm, gbc, "Full Name", 0, 1);
        gbc.gridx = 1; gbc.gridy = 1;
        pnlForm.add(txtFullName, gbc);

        addLabelRight(pnlForm, gbc, "Email", 0, 2);
        gbc.gridx = 1; gbc.gridy = 2;
        pnlForm.add(txtEmail, gbc);

        addLabelRight(pnlForm, gbc, "Cell Phone Number:", 0, 3);
        gbc.gridx = 1; gbc.gridy = 3;
        pnlForm.add(txtCellPhoneNumber, gbc);

        addLabelRight(pnlForm, gbc, "Instagram", 0, 4);
        gbc.gridx = 1; gbc.gridy = 4;
        pnlForm.add(txtInstagram, gbc);

        addLabelRight(pnlForm, gbc, "Facebook", 0, 5);
        gbc.gridx = 1; gbc.gridy = 5;
        pnlForm.add(txtFacebook, gbc);

        addLabelRight(pnlForm, gbc, "Known", 0, 6);
        gbc.gridx = 1; gbc.gridy = 6;
        pnlForm.add(chkKnown, gbc);

        // COLUMNA DERECHA (X = 2 y 3)
        addLabelRight(pnlForm, gbc, "Type:", 2, 0);
        gbc.gridx = 3; gbc.gridy = 0;
        pnlForm.add(cmbType, gbc);

        addLabelRight(pnlForm, gbc, "Gender:", 2, 2);
        gbc.gridx = 3; gbc.gridy = 2;
        pnlForm.add(cmbGender, gbc);

        addLabelRight(pnlForm, gbc, "Birth Date", 2, 3);
        gbc.gridx = 3; gbc.gridy = 3;
        pnlForm.add(txtBirthDate, gbc);

        addLabelRight(pnlForm, gbc, "Hobbies:", 2, 4);
        gbc.gridx = 3; gbc.gridy = 4; gbc.gridheight = 3;
        pnlForm.add(scrHobbies, gbc);
        gbc.gridheight = 1;

        addLabelRight(pnlForm, gbc, "Comments", 2, 7);
        gbc.gridx = 3; gbc.gridy = 7;
        pnlForm.add(scrComments, gbc);

        add(pnlForm, BorderLayout.CENTER);

        // Botón inferior
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        JButton btnSave = new JButton("Save");
        JButton btnNew = new JButton("New");
        pnlBottom.add(btnSave);
        pnlBottom.add(btnNew);
        add(pnlBottom, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveContact());
        btnNew.addActionListener(e -> clearForm());
    }

    private void addLabelRight(JPanel panel, GridBagConstraints gbc, String text, int x, int y) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
    }

    private void saveContact() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String fullName = txtFullName.getText().trim();
            String email = txtEmail.getText().trim();
            String cellPhoneNumber = txtCellPhoneNumber.getText().trim();
            String instagram = txtInstagram.getText().trim();
            String facebook = txtFacebook.getText().trim();
            boolean isKnown = chkKnown.isSelected();
            String type = (String) cmbType.getSelectedItem();
            char gender = cmbGender.getSelectedItem().toString().charAt(0);

            List<String> selectedHobbies = lstHobbies.getSelectedValuesList();
            String[] hobbies = selectedHobbies.toArray(new String[0]);

            Date birthDate = txtBirthDate.getText().trim().isEmpty()
                    ? new Date()
                    : dateFormat.parse(txtBirthDate.getText().trim());

            String comments = txaComments.getText().trim();

            Contact contact = new Contact(id, fullName, email, cellPhoneNumber, instagram, facebook, isKnown, type, gender, hobbies, birthDate, comments);
            controller.addContact(contact);
            JOptionPane.showMessageDialog(this, "Contact saved:\n" + contact.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Id must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Birth Date must follow dd/MM/yyyy format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtCellPhoneNumber.setText("");
        txtInstagram.setText("");
        txtFacebook.setText("");
        chkKnown.setSelected(false);
        cmbType.setSelectedIndex(0);
        cmbGender.setSelectedIndex(0);
        txtBirthDate.setText("");
        lstHobbies.clearSelection();
        txaComments.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmContacts().setVisible(true));
    }
}
