/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiClass;

import DAO.DATA;
import OOP.Items.Item;
import OOP.Items.Paket;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roni
 */
public class DialogEditTambahPaketItem extends javax.swing.JDialog {

    /**
     * Creates new form DialogEditTambahPaketItem
     * @throws java.sql.SQLException
     */
    ArrayList<Item> listItem = new ArrayList<>();
    Paket paket;
    java.awt.Frame parent;
    private void getTable(){
        Paket text = GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow());
        tfItemName.setForeground(Color.BLACK);
        tfHeight.setForeground(Color.BLACK);
        tfWidth.setForeground(Color.BLACK);
        tfLength.setForeground(Color.BLACK);
        tfWeight.setForeground(Color.BLACK);
        tfAlamat.setText(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getAlamatTujuan());
        tfJarak.setText(String.valueOf(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getJarak()));
        tfItemName.setText(text.getItem().get(jTable1.getSelectedRow()).getNama());
        tfHeight.setText(String.valueOf(text.getItem().get(jTable1.getSelectedRow()).getDimensi()[2]));
        tfWidth.setText(String.valueOf(text.getItem().get(jTable1.getSelectedRow()).getDimensi()[1]));
        tfLength.setText(String.valueOf(text.getItem().get(jTable1.getSelectedRow()).getDimensi()[0]));
        tfWeight.setText(String.valueOf(text.getItem().get(jTable1.getSelectedRow()).getBerat()));
    }
    private void showTable() throws SQLException{
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nama");
            model.addColumn("Berat");
            model.addColumn("Dimensi");
            jTable1.setModel(model);
            if (jLabel6.getText().equals("Edit Paket")||jLabel6.getText().equals("Detail Paket")){
                for (Item item : GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getItem()) {
                    model.addRow(new Object[]{item.getNama(), item.getBerat()+"kg", item.getDimensi()[0]+"cm x"+item.getDimensi()[1]+"cm x"+item.getDimensi()[2]+"cm"});
                }
            } else {
                for (Item item : listItem) {
                    model.addRow(new Object[]{item.getNama(), item.getBerat()+"kg", item.getDimensi()[0]+"cm x"+item.getDimensi()[1]+"cm x"+item.getDimensi()[2]+"cm"});
                }
            }
            jTable1.setModel(model);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public DialogEditTambahPaketItem(java.awt.Frame parent, boolean modal) throws Exception{
        super(parent, modal);
        this.parent = parent;
        initComponents();
        rbFast.setSelected(true);
        if (GUIMenuPengirim.btnEdit.getModel().isArmed()){
            jLabel6.setText("Edit Paket");
            jButton4.setText("Edit Paket");
            tfAlamat.setForeground(Color.BLACK);
            tfJarak.setForeground(Color.BLACK);
            String replace = lTotalHarga.getText().replace("-", GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaBarang()+" ("+GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaPajak()+")");
            lTotalHarga.setText(replace);
            tfAlamat.setText(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getAlamatTujuan());
            tfJarak.setText(String.valueOf(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getJarak()));
        } else if (GUIMenuPengirim.btnTambah.getModel().isArmed()) {
            jLabel6.setText("Tambah Paket");
        } else if (GUIMenuPengirim.btnDetail.getModel().isArmed()){
            jLabel6.setText("Detail Paket");
            String replace = lTotalHarga.getText().replace("-", String.valueOf(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).hargaBarang()));
            lTotalHarga.setText(replace);
            tfAlamat.setEditable(false);
            tfItemName.setEditable(false);
            tfJarak.setEditable(false);
            tfHeight.setEditable(false);
            tfLength.setEditable(false);
            tfWidth.setEditable(false);
            tfWeight.setEditable(false);
            tfHeight.setEnabled(true);
            tfLength.setEnabled(true);
            tfWidth.setEnabled(true);
            tfWeight.setEnabled(true);
            rbBeratDimensi.setVisible(false);
            rbBerat.setVisible(false);
            tfAlamat.setForeground(Color.BLACK);
            tfJarak.setForeground(Color.BLACK);
            tfAlamat.setText(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getAlamatTujuan());
            tfJarak.setText(String.valueOf(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow()).getJarak()));
            jButton1.setVisible(false);
            jButton3.setVisible(false);
            jButton4.setVisible(false);
            jLabel10.setVisible(false);
            rbFast.setVisible(false);
            rbSlow.setVisible(false);
        }
        showTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgBerat = new javax.swing.ButtonGroup();
        bgTipePengiriman = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tfItemName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfLength = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfWidth = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfHeight = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfWeight = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lTotalHarga = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfAlamat = new javax.swing.JTextField();
        tfJarak = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        rbBerat = new javax.swing.JRadioButton();
        rbBeratDimensi = new javax.swing.JRadioButton();
        rbFast = new javax.swing.JRadioButton();
        rbSlow = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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

        tfItemName.setForeground(java.awt.Color.gray);
        tfItemName.setText("ex: Kopi Landak");
        tfItemName.setEnabled(false);
        tfItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfItemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfItemNameFocusLost(evt);
            }
        });
        tfItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfItemNameActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nama Item :");

        tfLength.setForeground(java.awt.Color.gray);
        tfLength.setText("ex: 10");
        tfLength.setEnabled(false);
        tfLength.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfLengthFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLengthFocusLost(evt);
            }
        });
        tfLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLengthActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Panjang (cm) :");

        tfWidth.setForeground(java.awt.Color.gray);
        tfWidth.setText("ex: 10");
        tfWidth.setEnabled(false);
        tfWidth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfWidthFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfWidthFocusLost(evt);
            }
        });
        tfWidth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfWidthActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Lebar (cm) :");

        tfHeight.setForeground(java.awt.Color.gray);
        tfHeight.setText("ex: 10");
        tfHeight.setEnabled(false);
        tfHeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfHeightFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfHeightFocusLost(evt);
            }
        });
        tfHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHeightActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tinggi (cm) :");

        tfWeight.setForeground(java.awt.Color.gray);
        tfWeight.setText("ex: 10");
        tfWeight.setEnabled(false);
        tfWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfWeightFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfWeightFocusLost(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Berat (Kg) :");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("-??- Paket");

        jButton1.setText("Tambah Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lTotalHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lTotalHarga.setText("Total Harga: Rp.-");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Alamat Tujuan :");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Jarak :");

        tfAlamat.setForeground(java.awt.Color.gray);
        tfAlamat.setText("Jalan Buah Batu No.02, RT 09 RW 06, Lengkong, Bojongsoang");
        tfAlamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfAlamatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAlamatFocusLost(evt);
            }
        });
        tfAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfAlamatMouseReleased(evt);
            }
        });
        tfAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAlamatActionPerformed(evt);
            }
        });
        tfAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAlamatKeyReleased(evt);
            }
        });

        tfJarak.setForeground(java.awt.Color.gray);
        tfJarak.setText("ex: 10");
        tfJarak.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfJarakFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfJarakFocusLost(evt);
            }
        });
        tfJarak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfJarakMouseReleased(evt);
            }
        });
        tfJarak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJarakActionPerformed(evt);
            }
        });
        tfJarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfJarakKeyReleased(evt);
            }
        });

        jButton4.setText("Buat Paket");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        bgBerat.add(rbBerat);
        rbBerat.setText("Berat");
        rbBerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBeratActionPerformed(evt);
            }
        });

        bgBerat.add(rbBeratDimensi);
        rbBeratDimensi.setText("BeratDimensi");
        rbBeratDimensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBeratDimensiActionPerformed(evt);
            }
        });

        bgTipePengiriman.add(rbFast);
        rbFast.setText("Fast");
        rbFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFastActionPerformed(evt);
            }
        });

        bgTipePengiriman.add(rbSlow);
        rbSlow.setText("Slow");
        rbSlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSlowActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Tipe Pengiriman : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lTotalHarga))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbBerat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbBeratDimensi))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfHeight, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfWidth, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfLength, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfItemName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbFast)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSlow))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfJarak, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfJarak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFast)
                    .addComponent(rbSlow)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton1))
                    .addComponent(lTotalHarga))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbBerat)
                    .addComponent(rbBeratDimensi))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfItemNameFocusGained
        // TODO add your handling code here:
        if (tfItemName.getForeground()==Color.GRAY){
            tfItemName.setText("");
            tfItemName.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfItemNameFocusGained

    private void tfItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfItemNameFocusLost
        // TODO add your handling code here:
        if (tfItemName.getText().equals("")){
            tfItemName.setText("ex: Kopi Landak");
            tfItemName.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfItemNameFocusLost

    private void tfItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfItemNameActionPerformed

    private void tfLengthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLengthFocusGained
        // TODO add your handling code here:
        if (tfLength.getForeground()==Color.GRAY){
            tfLength.setText("");
            tfLength.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfLengthFocusGained

    private void tfLengthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLengthFocusLost
        // TODO add your handling code here:
        if (tfLength.getText().equals("")){
            tfLength.setText("ex: 10");
            tfLength.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfLengthFocusLost

    private void tfLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLengthActionPerformed

    private void tfWidthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfWidthFocusGained
        // TODO add your handling code here:
        if (tfWidth.getForeground()==Color.GRAY){
            tfWidth.setText("");
            tfWidth.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfWidthFocusGained

    private void tfWidthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfWidthFocusLost
        // TODO add your handling code here:
        if (tfWidth.getText().equals("")){
            tfWidth.setText("ex: 10");
            tfWidth.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfWidthFocusLost

    private void tfHeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfHeightFocusGained
        // TODO add your handling code here:
        if (tfHeight.getForeground()==Color.GRAY){
            tfHeight.setText("");
            tfHeight.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfHeightFocusGained

    private void tfHeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfHeightFocusLost
        // TODO add your handling code here:
        if (tfHeight.getText().equals("")){
            tfHeight.setText("ex: 10");
            tfHeight.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfHeightFocusLost

    private void tfWeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfWeightFocusGained
        // TODO add your handling code here:
        if (tfWeight.getForeground()==Color.GRAY){
            tfWeight.setText("");
            tfWeight.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfWeightFocusGained

    private void tfWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfWeightFocusLost
        // TODO add your handling code here:
        if (tfWeight.getText().equals("")){
            tfWeight.setText("ex: 10");
            tfWeight.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfWeightFocusLost

    private void tfWidthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfWidthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfWidthActionPerformed

    private void tfHeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHeightActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tfItemName.getForeground()==Color.BLACK&&
            ((tfHeight.getForeground()==Color.BLACK&&
            tfLength.getForeground()==Color.BLACK&&
            tfWidth.getForeground()==Color.BLACK)||
            (tfWeight.getForeground()==Color.BLACK))){
            Item item = null;
            if (tfWeight.isEnabled()){
                try {
                    item = new Item(null, tfItemName.getText(), Double.parseDouble(tfWeight.getText()));
                    item.setPaket(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount));
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Input berat salah");
                }
                
            } else {
                try {
                    item = new Item(null, tfItemName.getText(), new double[]{Double.parseDouble(tfLength.getText()),Double.parseDouble(tfWidth.getText()),Double.parseDouble(tfHeight.getText())});
                    item.setPaket(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount));
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Input dimensi salah");
                }
            } 
            try {
                if ("Edit Paket".equals(jLabel6.getText())){
                    DATA.addDATA(item);
                    lTotalHarga.setText("Total harga: Rp."+GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaBarang()+" ("+GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaPajak()+")");
                } else {
                    paket = new Paket(null, LocalDate.now(), rbFast.isSelected()?"FAST":"SLOW", "WAITING FOR PAYMENT", Integer.parseInt(tfJarak.getText()), tfAlamat.getText());
                    listItem.add(item);
                    paket.setItem(listItem);
                    lTotalHarga.setText("Total harga: Rp."+paket.hargaBarang()+" ("+paket.hargaPajak()+")");
                }
                GUIMenuPengirim.loadData();
                showTable();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan\n"+ex.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tidak bisa menambah item\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Format salah atau belum diisi");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAlamatActionPerformed

    private void tfJarakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJarakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJarakActionPerformed

    private void tfAlamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlamatFocusGained
        // TODO add your handling code here:
        if (tfAlamat.getForeground()==Color.GRAY){
            tfAlamat.setText("");
            tfAlamat.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfAlamatFocusGained

    private void tfAlamatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlamatFocusLost
        // TODO add your handling code here:
        if (tfAlamat.getText().equals("")){
            tfAlamat.setText("Jalan Buah Batu No.02, RT 09 RW 06, Lengkong, Bojongsoang");
            tfAlamat.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfAlamatFocusLost

    private void tfJarakFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJarakFocusGained
        // TODO add your handling code here:
        if (tfJarak.getForeground()==Color.GRAY){
            tfJarak.setText("");
            tfJarak.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tfJarakFocusGained

    private void tfJarakFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfJarakFocusLost
        // TODO add your handling code here:
        if (tfJarak.getText().equals("")){
            tfJarak.setText("ex: 10");
            tfJarak.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tfJarakFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tfAlamat.getForeground()==Color.BLACK&&
            tfJarak.getForeground()==Color.BLACK&&
            jTable1.getModel().getRowCount()!=0){
            try {    
                if ("Edit Paket".equals(jLabel6.getText())){
                    paket = GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.jTable1.getSelectedRow());
                    paket.setTanggalDibuat(LocalDate.now());
                    paket.setTipe(rbFast.isSelected()?"FAST":"SLOW");
                    if (paket.getStatus().contains("WRONG")) paket.setStatus("WAITING");
                    else paket.setStatus("WAITING FOR PAYMENT");
                    paket.setJarak(Integer.parseInt(tfJarak.getText()));
                    paket.setAlamatTujuan(tfAlamat.getText());
                    DATA.updateDATA(paket);
                } else {
                    paket.setPengirim(GUIMenuPengirim.pengirim);
                    paket.setItem(listItem);
                    DATA.addDATA(paket);
                    paket = DATA.listPaket.get(DATA.listPaket.size()-1);
                    for (Item item : listItem) {
                        item.setPaket(paket);
                        DATA.addDATA(item);
                        DATA.loadDATA();
                    }
                    paket.setItem(listItem);
                    DATA.updateDATA(paket);
                }
                DATA.updateDATA(GUIMenuPengirim.pengirim);
                showTable();
                JOptionPane.showMessageDialog(this, "Paket Berhasil Ditambah");
                GUIMenuPengirim.showTable();
                dispose();
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
            } catch(HeadlessException | NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Format input salah atau belum di isi");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Alamat Jarak atau Item masih kosong atau salah");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            if ((jLabel6.getText().equals("Edit Paket")||jLabel6.getText().equals("Detail Paket"))){
                DATA.hapusDATA(GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).getItem().get(jTable1.getSelectedRow()));
                GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).getItem().remove(jTable1.getSelectedRow());
            } else {
                listItem.remove(jTable1.getSelectedRow());
            }
            lTotalHarga.setText("Total harga: Rp."+GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaBarang()+" ("+GUIMenuPengirim.pengirim.getPaket().get(GUIMenuPengirim.tableCount).hargaPajak()+")");
            JOptionPane.showMessageDialog(this, "Item berhasil dihapus");
            showTable();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Item tidak bisa dihapus");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rbBeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBeratActionPerformed
        // TODO add your handling code here:
        tfWeight.setEnabled(true);
        tfHeight.setEnabled(false);
        tfWidth.setEnabled(false);
        tfLength.setEnabled(false);
    }//GEN-LAST:event_rbBeratActionPerformed

    private void rbBeratDimensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBeratDimensiActionPerformed
        // TODO add your handling code here:
        tfHeight.setEnabled(true);
        tfWidth.setEnabled(true);
        tfLength.setEnabled(true);
        tfWeight.setEnabled(false);
    }//GEN-LAST:event_rbBeratDimensiActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if("Detail Paket".equals(jLabel6.getText())){
            getTable();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if("Detail Paket".equals(jLabel6.getText())){
            getTable();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void rbFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFastActionPerformed

    private void rbSlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSlowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSlowActionPerformed

    private void tfAlamatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAlamatKeyReleased
        // TODO add your handling code here:
        if (tfJarak.getForeground()==Color.BLACK&&!tfAlamat.getText().equals("")){
            tfItemName.setEnabled(true);
        } else {
            tfItemName.setEnabled(false);
        }
    }//GEN-LAST:event_tfAlamatKeyReleased

    private void tfJarakKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJarakKeyReleased
        // TODO add your handling code here:
        if (tfAlamat.getForeground()==Color.BLACK&&!tfJarak.getText().equals("")){
            tfItemName.setEnabled(true);
        } else {
             tfItemName.setEnabled(false);
        }
    }//GEN-LAST:event_tfJarakKeyReleased

    private void tfAlamatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfAlamatMouseReleased
        // TODO add your handling code here:
        if (tfJarak.getForeground()==Color.BLACK&&!tfAlamat.getText().equals("")){
            tfItemName.setEnabled(true);
        } else {
            tfItemName.setEnabled(false);
        }
    }//GEN-LAST:event_tfAlamatMouseReleased

    private void tfJarakMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfJarakMouseReleased
        // TODO add your handling code here:
        if (tfAlamat.getForeground()==Color.BLACK&&!tfJarak.getText().equals("")){
            tfItemName.setEnabled(true);
        } else {
             tfItemName.setEnabled(false);
        }
    }//GEN-LAST:event_tfJarakMouseReleased

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
//            java.util.logging.Logger.getLogger(DialogEditTambahPaketItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(() -> {
//            DialogEditTambahPaketItem dialog;
//            try {
//                dialog = new DialogEditTambahPaketItem(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            } catch (Exception ex) {
//                Logger.getLogger(DialogEditTambahPaketItem.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgBerat;
    private javax.swing.ButtonGroup bgTipePengiriman;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private javax.swing.JLabel lTotalHarga;
    private javax.swing.JRadioButton rbBerat;
    private javax.swing.JRadioButton rbBeratDimensi;
    private javax.swing.JRadioButton rbFast;
    private javax.swing.JRadioButton rbSlow;
    private javax.swing.JTextField tfAlamat;
    private javax.swing.JTextField tfHeight;
    private javax.swing.JTextField tfItemName;
    private javax.swing.JTextField tfJarak;
    private javax.swing.JTextField tfLength;
    private javax.swing.JTextField tfWeight;
    private javax.swing.JTextField tfWidth;
    // End of variables declaration//GEN-END:variables
}
