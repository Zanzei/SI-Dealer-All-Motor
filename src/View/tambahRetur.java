/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.customerDAO;
import Controller.penjualanDAO;
import Controller.returDAO;
import Controller.stokDAO;
import Model.Detailpenjualan;
import Model.Detailretur;
import Model.Penjualan;
import Model.Retur;
import Model.Stokdigudang;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class tambahRetur extends javax.swing.JPanel {

    /**
     * Creates new form tambahRetur
     */
    private returDAO daoRetur;
    private DefaultTableModel dtmCart;
    private stokDAO daoStok;
    private customerDAO daoCustomer;
    private penjualanDAO daoPenjualan;
    private DefaultComboBoxModel dcbmProduk;

    private int noUrut;
    private List<Stokdigudang> listStok;
    private List<Detailretur> listDetail;

    public tambahRetur() {
        initComponents();
        daoRetur = new returDAO();
        daoPenjualan = new penjualanDAO();
        daoStok = new stokDAO();
        daoCustomer = new customerDAO();

        listStok = new ArrayList<>();
        listDetail = new ArrayList<>();
        dcbmProduk = new DefaultComboBoxModel();
        dtmCart = (DefaultTableModel) tableCart.getModel();

        noUrut = 1;
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk memasukkan data ke tableCart
    private void add() {
        try {
            rbDisetujui = new ButtonGroup();
            rbDisetujui.add(rbYa);
            rbDisetujui.add(rbTidak);

            //Add to list for use in addToDb
            String selectedItem = trimId(cbProduk.getSelectedItem().toString());
            Detailretur dp = new Detailretur();
            dp.setIDProduk(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0));
            dp.setKeterangan(taKeterangan.getText());
            
            String disetujui = getSelectedButtonText(rbDisetujui);    
            boolean setuju = true;
            if (disetujui.equals("Ya")) {
                dp.setDisetujui(true);
            } else {
                dp.setDisetujui(false);
                setuju = false;
            }
            
            //Add to table
            dtmCart.addRow(new Object[]{
                noUrut,
                cbProduk.getSelectedItem(),
                tfHarga.getText(),
                taKeterangan.getText(),
                setuju
            });

            listDetail.add(dp);
            noUrut++;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal add order!");
        }
    }

    //Untuk memasukkan data dari tableCart ke database
    private void addToDb() {
        try {
            if (dtmCart.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Retur tidak dapat dibuat sebab tidak data yang dimasukkan!");
            } else {
                Retur p = new Retur();
                Penjualan order = daoPenjualan.getPenjualanById(Integer.parseInt(tfID.getText())).get(0);

                p.setIDOrder(order);
                p.setIDCustomer(order.getIDCustomer().getIDCustomer());

                daoRetur.addOrUpdateRetur(p);
                for (Detailretur od : listDetail) {
                    od.setIDRetur(p);
                    daoRetur.addOrUpdateDetailRetur(od);
                }
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                refresh();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
        }
    }

    //Untuk mereset isi dari tableCart
    private void refresh() {
        dtmCart.getDataVector().removeAllElements();
        tableCart.repaint();
        listDetail.removeAll(listDetail);
        noUrut = 1;
    }

    /*
    ---------
    End of methods 
     */
    private void addToComboBox() {
        dcbmProduk.removeAllElements();
        for (Stokdigudang k : listStok) {
            dcbmProduk.addElement(k.getIDBarang() + " - " + k.getNamaBarang());
        }
        listStok.clear();

        cbProduk.setModel(dcbmProduk);
    }

    private String trimId(String selectedItem) {
        selectedItem = selectedItem.substring(0, selectedItem.indexOf("-"));
        selectedItem = selectedItem.trim();
        return selectedItem;
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private void konfirmasi() {
        try {
            daoPenjualan.getPenjualanById(Integer.parseInt(tfID.getText()));
            JOptionPane.showMessageDialog(this, "ID Order yang dimasukkan ada!");

            List<Detailpenjualan> listDetailTemp = daoPenjualan.getDetailPenjualan(Integer.parseInt(tfID.getText()));

            for (Detailpenjualan dp : listDetailTemp) {
                listStok.add(dp.getIDProduk());
            }
            addToComboBox();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ID Order yang dimasukkan tidak ada!");
            tfID.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbDisetujui = new javax.swing.ButtonGroup();
        jPanel20 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cbProduk = new javax.swing.JComboBox<>();
        tfHarga = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        rbTidak = new javax.swing.JRadioButton();
        rbYa = new javax.swing.JRadioButton();
        jScrollPane23 = new javax.swing.JScrollPane();
        taKeterangan = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        jButton52 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 0, 0));

        jPanel20.setBackground(new java.awt.Color(255, 51, 51));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Retur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(240, 240, 240));
        jLabel59.setText("Order ID :");

        btnConfirm.setText("Konfirmasi ID");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addGap(22, 22, 22))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConfirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 51, 51));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Detail Retur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(240, 240, 240));
        jLabel62.setText("Produk :");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(240, 240, 240));
        jLabel63.setText("Harga :");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(240, 240, 240));
        jLabel65.setText("Keterangan :");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(240, 240, 240));
        jLabel66.setText("Disetujui?");

        cbProduk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Masukkan ID dahulu --" }));
        cbProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbProdukMouseExited(evt);
            }
        });
        cbProduk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProdukItemStateChanged(evt);
            }
        });
        cbProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProdukActionPerformed(evt);
            }
        });

        tfHarga.setEnabled(false);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        rbTidak.setBackground(new java.awt.Color(255, 51, 51));
        rbDisetujui.add(rbTidak);
        rbTidak.setForeground(new java.awt.Color(240, 240, 240));
        rbTidak.setText("Tidak");

        rbYa.setBackground(new java.awt.Color(255, 51, 51));
        rbDisetujui.add(rbYa);
        rbYa.setForeground(new java.awt.Color(240, 240, 240));
        rbYa.setText("Ya");

        taKeterangan.setColumns(20);
        taKeterangan.setLineWrap(true);
        taKeterangan.setRows(3);
        jScrollPane23.setViewportView(taKeterangan);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbYa)
                                .addGap(59, 59, 59)
                                .addComponent(rbTidak))
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(jLabel62)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(jLabel63)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfHarga)))
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel62))
                    .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel65)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(rbTidak)
                    .addComponent(rbYa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(240, 240, 240));
        jLabel67.setText("Tambah Retur");

        jButton52.setText("Back");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Urut", "ID Produk", "Harga", "Keterangan", "Disetujui?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane22.setViewportView(tableCart);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTambah.setText("Konfirmasi Penambahan");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(352, 352, 352))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(252, 252, 252))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton52))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addGap(42, 42, 42)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton52)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        refresh();
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbProdukItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProdukItemStateChanged

    }//GEN-LAST:event_cbProdukItemStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        addToDb();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        konfirmasi();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void cbProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProdukActionPerformed
        try {
            if (dcbmProduk.getSize() < 1) {
                dcbmProduk.addElement("-- Masukkan ID dahulu --");
            } else {
                String selectedItem = trimId(cbProduk.getSelectedItem().toString());
                tfHarga.setText(String.valueOf(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0).getHarga()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ID Order yang dimasukkan tidak ada!");
        }
    }//GEN-LAST:event_cbProdukActionPerformed

    private void cbProdukMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbProdukMouseExited

    }//GEN-LAST:event_cbProdukMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbProduk;
    private javax.swing.JButton jButton52;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.ButtonGroup rbDisetujui;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.JRadioButton rbYa;
    private javax.swing.JTextArea taKeterangan;
    private javax.swing.JTable tableCart;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfID;
    // End of variables declaration//GEN-END:variables
}
