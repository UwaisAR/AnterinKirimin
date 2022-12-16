/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiClass;

import OOP.Items.Paket;
import OOP.Users.Akun;
import OOP.Users.Pengirim;
import OOP.Users.Kurir;
import DAO.DATA;
import OOP.Misc.Gudang;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roni
 */
public class GUIMenuKurir extends javax.swing.JFrame {

    public static Kurir kurir;
    private static Gudang gudang;
    private static final ArrayList<Paket> LIST_PAKET_SENDER = new ArrayList<>();
    private static final ArrayList<Paket> LIST_PAKET_KURIR = new ArrayList<>();
    private static final ArrayList<Paket> LIST_PAKET_GUDANG = new ArrayList<>();
    public static String[] kolomPaket = {"Nomor Resi", "Tipe Pengiriman", "Alamat Penerima", "Kota Tujuan", "Nama Pengirim", "Nomor Telepon"};
    /**
     * Creates new form GUIMenuKurir
     * @throws java.sql.SQLException
     */
    public static void loadData() throws SQLException{
        kurir = (Kurir) DATA.authAccount(GUILogin.tfUsername.getText(), GUILogin.tfPassword1.getText());
        gudang = DATA.listGudang.stream().filter(t -> t.getKota().toLowerCase().contains(kurir.getKota().toLowerCase())).findFirst().orElse(null);
    }
    
    static void showAll() throws SQLException{
        DATA.loadDATA();
        loadData();
        DefaultTableModel modelTable = new DefaultTableModel();
        DefaultListModel modelList = new DefaultListModel();
        for (Akun pengirim : DATA.listAkun.stream().filter(t -> t instanceof Pengirim&&((Pengirim)t).getAlamat().toLowerCase().contains(kurir.getKota().toLowerCase())).collect(Collectors.toList())) {
            for (Paket paket : pengirim.getPaket().stream().filter(t -> t.getKurir()==null&&t.getGudang()==null&&t.getStatus().equals("WAITING")).collect(Collectors.toList())) {
                LIST_PAKET_SENDER.add(paket);
                modelList.addElement(paket.getNoResi()+" - "+paket.getPengirim().getUsername()+" "+paket.getTanggalDibuat());
            }
        }
        ltPaketSender.setModel(modelList);
        modelList = new DefaultListModel();
        for (Paket paket : kurir.getPaket()) {
            LIST_PAKET_KURIR.add(paket);
            modelList.addElement(paket.getNoResi()+" - "+paket.getPengirim().getUsername()+" "+paket.getTanggalDibuat());
        }
        ltPaketKurir.setModel(modelList);
        for (String string : kolomPaket) {
            modelTable.addColumn(string);
        }
        for (Paket paket : gudang.getPaket()) {
            LIST_PAKET_GUDANG.add(paket);
            modelTable.addRow(new Object[]{paket.getNoResi(), paket.getTipe(), paket.getPengirim().getAlamat(), paket.getAlamatTujuan(), paket.getPengirim().getUsername(), paket.getPengirim().getNoHp()});
        }
        jTable1.setModel(modelTable);
    }
    
