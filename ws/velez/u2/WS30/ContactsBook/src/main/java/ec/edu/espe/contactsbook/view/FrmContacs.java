package ec.edu.espe.contactsbook.view;

import ec.edu.espe.contactsbook.controller.ContactController;
import ec.edu.espe.contactsbook.model.Contact;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class FrmContacs extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmContacs.class.getName());

    public FrmContacs() {
        initComponents();
        loadBirthDateCombos();
        setLocationRelativeTo(null);
    }

    private void loadBirthDateCombos() {
        cmbDay.removeAllItems();
        for (int d = 1; d <= 31; d++) {
            cmbDay.addItem(String.valueOf(d));
        }

        cmbMonth.removeAllItems();
        for (int m = 1; m <= 12; m++) {
            cmbMonth.addItem(String.valueOf(m));
        }

        cmbYear.removeAllItems();
        int currentYear = LocalDate.now().getYear();
        for (int y = currentYear; y >= 1940; y--) {
            cmbYear.addItem(String.valueOf(y));
        }
        cmbYear.setSelectedItem("2005");
    }

    private void clearFields() {
        txtId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtCellphoneNumber.setText("");
        txtInstagram.setText("");
        txtFacebook.setText("");
        chkIsKnown.setSelected(false);
        cmbTypeOfContact.setSelectedIndex(0);
        cmbGender.setSelectedIndex(0);
        cmbDay.setSelectedIndex(0);
        cmbMonth.setSelectedIndex(0);
        cmbYear.setSelectedItem("2005");
        lstHobbies.clearSelection();
        txaComments.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCellphoneNumber = new javax.swing.JTextField();
        txtInstagram = new javax.swing.JTextField();
        txtFacebook = new javax.swing.JTextField();
        chkIsKnown = new javax.swing.JCheckBox();
        cmbTypeOfContact = new javax.swing.JComboBox<>();
        cmbGender = new javax.swing.JComboBox<>();
        cmbDay = new javax.swing.JComboBox<>();
        cmbMonth = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHobbies = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaComments = new javax.swing.JTextArea();
        pnlButtons = new javax.swing.JPanel();
        btnMainMenu = new javax.swing.JButton();
        BTNCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuContactsBook = new javax.swing.JMenu();
        itmMainMenu = new javax.swing.JMenuItem();
        itmAbout = new javax.swing.JMenuItem();
        itmQuit = new javax.swing.JMenuItem();
        mnuContacts = new javax.swing.JMenu();
        itmViewContacts = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Contact");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADD CONTACT");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setText("id:");

        jLabel3.setText("Full Name:");

        jLabel4.setText("Email:");

        jLabel5.setText("Cell Phone Number:");

        jLabel6.setText("Instagram:");

        jLabel7.setText("Facebook:");

        jLabel8.setText("Known:");

        jLabel9.setText("Type:");

        jLabel11.setText("Gender:");

        jLabel12.setText("Hobbies:");

        jLabel13.setText("Birth Date (D/M/Y):");

        jLabel14.setText("Comments:");

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        txtFullName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFullNameKeyTyped(evt);
            }
        });

        txtCellphoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCellphoneNumberKeyTyped(evt);
            }
        });

        chkIsKnown.setText("Yes");

        cmbTypeOfContact.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Friend", "Family", "Job", "University" }));

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        cmbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2005" }));

        lstHobbies.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Play the guitar", "Watch movies", "Play soccer", "Play videogames", "Reading" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstHobbies);

        txaComments.setColumns(20);
        txaComments.setRows(4);
        jScrollPane2.setViewportView(txaComments);

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtCellphoneNumber)
                    .addComponent(txtInstagram)
                    .addComponent(txtFacebook)
                    .addComponent(chkIsKnown))
                .addGap(40, 40, 40)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTypeOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmbTypeOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(chkIsKnown)
                        .addComponent(jLabel14))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnMainMenu.setText("Back to Menu");
        btnMainMenu.addActionListener(this::btnMainMenuActionPerformed);

        BTNCancel.setText("Cancel / Clear");
        BTNCancel.addActionListener(this::BTNCancelActionPerformed);

        btnSave.setText("Save");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnMainMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNCancel)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMainMenu)
                    .addComponent(BTNCancel)
                    .addComponent(btnSave))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        mnuContactsBook.setText("ContactsBook");

        itmMainMenu.setText("Main Menu");
        itmMainMenu.addActionListener(this::itmMainMenuActionPerformed);
        mnuContactsBook.add(itmMainMenu);

        itmAbout.setText("About");
        itmAbout.addActionListener(this::itmAboutActionPerformed);
        mnuContactsBook.add(itmAbout);

        itmQuit.setText("Quit");
        itmQuit.addActionListener(this::itmQuitActionPerformed);
        mnuContactsBook.add(itmQuit);

        jMenuBar1.add(mnuContactsBook);

        mnuContacts.setText("Contacts");

        itmViewContacts.setText("View Contacts");
        itmViewContacts.addActionListener(this::itmViewContactsActionPerformed);
        mnuContacts.add(itmViewContacts);

        jMenuBar1.add(mnuContacts);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtFullNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFullNameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtFullNameKeyTyped

    private void txtCellphoneNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCellphoneNumberKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtCellphoneNumber.getText().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCellphoneNumberKeyTyped

    private void BTNCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelActionPerformed
        clearFields();
    }//GEN-LAST:event_BTNCancelActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        new FrmContactsMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void itmMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMainMenuActionPerformed
        new FrmContactsMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmMainMenuActionPerformed

    private void itmAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAboutActionPerformed
        new FrmAbout().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmAboutActionPerformed

    private void itmQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itmQuitActionPerformed

    private void itmViewContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmViewContactsActionPerformed
        new FrmViewContacts().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmViewContactsActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String idText = txtId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String cellPhoneNumber = txtCellphoneNumber.getText().trim();
        String instagram = txtInstagram.getText().trim();
        String Facebook = txtFacebook.getText().trim();

        if (idText.isEmpty() || !idText.matches("^\\d+$")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid numeric ID.");
            return;
        }
        int id = Integer.parseInt(idText);
        if (id <= 0) {
            JOptionPane.showMessageDialog(rootPane, "ID must be greater than 0.");
            return;
        }

        if (fullName.isEmpty() || !fullName.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid Full Name (letters only).");
            return;
        }

        if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid Email (example: user@mail.com).");
            return;
        }

        if (!cellPhoneNumber.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(rootPane, "Cell Phone Number must have 10 digits.");
            return;
        }

        int day = Integer.parseInt(cmbDay.getSelectedItem().toString());
        int month = Integer.parseInt(cmbMonth.getSelectedItem().toString());
        int year = Integer.parseInt(cmbYear.getSelectedItem().toString());

        LocalDate selectedLocalDate;
        try {
            selectedLocalDate = LocalDate.of(year, month, day);
        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(rootPane, "Invalid Birth Date selected (check day and month).");
            return;
        }

        if (selectedLocalDate.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(rootPane, "Birth Date cannot be in the future.");
            return;
        }

        boolean isKnown = chkIsKnown.isSelected();
        String type = (String) cmbTypeOfContact.getSelectedItem();
        char gender = cmbGender.getSelectedItem().toString().charAt(0);
        ArrayList<String> hobbies = new ArrayList<>(lstHobbies.getSelectedValuesList());
        Date birthDate = Date.from(selectedLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String comments = txaComments.getText();

        Contact contact = new Contact(id, fullName, email, cellPhoneNumber, instagram, Facebook, isKnown, type, gender, hobbies, birthDate, comments);
        ContactController.addContact(contact);

        JOptionPane.showMessageDialog(rootPane, "Contact saved!\n\n" + contact);
        clearFields();
    }//GEN-LAST:event_btnSaveActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmContacs().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCancel;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkIsKnown;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbTypeOfContact;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmMainMenu;
    private javax.swing.JMenuItem itmQuit;
    private javax.swing.JMenuItem itmViewContacts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstHobbies;
    private javax.swing.JMenu mnuContacts;
    private javax.swing.JMenu mnuContactsBook;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextArea txaComments;
    private javax.swing.JTextField txtCellphoneNumber;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFacebook;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtInstagram;
    // End of variables declaration//GEN-END:variables
}
