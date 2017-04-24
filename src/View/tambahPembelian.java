/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.pembelianDAO;
import Controller.stokDAO;
import Controller.supplierDAO;
import Model.Detailpembelian;
import Model.Pembelian;
import Model.Stokdigudang;
import Model.Supplier;
import com.mchange.v2.cmdline.ParsedCommandLine;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class tambahPembelian extends javax.swing.JPanel {

    /**
     * Creates new form tambahPembelian
     */
    private pembelianDAO daoPembelian;
    private stokDAO daoStok;
    private supplierDAO daoSupplier;

    private DefaultTableModel dtmCart;
    private List<Detailpembelian> listDetail;
    private List<Supplier> listSupplier;
    private List<Stokdigudang> listStok;

    private DefaultComboBoxModel dcbmSupplier;
    private DefaultComboBoxModel dcbmProduk;

    private int noUrut;
    SpinnerModel sm;

    public tambahPembelian() {
        initComponents();
        daoPembelian = new pembelianDAO();
        daoStok = new stokDAO();
        daoSupplier = new supplierDAO();

        dtmCart = (DefaultTableModel) tableCart.getModel();
        dcbmSupplier = new DefaultComboBoxModel();
        dcbmProduk = new DefaultComboBoxModel();

        listDetail = new ArrayList<>();
        listSupplier = daoSupplier.getAllSupplier();
        listStok = daoStok.getStokNotEmpty();

        noUrut = 1;
        addToComboBox();
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk memasukkan data ke tableCart
    private void add() {
        try {
            rbMetode = new ButtonGroup();
            rbMetode.add(rbKredit);
            rbMetode.add(rbDebit);
            rbMetode.add(rbTunai);

            String potongan = tfPotongan.getText();
            Integer.parseInt(potongan);
            tfSubTotal.setText(String.valueOf((Long.parseLong(tfHarga.getText()) * Long.parseLong(spJumlah.getValue().toString())) - Integer.parseInt(tfPotongan.getText())));

            //Add to table
            dtmCart.addRow(new Object[]{
                noUrut,
                cbProduk.getSelectedItem(),
                tfHarga.getText(),
                spJumlah.getValue(),
                potongan,
                tfSubTotal.getText()
            });

            //Add to list for use in addToDb
            String selectedItem = trimId(cbProduk.getSelectedItem().toString());

            Detailpembelian dp = new Detailpembelian();
            dp.setIDProduk(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0));
            dp.setHarga(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0).getHarga());
            dp.setJumlah(Integer.parseInt(spJumlah.getValue().toString()));
            dp.setPotonganHarga(Integer.parseInt(potongan));
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
                JOptionPane.showMessageDialog(this, "Order tidak dapat dibuat sebab tidak ada produk yang dibeli!");
            } else {
                String metode = getSelectedButtonText(rbMetode);
                Pembelian p = new Pembelian();
                Date d = new Date();
                p.setIDSupplier(daoSupplier.getSupplierById(Integer.parseInt(trimId(cbSupplier.getSelectedItem().toString()))).get(0));
                p.setMetodePembayaran(metode);
                p.setTanggalPembelian(d);

                daoPembelian.addOrUpdatePembelian(p);
                for (Detailpembelian od : listDetail) {
                    od.setIDOrder(p);
                    daoPembelian.addOrUpdateDetailPembelian(od);
                    
                    //Update data stok produk (bertambah)
                    Stokdigudang s = daoStok.getStokById(od.getIDProduk().getIDBarang()).get(0);
                    
                    s.setStok(s.getStok() + od.getJumlah());
                    daoStok.addOrUpdateStok(s);                  
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
        for (Supplier k : listSupplier) {
            dcbmSupplier.addElement(k);
        }

        for (Stokdigudang k : listStok) {
            dcbmProduk.addElement(k.getIDBarang() + " - " + k.getNamaBarang());
        }
        cbSupplier.setModel(dcbmSupplier);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbMetode = new javax.swing.ButtonGroup();
        jLabel34 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        cbSupplier = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        rbKredit = new javax.swing.JRadioButton();
        rbDebit = new javax.swing.JRadioButton();
        rbTunai = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        cbProduk = new javax.swing.JComboBox<>();
        tfHarga = new javax.swing.JTextField();
        spJumlah = new javax.swing.JSpinner();
        tfPotongan = new javax.swing.JTextField();
        tfSubTotal = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        btnConfirm = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 0, 0));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Tambah Pembelian");

        jPanel16.setBackground(new java.awt.Color(255, 51, 51));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(240, 240, 240));
        jLabel35.setText("Supplier :");

        cbSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Supplier --" }));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(240, 240, 240));
        jLabel36.setText("Metode Pembayaran :");

        rbKredit.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbKredit);
        rbKredit.setForeground(new java.awt.Color(240, 240, 240));
        rbKredit.setText("KREDIT");

        rbDebit.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbDebit);
        rbDebit.setForeground(new java.awt.Color(240, 240, 240));
        rbDebit.setText("DEBIT");

        rbTunai.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbTunai);
        rbTunai.setForeground(new java.awt.Color(240, 240, 240));
        rbTunai.setText("TUNAI");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(rbKredit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(rbDebit)
                        .addGap(18, 18, 18)
                        .addComponent(rbTunai)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(rbKredit)
                    .addComponent(rbDebit)
                    .addComponent(rbTunai))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 51, 51));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Detail Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Produk :");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(240, 240, 240));
        jLabel38.setText("Harga :");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(240, 240, 240));
        jLabel41.setText("Jumlah :");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(240, 240, 240));
        jLabel44.setText("Potongan :");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(240, 240, 240));
        jLabel49.setText("Sub Total :");

        cbProduk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Produk --" }));
        cbProduk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProdukItemStateChanged(evt);
            }
        });

        tfHarga.setEnabled(false);

        spJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spJumlah.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spJumlahStateChanged(evt);
            }
        });

        tfPotongan.setText("0");
        tfPotongan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfPotonganMouseExited(evt);
            }
        });
        tfPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPotonganActionPerformed(evt);
            }
        });
        tfPotongan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPotonganFocusLost(evt);
            }
        });
        tfPotongan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPotonganKeyTyped(evt);
            }
        });

        tfSubTotal.setEnabled(false);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(tfHarga))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(spJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(tfPotongan))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(18, 18, 18)
                        .addComponent(tfSubTotal)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(spJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44))
                    .addComponent(tfPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Urut", "Produk", "Harga", "Jumlah", "Potongan Harga", "Sub Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(tableCart);

        btnConfirm.setText("Konfirmasi Penambahan");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnClear.setText("Clear Order");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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
                                .addComponent(jLabel34)
                                .addGap(352, 352, 352))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(252, 252, 252))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(294, 294, 294))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnBack))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addGap(42, 42, 42)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbProdukItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProdukItemStateChanged
        String selectedItem = trimId(cbProduk.getSelectedItem().toString());

        spJumlah.setValue(1);
        tfHarga.setText(String.valueOf(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0).getHarga()));
        tfSubTotal.setText(String.valueOf(Long.parseLong(tfHarga.getText()) * Long.parseLong(spJumlah.getValue().toString())));
    }//GEN-LAST:event_cbProdukItemStateChanged

    private void spJumlahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spJumlahStateChanged
        // TODO add your handling code here:
        tfSubTotal.setText(String.valueOf(Long.parseLong(tfHarga.getText()) * Long.parseLong(spJumlah.getValue().toString())));
    }//GEN-LAST:event_spJumlahStateChanged

    private void tfPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPotonganActionPerformed

    }//GEN-LAST:event_tfPotonganActionPerformed

    private void tfPotonganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPotonganKeyTyped

    }//GEN-LAST:event_tfPotonganKeyTyped

    private void tfPotonganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPotonganMouseExited

    }//GEN-LAST:event_tfPotonganMouseExited

    private void tfPotonganFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPotonganFocusLost

    }//GEN-LAST:event_tfPotonganFocusLost

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        refresh();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        addToDb();
    }//GEN-LAST:event_btnConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cbProduk;
    private javax.swing.JComboBox<String> cbSupplier;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JRadioButton rbDebit;
    private javax.swing.JRadioButton rbKredit;
    private javax.swing.ButtonGroup rbMetode;
    private javax.swing.JRadioButton rbTunai;
    private javax.swing.JSpinner spJumlah;
    private javax.swing.JTable tableCart;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfPotongan;
    private javax.swing.JTextField tfSubTotal;
    // End of variables declaration//GEN-END:variables
}