    public GUIMenuKurir() throws SQLException {
        loadData();
        initComponents();
        TitledBorder border = BorderFactory.createTitledBorder("Warehouse "+gudang.getKota()+" ["+gudang.getKode()+"]");
        border.setBorder(BorderFactory.createEtchedBorder());
        border.setTitleFont(new Font("Dialog", Font.BOLD, 14));
        jPanel3.setBorder(border);
        jLabel1.setText(jLabel1.getText().replace("namauser", GUILogin.tfUsername.getText()));
        showAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAmbilSender = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ltPaketSender = new javax.swing.JList<>();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnKirimGudang = new javax.swing.JButton();
        btnKirimRecipient = new javax.swing.JButton();
        btnAmbilGudang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnKirim = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ltPaketKurir = new javax.swing.JList<>();
        btnReturn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List Paket Pengirim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel1.setToolTipText("");

        btnAmbilSender.setText("Ambil Paket");
        btnAmbilSender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilSenderActionPerformed(evt);
            }
        });

        ltPaketSender.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ltPaketSender);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAmbilSender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addGap(0, 166, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAmbilSender)
                    .addComponent(btnUpdate))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Gudang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        btnKirimGudang.setText("Kirim ke Gudang tujuan");
        btnKirimGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimGudangActionPerformed(evt);
            }
        });

        btnKirimRecipient.setText("Kirim ke Penerima");
        btnKirimRecipient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimRecipientActionPerformed(evt);
            }
        });

        btnAmbilGudang.setText("Ambil Paket");
        btnAmbilGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilGudangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnKirimGudang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKirimRecipient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAmbilGudang)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKirimGudang)
                    .addComponent(btnKirimRecipient)
                    .addComponent(btnAmbilGudang))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Paket yang dibawa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        btnKirim.setText("Kirim ke Gudang");
        btnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimActionPerformed(evt);
            }
        });

        ltPaketKurir.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(ltPaketKurir);

        btnReturn.setText("Kembalikan ke pengirim");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnKirim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReturn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKirim)
                    .addComponent(btnReturn))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Selamat datang, namauser");

        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jButton1.setText("Edit Profil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        new StartMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // TODO add your handling code here:
            showAll();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAmbilSenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilSenderActionPerformed
        // TODO add your handling code here:
        try {
            System.out.println(LIST_PAKET_SENDER.size());
            LIST_PAKET_SENDER.get(ltPaketSender.getSelectedIndex()).setKurir(kurir);
            kurir.setStatus("DELIVERING PACKAGE TO WAREHOUSE");
            DATA.updateDATA(kurir);
            DATA.updateDATA(LIST_PAKET_SENDER.get(ltPaketSender.getSelectedIndex()));
            showAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal ambil paket");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ambil paket");
        }
        
    }//GEN-LAST:event_btnAmbilSenderActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        try {
            LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setKurir(null);
            LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setGudang(null);
            LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setStatus("WAITING");
            if (!DATA.listGudang.stream().anyMatch(t -> LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).getAlamatTujuan().toLowerCase().contains(t.getKota().toLowerCase()))){
                LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setJarak(0);
                LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setStatus("WRONG ADDRESS");
            }
            kurir.setStatus("RETRUN PACAKGE TO SENDER");
            DATA.updateDATA(kurir);
            DATA.updateDATA(LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()));
            showAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengembalikan paket");
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal mengembalikan paket");
        }
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimActionPerformed
        try {
            // TODO add your handling code here:
            LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setGudang(gudang);
            LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()).setKurir(null);
            DATA.updateDATA(LIST_PAKET_KURIR.get(ltPaketKurir.getSelectedIndex()));
            if(ltPaketKurir.getSize()==null) {
                kurir.setStatus("WAITING FOR PACKAGE");
                DATA.updateDATA(kurir);
            }
            showAll();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengirim paket ke gudang");
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal mengirim paket ke gudang");
        }
    }//GEN-LAST:event_btnKirimActionPerformed

    private void btnKirimRecipientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimRecipientActionPerformed
        // TODO add your handling code here:
        try {
            if(!LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).getAlamatTujuan().toLowerCase().contains(gudang.getKota().toLowerCase())){
                throw new Exception("Tidak bisa mengirimkan paket");
            } else {
                LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setTanggalSampai(LocalDate.now());
                LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setGudang(null);
                LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setKurir(null);
                LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setStatus("RECEIVED TO RECIPIENT {"+LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).getAlamatTujuan()+"]");
                DATA.updateDATA(LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()));
                kurir.setStatus("DELIVERING THE PACKAGE TO RECIPIENT");
                DATA.updateDATA(LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()));
                DATA.updateDATA(kurir);
                DATA.updateDATA(gudang);
                showAll();
                JOptionPane.showMessageDialog(this, "Berhasil mengirim barang ke penerima");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnKirimRecipientActionPerformed

    private void btnKirimGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimGudangActionPerformed
        try {
            // TODO add your handling code here:
            LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setGudang(DATA.listGudang.stream().filter(t -> LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).getAlamatTujuan().toLowerCase().contains(t.getKota().toLowerCase())).findFirst().orElse(null)
            );
            kurir.setStatus("DELIVERING PACKAGE TO RECIPIENT WAREHOUSE ["+gudang.getKota()+"]");
            DATA.updateDATA(kurir);
            DATA.updateDATA(LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()));
            showAll();
            JOptionPane.showMessageDialog(this, "Berhasil mengirim barang ke gudang lain");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengirimkan Paket");
        }
    }//GEN-LAST:event_btnKirimGudangActionPerformed

    private void btnAmbilGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilGudangActionPerformed
        // TODO add your handling code here:
        try {
            System.out.println(LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).getNoResi());
            LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setKurir(kurir);
            LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()).setGudang(null);
            kurir.setStatus("PICK UP PACKAGE FROM WAREHOUSE");
            DATA.updateDATA(kurir);
            DATA.updateDATA(LIST_PAKET_GUDANG.get(jTable1.getSelectedRow()));
            showAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal ambil paket");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ambil paket");
        }
    }//GEN-LAST:event_btnAmbilGudangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            new DialogEditTambahKurir(this, true).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIMenuKurir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            try {
//                new GUIMenuKurir().setVisible(true);
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan");
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAmbilGudang;
    private javax.swing.JButton btnAmbilSender;
    private javax.swing.JButton btnKirim;
    private javax.swing.JButton btnKirimGudang;
    private javax.swing.JButton btnKirimRecipient;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JList<String> ltPaketKurir;
    public static javax.swing.JList<String> ltPaketSender;
    // End of variables declaration//GEN-END:variables
}
