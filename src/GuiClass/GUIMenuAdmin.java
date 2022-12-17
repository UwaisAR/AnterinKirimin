/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiClass;

import DAO.DATA;
import OOP.Items.Paket;
import OOP.Misc.Gudang;
import OOP.Users.Akun;
import OOP.Users.Kurir;
import OOP.Users.Pengirim;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roni
 */
public class GUIMenuAdmin extends javax.swing.JFrame {

    /**
     * Creates new form GUIMenuAdmin
     */
    private static final String[] KOLOM_PENGIRIM = {"Kode", "Nama Pengirim", "Kata Sandi", "Nomor Telepon", "Alamat"};
    private static final String[] KOLOM_KURIR = {"Kode", "Nama Kurir", "Kata Sandi", "Nomor Telepon", "Kota", "Status"};
    private static final String[] KOLOM_PAKET = {"Nomor Resi", "Tanggal Sampai", "Tanggal Kirim", "Tipe Pengiriman", "Status", "Harga", "Kode Pengirim"};
    
    public static void showList() throws SQLException {
        DATA.loadDATA();
        DefaultListModel model = new DefaultListModel();
        int count = 0;
        for (Gudang gudang : DATA.listGudang) {
            model.addElement(gudang.getKota());
            count++;
        }
        model.addElement("Semua Paket");
        ltGudang.setModel(model);
        ltGudang.setSelectedValue("Semua Paket", true);
    }
    
