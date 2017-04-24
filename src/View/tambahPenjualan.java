/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.customerDAO;
import Controller.karyawanDAO;
import Controller.penjualanDAO;
import Controller.stokDAO;
import Model.Customer;
import Model.Detailpenjualan;
import Model.Karyawan;
import Model.Penjualan;
import Model.Stokdigudang;
import Model.Supplier;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class tambahPenjualan extends javax.swing.JPanel {

    /**
     * Creates new form tambahPenjualan
     */
    private penjualanDAO daoPenjualan;
    private customerDAO daoCustomer;
    private stokDAO daoStok;
    private karyawanDAO daoKaryawan;

    private DefaultTableModel dtmCart;
    private List<Detailpenjualan> listDetail;
    private List<Customer> listCustomer;
    private List<Karyawan> listKaryawan;
    private List<Stokdigudang> listStok;

    private DefaultComboBoxModel dcbmCustomer;
    private DefaultComboBoxModel dcbmKaryawan;
    private DefaultComboBoxModel dcbmProduk;

    private int noUrut;
    SpinnerNumberModel sm;

    public tambahPenjualan() {
        initComponents();
        daoPenjualan = new penjualanDAO();
        daoStok = new stokDAO();
        daoCustomer = new customerDAO();
        daoKaryawan = new karyawanDAO();

        dtmCart = (DefaultTableModel) tableCart.getModel();
        dcbmCustomer = new DefaultComboBoxModel();
        dcbmKaryawan = new DefaultComboBoxModel();
        dcbmProduk = new DefaultComboBoxModel();

        listDetail = new ArrayList<>();
        listCustomer = daoCustomer.getAllCustomers();
        listKaryawan = daoKaryawan.getAllKaryawans();
        listStok = daoStok.getStokNotEmpty();

        noUrut = 1;
        addToComboBox();
        panelDetail.setVisible(false);
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

            Detailpenjualan dp = new Detailpenjualan();
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
                Penjualan p = new Penjualan();
                Date d = new Date();

                p.setIDCustomer(daoCustomer.getCustomerById(Integer.parseInt(trimId(cbCustomer.getSelectedItem().toString()))).get(0));
                p.setIDKaryawan(daoKaryawan.getKaryawanById(Integer.parseInt(trimId(cbKaryawan.getSelectedItem().toString()))).get(0));

                //Ask for input for lunas
                int balasan = JOptionPane.showConfirmDialog(this, "Apakah sudah lunas?", "Status Lunas", JOptionPane.YES_NO_OPTION);
                if (balasan == JOptionPane.YES_OPTION) {
                    p.setLunas(true);
                    p.setTanggalPembayaran(d);
                } else {
                    p.setLunas(false);
                    labelPenjelasan.setText("Tanggal Penjualan");
                    panelDetail.setVisible(true);
                    JOptionPane.showMessageDialog(this, panelDetail, null, JOptionPane.OK_OPTION);

                    p.setTanggalPembayaran(lunasChooser.getDate());
                }

                p.setMetodePembayaran(metode);
                //System.out.println("");
                daoPenjualan.addOrUpdatePenjualan(p);
                for (Detailpenjualan od : listDetail) {
                    od.setIDOrder(p);
                    daoPenjualan.addOrUpdateDetailPenjualan(od);

                    //Update data stok produk (berkurang)
                    Stokdigudang s = daoStok.getStokById(od.getIDProduk().getIDBarang()).get(0);

                    s.setStok(s.getStok() - od.getJumlah());
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
    private void gantiSpinner(Stokdigudang p) {
        int batas = p.getStok();

        sm = new SpinnerNumberModel(1, 1, batas, 1);
        spJumlah.setModel(sm);
    }

    private void addToComboBox() {
        for (Customer k : listCustomer) {
            dcbmCustomer.addElement(k.getIDCustomer() + " - " + k.getNamaDepan() + " " + k.getNamaBelakang());
        }

        for (Karyawan k : listKaryawan) {
            dcbmKaryawan.addElement(k.getIDKaryawan() + " - " + k.getNamaDepan() + " " + k.getNamaBelakang());
        }
        for (Stokdigudang k : listStok) {
            dcbmProduk.addElement(k.getIDBarang() + " - " + k.getNamaBarang());
        }

        cbCustomer.setModel(dcbmCustomer);
        cbProduk.setModel(dcbmProduk);
        cbKaryawan.setModel(dcbmKaryawan);
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
        jPanel18 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        cbCustomer = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        rbKredit = new javax.swing.JRadioButton();
        rbDebit = new javax.swing.JRadioButton();
        rbTunai = new javax.swing.JRadioButton();
        jLabel58 = new javax.swing.JLabel();
        cbKaryawan = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        cbProduk = new javax.swing.JComboBox<>();
        tfHarga = new javax.swing.JTextField();
        spJumlah = new javax.swing.JSpinner();
        tfPotongan = new javax.swing.JTextField();
        tfSubTotal = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        panelDetail = new javax.swing.JPanel();
        labelPenjelasan = new javax.swing.JLabel();
        lunasChooser = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(255, 51, 51));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(240, 240, 240));
        jLabel50.setText("Customer :");

        cbCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Customer --" }));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(240, 240, 240));
        jLabel51.setText("Metode Pembayaran :");

        rbKredit.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbKredit);
        rbKredit.setForeground(new java.awt.Color(240, 240, 240));
        rbKredit.setText("KREDIT");
        rbKredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKreditActionPerformed(evt);
            }
        });

        rbDebit.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbDebit);
        rbDebit.setForeground(new java.awt.Color(240, 240, 240));
        rbDebit.setText("DEBIT");

        rbTunai.setBackground(new java.awt.Color(255, 51, 51));
        rbMetode.add(rbTunai);
        rbTunai.setForeground(new java.awt.Color(240, 240, 240));
        rbTunai.setText("TUNAI");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(240, 240, 240));
        jLabel58.setText("Karyawan :");

        cbKaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Karyawan --" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbKredit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(rbDebit)
                        .addGap(18, 18, 18)
                        .addComponent(rbTunai))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbKaryawan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(rbKredit)
                    .addComponent(rbDebit)
                    .addComponent(rbTunai))
                .addContainerGap())
        );

        add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 44, -1, -1));

        jPanel19.setBackground(new java.awt.Color(255, 51, 51));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Detail Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(240, 240, 240));
        jLabel52.setText("Produk :");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(240, 240, 240));
        jLabel53.setText("Harga :");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(240, 240, 240));
        jLabel54.setText("Jumlah :");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(240, 240, 240));
        jLabel55.setText("Potongan :");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(240, 240, 240));
        jLabel56.setText("Sub Total :");

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

        tfSubTotal.setEnabled(false);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)
                        .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(18, 18, 18)
                        .addComponent(tfHarga))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(spJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(tfPotongan))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(tfSubTotal)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(cbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(spJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55))
                    .addComponent(tfPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(240, 240, 240));
        jLabel57.setText("Tambah Penjualan");
        add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 11, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, -1));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Urut", "ID Produk", "Harga", "Jumlah", "Potongan Harga", "Sub Total"
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
        jScrollPane18.setViewportView(tableCart);

        add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 688, 119));

        btnClear.setText("Clear Order");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, 147, -1));

        btnConfirm.setText("Konfirmasi Penambahan");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        add(btnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, -1, -1));

        panelDetail.setBackground(new java.awt.Color(0, 51, 51));
        panelDetail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelDetailFocusGained(evt);
            }
        });
        panelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPenjelasan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPenjelasan.setForeground(new java.awt.Color(240, 240, 240));
        labelPenjelasan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelDetail.add(labelPenjelasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 630, 20));
        panelDetail.add(lunasChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 300, -1));

        add(panelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void rbKreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbKreditActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbProdukItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProdukItemStateChanged
        String selectedItem = trimId(cbProduk.getSelectedItem().toString());
        gantiSpinner(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0));
        
        spJumlah.setValue(1);
        tfHarga.setText(String.valueOf(daoStok.getStokById(Integer.parseInt(selectedItem)).get(0).getHarga()));
        tfSubTotal.setText(String.valueOf(Long.parseLong(tfHarga.getText()) * Long.parseLong(spJumlah.getValue().toString())));
    }//GEN-LAST:event_cbProdukItemStateChanged

    private void spJumlahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spJumlahStateChanged
        tfSubTotal.setText(String.valueOf(Long.parseLong(tfHarga.getText()) * Long.parseLong(spJumlah.getValue().toString())));
    }//GEN-LAST:event_spJumlahStateChanged

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        addToDb();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        refresh();
    }//GEN-LAST:event_btnClearActionPerformed

    private void panelDetailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelDetailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panelDetailFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cbCustomer;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JComboBox<String> cbProduk;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JLabel labelPenjelasan;
    private com.toedter.calendar.JDateChooser lunasChooser;
    private javax.swing.JPanel panelDetail;
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
