package ec.edu.espe.contactsbook.view;

import ec.edu.espe.contactsbook.model.Contact;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class FrmContacts extends javax.swing.JFrame {

    private ArrayList<String> errorLog = new ArrayList<>();
    private JMenuItem menuItemErrors;

    public FrmContacts() {
        initComponents();
        txtBirthDate.setToolTipText("Formato: dd/MM/yyyy");
        construirMenu();
    }

    private void construirMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("Archivo");
        JMenuItem menuItemNew  = new JMenuItem("Nuevo Contacto");
        JMenuItem menuItemExit = new JMenuItem("Salir");

        menuItemNew.addActionListener(e -> limpiarFormulario());
        menuItemExit.addActionListener(e -> System.exit(0));

        menuFile.add(menuItemNew);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu("Ayuda");
        menuItemErrors = new JMenuItem("Errores (0)");
        menuItemErrors.addActionListener(e -> mostrarErrores());
        JMenuItem menuItemAbout = new JMenuItem("Acerca de...");

        menuItemAbout.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "ContactBook v1.0\nAutor: Cristopher Guañuna\nESPE - Runtime Dynamics",
                "Acerca de",
                JOptionPane.INFORMATION_MESSAGE));

        menuHelp.add(menuItemErrors);
        menuHelp.addSeparator();
        menuHelp.add(menuItemAbout);

        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtCellphoneNumber.setText("");
        txtInstagram.setText("");
        txtFacebook.setText("");
        txtBirthDate.setText("");
        txaComments.setText("");
        chkIsKnown.setSelected(false);
        lstHobbies.clearSelection();
    }

    private void registrarError(String mensaje) {
        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
        errorLog.add("[" + hora + "] " + mensaje);
        menuItemErrors.setText("Errores (" + errorLog.size() + ")");
        JOptionPane.showMessageDialog(rootPane, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarErrores() {
        if (errorLog.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No hay errores registrados.",
                "Historial de errores",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String e : errorLog) {
            sb.append(e).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Historial de errores", JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings("unchecked")
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCellphoneNumber = new javax.swing.JTextField();
        txtInstagram = new javax.swing.JTextField();
        chkIsKnown = new javax.swing.JCheckBox();
        txtFacebook = new javax.swing.JTextField();
        cmbTypeOfContact = new javax.swing.JComboBox<>();
        cmbGender = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHobbies = new javax.swing.JList<>();
        txtBirthDate = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaComments = new javax.swing.JTextArea();
        pnlButtons = new javax.swing.JPanel();
        btmCancel = new javax.swing.JButton();
        btmSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ADD CONTACT");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(jLabel1)
                .addContainerGap(360, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel2.setText("id:");
        jLabel3.setText("Full Name:");
        jLabel4.setText("Email:");
        jLabel5.setText("Cell Phone Number:");
        jLabel6.setText("Instagram:");
        jLabel7.setText("Facebook:");
        jLabel8.setText("Know:");
        jLabel9.setText("Type:");
        jLabel10.setText("Gender:");
        jLabel11.setText("Hobbies:");
        jLabel12.setText("Birth Date:");
        jLabel13.setText("Comments:");

        chkIsKnown.setText("Yes");

        cmbTypeOfContact.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Friend", "Family", "Job Mate", "Gym Mate", "Highschool" }));
        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        lstHobbies.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Pay the guitar", "Watch movies", "Play soccer", "Play Basketball", "Reading", "Cooking", "Swimming", "Running" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstHobbies);

        txaComments.setColumns(20);
        txaComments.setRows(5);
        jScrollPane2.setViewportView(txaComments);

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(chkIsKnown))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFacebook, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(txtInstagram)
                            .addComponent(txtCellphoneNumber)
                            .addComponent(txtEmail)
                            .addComponent(txtFullName)
                            .addComponent(txtId))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTypeOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTypeOfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(chkIsKnown)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        btmCancel.setText("Cancel");

        btmSave.setText("Save");
        btmSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(btmSave)
                .addGap(185, 185, 185)
                .addComponent(btmCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmCancel)
                    .addComponent(btmSave))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void btmSaveActionPerformed(java.awt.event.ActionEvent evt) {
        Contact contact;
        int id;
        String fullName;
        String email;
        String cellPhoneNumber;
        String instagram;
        String facebook;
        boolean isKnow;
        String type;
        char gender;
        ArrayList<String> hobbies;
        Date birthDate;
        String comments;

        try {
            id = Integer.parseInt(txtId.getText().trim());
        } catch (NumberFormatException e) {
            registrarError("El ID debe ser un número entero.");
            return;
        }

        fullName = txtFullName.getText().trim();
        if (fullName.isEmpty() || !fullName.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            registrarError("El nombre solo debe contener letras y espacios.");
            return;
        }

        email = txtEmail.getText().trim();
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            registrarError("Ingrese un correo electrónico válido (ejemplo@dominio.com).");
            return;
        }

        cellPhoneNumber = txtCellphoneNumber.getText().trim();
        if (!cellPhoneNumber.matches("\\d{10}")) {
            registrarError("El número de celular debe tener 10 dígitos numéricos.");
            return;
        }

        instagram = txtInstagram.getText().trim();
        facebook = txtFacebook.getText().trim();
        isKnow = chkIsKnown.isSelected();
        type = (String) cmbTypeOfContact.getSelectedItem();
        gender = cmbGender.getSelectedItem().toString().charAt(0);
        hobbies = new ArrayList<>(lstHobbies.getSelectedValuesList());
        comments = txaComments.getText();

        String dateString = txtBirthDate.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            if (dateString.isEmpty()) {
                throw new ParseException("Fecha vacía", 0);
            }
            birthDate = sdf.parse(dateString);
        } catch (ParseException e) {
            registrarError("Ingrese la fecha en formato dd/MM/yyyy (ejemplo: 25/12/2000).");
            return;
        }

        contact = new Contact(id, fullName, email, cellPhoneNumber, instagram, facebook, isKnow, type, gender, hobbies, birthDate, comments);
        JOptionPane.showMessageDialog(rootPane, contact);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmContacts().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btmCancel;
    private javax.swing.JButton btmSave;
    private javax.swing.JCheckBox chkIsKnown;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbTypeOfContact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstHobbies;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextArea txaComments;
    private javax.swing.JTextField txtBirthDate;
    private javax.swing.JTextField txtCellphoneNumber;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFacebook;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtInstagram;
}