    public static void showTable() throws SQLException{
        DATA.loadDATA();
        try {
            DefaultTableModel model = new DefaultTableModel();
            for (String kolom : jTabbedPane1.getSelectedIndex()==0?KOLOM_PENGIRIM:jTabbedPane1.getSelectedIndex()==1?KOLOM_KURIR:KOLOM_PAKET) {
                model.addColumn(kolom);
            }
            jTable1.setModel(model);
            switch(jTabbedPane1.getSelectedIndex()){
                case 0:
                    for (Akun p : DATA.listAkun.stream().filter(t -> t instanceof Pengirim).collect(Collectors.toList())) {
                        model.addRow(new Object[]{p.getKode(), p.getUsername(), p.getPassword(), p.getNoHp(), ((Pengirim)p).getAlamat()});
                    }
                    break;
                case 1:
                    for (Akun p : DATA.listAkun.stream().filter(t -> t instanceof Kurir).collect(Collectors.toList())) {
                        model.addRow(new Object[]{p.getKode(), p.getUsername(), p.getPassword(), p.getNoHp(), ((Kurir)p).getKota(), ((Kurir)p).getStatus()});
                    }
                    break;
                case 2:
                    if (ltGudang.getSelectedValue().equals("Semua Paket")){
                        for (Paket p : DATA.listPaket){
                            model.addRow(new Object[]{p.getNoResi(), p.getTanggalSampai()!=null?p.getTanggalSampai():p.getEstimasiSampai(), p.getTanggalDibuat(), p.getTipe(), p.getStatus(), p.hargaBarang()+p.hargaPajak(), p.getPengirim().getKode()});
                        }
                    } else {
                        for (Paket p : DATA.listPaket.stream().filter(t -> t.getGudang()!=null).filter(t -> t.getGudang().getKota().equals(ltGudang.getSelectedValue())).collect(Collectors.toList())){
                            model.addRow(new Object[]{p.getNoResi(), p.getEstimasiSampai(), p.getTanggalDibuat(), p.getTipe(), p.getStatus(), p.hargaBarang()+p.hargaPajak(), p.getPengirim().getKode()});
                        }
                    }
                    break;
            }
            jTable1.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIMenuPengirim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String generateKode(String kode, String idKode){
        while(idKode.length()<4){
            idKode = "0" + idKode;
        }
        return kode+idKode;
    }
    
    protected static String getTableValue(int row){
        return(String) jTable1.getValueAt(jTable1.getSelectedRow(), row);
    }
    
    public void insertLabelfromTable(){
        switch (jTabbedPane1.getSelectedIndex()){
            case 0:
                lIduserPengirim.setText(getTableValue(0));
                lUsernamePengirim.setText(getTableValue(1));
                lNomorteleponPengirim.setText(getTableValue(3));
                lAlamat.setText(getTableValue(4));
                break;
            case 1:
                lIduserKurir.setText(getTableValue(0));
                lUsernameKurir.setText(getTableValue(1));
                lNomorteleponKurir.setText(getTableValue(3));
                lKotaKurir.setText(getTableValue(4));
                lStatusKurir.setText(getTableValue(5));
                break;
            case 2:
                try {
                    DATA.loadDATA();
                    if (GUIMenuAdmin.jTable1.getSelectedRow()!=-1&&jTabbedPane1.getSelectedIndex()==2){
                        Paket paket = DATA.listPaket.stream().filter(t -> t.getNoResi().equals(GUIMenuAdmin.jTable1.getValueAt(GUIMenuAdmin.jTable1.getSelectedRow(), 0))).findFirst().orElse(null);
                        lKodeGudang.setText(paket.getGudang()==null?"Semua Paket":paket.getGudang().getKode());
                        lNoResi.setText(paket.getNoResi());
                        lTanggalBuat.setText(paket.getTanggalDibuat().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
                        if (paket.getTanggalSampai()!=null)lTanggalSampai.setText(paket.getTanggalSampai().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
                        else lTanggalSampai.setText(paket.getEstimasiSampai());
                        lKotaTujuanPaket.setText(paket.getAlamatTujuan());
                        lHargaPaket.setText("Rp. "+paket.hargaBarang()+" (+"+(paket.hargaPajak())+")");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal load detail paket");
                }
                break;
        }
    }
    public GUIMenuAdmin() throws SQLException {
        initComponents();
        showTable();
        showList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lIduserPengirim = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lUsernamePengirim = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lNomorteleponPengirim = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lAlamat = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnTambahKurir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lIduserKurir = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lUsernameKurir = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lNomorteleponKurir = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lKotaKurir = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lStatusKurir = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ltGudang = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        btnEditGudang = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lNoResi = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lTanggalBuat = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lTanggalSampai = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lKotaTujuanPaket = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lKodeGudang = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lHargaPaket = new javax.swing.JLabel();
        btnTambahGudang = new javax.swing.JButton();
        btnHapusGudang = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID User :");

        lIduserPengirim.setText("-");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama User :");

        lUsernamePengirim.setText("-");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nomor Telepon :");

        lNomorteleponPengirim.setText("-");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Alamat :");

        lAlamat.setEditable(false);
        lAlamat.setColumns(20);
        lAlamat.setLineWrap(true);
        lAlamat.setRows(3);
        lAlamat.setText("-");
        lAlamat.setWrapStyleWord(true);
        lAlamat.setAutoscrolls(false);
        lAlamat.setOpaque(false);
        jScrollPane3.setViewportView(lAlamat);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Detail Pengirim");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lIduserPengirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lUsernamePengirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lNomorteleponPengirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lIduserPengirim))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lUsernamePengirim))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lNomorteleponPengirim))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab("Pengirim", jPanel1);

        btnTambahKurir.setText("Tambahkan Kurir");
        btnTambahKurir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKurirActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ID User :");

        lIduserKurir.setText("-");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nama User :");

        lUsernameKurir.setText("-");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nomor Telepon :");

        lNomorteleponKurir.setText("-");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Kota kurir :");

        lKotaKurir.setText("-");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Status :");

        lStatusKurir.setText("-");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Detail Kurir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lIduserKurir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lUsernameKurir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lNomorteleponKurir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lStatusKurir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lKotaKurir, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTambahKurir)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lIduserKurir))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lUsernameKurir))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lNomorteleponKurir))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lKotaKurir))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lStatusKurir))
                .addGap(22, 22, 22)
                .addComponent(btnTambahKurir))
        );

        jTabbedPane1.addTab("Kurir", jPanel2);

        ltGudang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cabang Gudang"));
        ltGudang.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ltGudang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ltGudangMousePressed(evt);
            }
        });
        ltGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ltGudangKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(ltGudang);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Detail Gudang");

        btnEditGudang.setText("Edit Gudang");
        btnEditGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGudangActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Nomor Resi :");

        lNoResi.setText("-");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Tanggal Dibuat:");

        lTanggalBuat.setText("-");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tanggal Sampai: ");

        lTanggalSampai.setText("-");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Kota Tujuan :");

        lKotaTujuanPaket.setText("-");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Kode Gudang :");

        lKodeGudang.setText("-");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Harga Paket:");

        lHargaPaket.setText("-");

        btnTambahGudang.setText("Tambah");
        btnTambahGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahGudangActionPerformed(evt);
            }
        });

        btnHapusGudang.setText("Hapus");
        btnHapusGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusGudangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lKodeGudang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lHargaPaket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lTanggalBuat, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(lNoResi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lTanggalSampai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lKotaTujuanPaket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(btnEditGudang)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnTambahGudang))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHapusGudang))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lKodeGudang))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lNoResi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lTanggalBuat))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lTanggalSampai))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lKotaTujuanPaket))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lHargaPaket)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditGudang)
                            .addComponent(btnTambahGudang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusGudang)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gudang", jPanel3);

        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        btnEdit.setText("Edit ");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnHapus))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCari)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnUpdate))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        new StartMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTambahKurirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKurirActionPerformed
        try {
            // TODO add your handling code here:
            new DialogEditTambahKurir(this, true).setVisible(true);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
        }
    }//GEN-LAST:event_btnTambahKurirActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPengirim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        try {
            // TODO add your handling code here:
            if (jTabbedPane1.getSelectedIndex()==2){
                btnHapus.setEnabled(false);
            } else {
                btnHapus.setEnabled(true);
            }
            showTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        insertLabelfromTable();
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        for (String kolom : jTabbedPane1.getSelectedIndex()==0?KOLOM_PENGIRIM:jTabbedPane1.getSelectedIndex()==1?KOLOM_KURIR:KOLOM_PAKET) {
            model.addColumn(kolom);
        }
        switch (jTabbedPane1.getSelectedIndex()){
            case 0:
                for (Akun p : DATA.listAkun.stream().filter(t ->
                        t instanceof Pengirim&&(
                                t.getKode().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                                t.getNoHp().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                                t.getUsername().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                                t.getPassword().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                                ((Pengirim)t).getAlamat().toLowerCase().contains(jTextField1.getText().toLowerCase())
                        )
                ).collect(Collectors.toList())) {
                    model.addRow(new Object[]{p.getKode(), p.getUsername(), p.getPassword(), p.getNoHp(), ((Pengirim)p).getAlamat()});
                }
                break;
            case 1:
                for (Akun p : DATA.listAkun.stream().filter(t ->
                        t instanceof Kurir&&(
                            t.getKode().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                            t.getNoHp().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                            t.getUsername().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                            t.getPassword().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                            ((Kurir)t).getKota().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                            ((Kurir)t).getStatus().toLowerCase().contains(jTextField1.getText().toLowerCase())
                        )
                ).collect(Collectors.toList())) {
                    model.addRow(new Object[]{p.getKode(), p.getUsername(), p.getPassword(), p.getNoHp(), ((Kurir)p).getKota(), ((Kurir)p).getStatus()});
                }
                break;
            case 2:
                for (Paket p : DATA.listPaket.stream().filter(t ->
                        t.getNoResi().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getTanggalDibuat().toString().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getEstimasiSampai().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getTipe().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getStatus().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        String.valueOf(t.hargaBarang()+t.hargaPajak()).toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getPengirim().getUsername().toLowerCase().contains(jTextField1.getText().toLowerCase())||
                        t.getPengirim().getKode().toLowerCase().contains(jTextField1.getText().toLowerCase())
                ).collect(Collectors.toList())) {
                    model.addRow(new Object[]{p.getNoResi(), p.getEstimasiSampai(), p.getTanggalDibuat(), p.getTipe(), p.getStatus(), p.hargaBarang()+p.hargaPajak(), p.getPengirim().getKode()});
                }
                break;
        }
        jTable1.setModel(model);
        jTextField1.setText(null);
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            switch (jTabbedPane1.getSelectedIndex()){
                case 0:
                    new DialogEditDaftarPengirim(this, true).setVisible(true);
                    break;
                case 1:
                    new DialogEditTambahKurir(this, true).setVisible(true);
                    break;
                case 2:
                    new DialogDetailEditPaket(this, true).setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tabel belum dipilih");
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try {
            // TODO add your handling code here:
            DATA.loadDATA();
            String obj = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex())+" "+jTable1.getValueAt(jTable1.getSelectedRow(), 1); 
            String hapusElemen = jTable1.getValueAt(jTable1.getSelectedRow(), 0)+"";
            if (JOptionPane.showConfirmDialog(this, "Hapus "+obj+"?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                switch(jTabbedPane1.getSelectedIndex()){
                    case 0:
                        DATA.hapusDATA(DATA.listAkun.stream().filter(t -> t instanceof Pengirim&&t.getKode().equals(hapusElemen)).findFirst().orElse(null));
                        break;
                    case 1:
                        DATA.hapusDATA(DATA.listAkun.stream().filter(t -> t instanceof Kurir&&t.getKode().equals(hapusElemen)).findFirst().orElse(null));
                        break;
                }
                showTable();
                JOptionPane.showMessageDialog(this, obj+" berhasil dihapus");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak bisa dihapus");
        }
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGudangActionPerformed
        // TODO add your handling code here:
        try {
            if(DATA.listGudang.get(ltGudang.getSelectedIndex()).getPaket()==null) throw new Exception("Gudang tidak bisa diedit");
            new DialogEditTambahGudang(this, true).setVisible(true);
        } catch (IndexOutOfBoundsException|SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak bisa edit gudang");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnEditGudangActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        insertLabelfromTable();
    }//GEN-LAST:event_jTable1KeyReleased

    private void ltGudangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ltGudangMousePressed
        try {
            // TODO add your handling code here:
            showTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_ltGudangMousePressed

    private void ltGudangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ltGudangKeyReleased
        try {
            // TODO add your handling code here:
            showTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_ltGudangKeyReleased

    private void btnTambahGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahGudangActionPerformed
        try {
            // TODO add your handling code here:
            new DialogEditTambahGudang(this, true).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnTambahGudangActionPerformed

    private void btnHapusGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusGudangActionPerformed
        try {
            // TODO add your handling code here:
            Kurir kurir = (Kurir) DATA.listAkun.stream().filter(t -> t instanceof Kurir && ((Kurir)t).getKota().equals(DATA.listGudang.get(ltGudang.getSelectedIndex()).getKota())).findAny().orElse(null);
            for (Paket pkg : DATA.listGudang.get(ltGudang.getSelectedIndex()).getPaket()) {
                kurir.getPaket().add(pkg);
            }
            DATA.updateDATA(kurir);
            DATA.hapusDATA(DATA.listGudang.get(ltGudang.getSelectedIndex()));
            JOptionPane.showMessageDialog(this, "Berhasil hapus gudang");
            showList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tidak bisa hapus gudang");
        }
        
    }//GEN-LAST:event_btnHapusGudangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIMenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new GUIMenuAdmin().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(GUIMenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    public static javax.swing.JButton btnEdit;
    public static javax.swing.JButton btnEditGudang;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusGudang;
    private javax.swing.JButton btnLogout;
    public static javax.swing.JButton btnTambahGudang;
    public static javax.swing.JButton btnTambahKurir;
    public static javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea lAlamat;
    private javax.swing.JLabel lHargaPaket;
    private javax.swing.JLabel lIduserKurir;
    private javax.swing.JLabel lIduserPengirim;
    private javax.swing.JLabel lKodeGudang;
    private javax.swing.JLabel lKotaKurir;
    private javax.swing.JLabel lKotaTujuanPaket;
    private javax.swing.JLabel lNoResi;
    private javax.swing.JLabel lNomorteleponKurir;
    private javax.swing.JLabel lNomorteleponPengirim;
    private javax.swing.JLabel lStatusKurir;
    private javax.swing.JLabel lTanggalBuat;
    private javax.swing.JLabel lTanggalSampai;
    private javax.swing.JLabel lUsernameKurir;
    private javax.swing.JLabel lUsernamePengirim;
    public static javax.swing.JList<String> ltGudang;
    // End of variables declaration//GEN-END:variables
}
