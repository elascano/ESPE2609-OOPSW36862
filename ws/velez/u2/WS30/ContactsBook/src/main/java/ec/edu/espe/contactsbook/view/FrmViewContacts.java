package ec.edu.espe.contactsbook.view;

import ec.edu.espe.contactsbook.controller.ContactController;
import ec.edu.espe.contactsbook.model.Contact;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class FrmViewContacts extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmViewContacts.class.getName());

    public FrmViewContacts() {
        initComponents();
        setLocationRelativeTo(null);
        loadContactsTable();
    }

    private void loadContactsTable() {
        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Contact c : ContactController.getContacts()) {
            String dateStr = (c.getBirtDate() != null) ? sdf.format(c.getBirtDate()) : "";
            model.addRow(new Object[]{
                c.getId(),
                c.getFullName(),
                c.getEmail(),
                c.getCellPhoneNumber(),
                c.getType(),
                c.getGender(),
                dateStr
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContacts = new javax.swing.JTable();
        btnMainMenu = new javax.swing.JButton();
        btnAddContact = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuContactsBook = new javax.swing.JMenu();
        itmMainMenu = new javax.swing.JMenuItem();
        itmQuit = new javax.swing.JMenuItem();
        mnuContacts = new javax.swing.JMenu();
        itmAdd = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Contacts");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTERED CONTACTS");

        tblContacts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name", "Email", "Cell Phone", "Type", "Gender", "Birth Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblContacts);

        btnMainMenu.setText("Back to Menu");
        btnMainMenu.addActionListener(this::btnMainMenuActionPerformed);

        btnAddContact.setText("Add Contact");
        btnAddContact.addActionListener(this::btnAddContactActionPerformed);

        mnuContactsBook.setText("ContactsBook");

        itmMainMenu.setText("Main Menu");
        itmMainMenu.addActionListener(this::itmMainMenuActionPerformed);
        mnuContactsBook.add(itmMainMenu);

        itmQuit.setText("Quit");
        itmQuit.addActionListener(this::itmQuitActionPerformed);
        mnuContactsBook.add(itmQuit);

        jMenuBar1.add(mnuContactsBook);

        mnuContacts.setText("Contacts");

        itmAdd.setText("Add Contacts");
        itmAdd.addActionListener(this::itmAddActionPerformed);
        mnuContacts.add(itmAdd);

        jMenuBar1.add(mnuContacts);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMainMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddContact)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMainMenu)
                    .addComponent(btnAddContact))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        new FrmContactsMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactActionPerformed
        new FrmContacs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddContactActionPerformed

    private void itmMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMainMenuActionPerformed
        new FrmContactsMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmMainMenuActionPerformed

    private void itmQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itmQuitActionPerformed

    private void itmAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAddActionPerformed
        new FrmContacs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JMenuItem itmAdd;
    private javax.swing.JMenuItem itmMainMenu;
    private javax.swing.JMenuItem itmQuit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuContacts;
    private javax.swing.JMenu mnuContactsBook;
    private javax.swing.JTable tblContacts;
    // End of variables declaration//GEN-END:variables
}
