package ec.edu.espe.contactsbook.view;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class FrmContactsMenu extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmContactsMenu.class.getName());

    public FrmContactsMenu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        btnAddContact = new javax.swing.JButton();
        btnViewContacts = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuContactsBook = new javax.swing.JMenu();
        itmAbout = new javax.swing.JMenuItem();
        itmQuit = new javax.swing.JMenuItem();
        mnuContacts = new javax.swing.JMenu();
        itmAddContacts = new javax.swing.JMenuItem();
        itmViewContacts = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contacts Book - Menu");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("CONTACTS BOOK SYSTEM");

        lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitle.setText("Select an option from the menu or buttons below:");

        btnAddContact.setText("Add Contact");
        btnAddContact.addActionListener(this::btnAddContactActionPerformed);

        btnViewContacts.setText("View Contacts");
        btnViewContacts.addActionListener(this::btnViewContactsActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(lblSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(btnAddContact, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnViewContacts, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblSubtitle)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddContact, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewContacts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        mnuContactsBook.setText("ContactsBook");

        itmAbout.setText("About");
        itmAbout.addActionListener(this::itmAboutActionPerformed);
        mnuContactsBook.add(itmAbout);

        itmQuit.setText("Quit");
        itmQuit.addActionListener(this::itmQuitActionPerformed);
        mnuContactsBook.add(itmQuit);

        jMenuBar1.add(mnuContactsBook);

        mnuContacts.setText("Contacts");

        itmAddContacts.setText("Add Contacts");
        itmAddContacts.addActionListener(this::itmAddContactsActionPerformed);
        mnuContacts.add(itmAddContacts);

        itmViewContacts.setText("View Contacts");
        itmViewContacts.addActionListener(this::itmViewContactsActionPerformed);
        mnuContacts.add(itmViewContacts);

        jMenuBar1.add(mnuContacts);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAboutActionPerformed
        FrmAbout frmAbout = new FrmAbout();
        frmAbout.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmAboutActionPerformed

    private void itmQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itmQuitActionPerformed

    private void itmAddContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAddContactsActionPerformed
        FrmContacs frmContacs = new FrmContacs();
        frmContacs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmAddContactsActionPerformed

    private void itmViewContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmViewContactsActionPerformed
        FrmViewContacts frmView = new FrmViewContacts();
        frmView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmViewContactsActionPerformed

    private void btnAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactActionPerformed
        FrmContacs frmContacs = new FrmContacs();
        frmContacs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddContactActionPerformed

    private void btnViewContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewContactsActionPerformed
        FrmViewContacts frmView = new FrmViewContacts();
        frmView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewContactsActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new FrmContactsMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnViewContacts;
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmAddContacts;
    private javax.swing.JMenuItem itmQuit;
    private javax.swing.JMenuItem itmViewContacts;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu mnuContacts;
    private javax.swing.JMenu mnuContactsBook;
    // End of variables declaration//GEN-END:variables
}
