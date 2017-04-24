/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.pembelianDAO;
import Controller.supplierDAO;
import Model.Pembelian;
import Model.Supplier;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class supplier extends javax.swing.JPanel implements ListSelectionListener {

    /**
     * Creates new form supplier
     */
    private supplierDAO daoSup;
    private pembelianDAO daoPembelian;
    private DefaultTableModel dtmSupplier;
    private DefaultTableModel dtmDetail;

    private List<Supplier> listSupplier;

    int row;

    public supplier() {
        initComponents();
        daoSup = new supplierDAO();
        daoPembelian = new pembelianDAO();
        dtmSupplier = (DefaultTableModel) tabelSupplier.getModel();
        dtmDetail = (DefaultTableModel) tableDetail.getModel();

        listSupplier = daoSup.getAllSupplier();
        panelDetail.setVisible(false);
        showAllData();
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk menampilkan seluruh data
    private void showAllData() {
        tabelSupplier.getSelectionModel().removeListSelectionListener(this);
        listSupplier = daoSup.getAllSupplier();
        dtmSupplier.getDataVector().removeAllElements();
        for (Supplier s : listSupplier) {
            dtmSupplier.addRow(new Object[]{
                s.getIDSupplier(),
                s.getNamaPerusahaan(),
                s.getNamaDepan(),
                s.getNamaBelakang(),
                s.getAlamat(),
                s.getNoTelepon(),
                s.getCatatan(),
                s.getTanggalMulai()
            });
        }
        tabelSupplier.getSelectionModel().addListSelectionListener(this);
    }

    //Untuk menghapus data
    private void deleteData() {
        try {
            Supplier s = new Supplier();

            //Untuk membedakan antara update dan add
            if (tabelSupplier.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada data dipilih!");
                throw new Exception();
            }
            s.setIDSupplier(Integer.parseInt(tfID.getText()));
            s.setNamaDepan(tfDepan.getText());
            s.setNamaBelakang(tfBelakang.getText());
            s.setAlamat(taAlamat.getText());
            s.setNamaPerusahaan(tfPerusahaan.getText());
            s.setNoTelepon(Integer.parseInt(tfTelepon.getText()));
            s.setCatatan(taCatatan.getText());
            s.setTanggalMulai(dateChooser.getDate());

            daoSup.deleteSupplier(s);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            showAllData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
        }
    }
    //Untuk memasukkan atau mengupdate data

    private void addOrUpdate() {
        try {
            Supplier s = new Supplier();
            if (!tfDepan.getText().equals("")) {
                s.setNamaDepan(tfDepan.getText());
            } else {
                throw new Exception();
            }

            //Untuk membedakan antara update dan add
            if (!tabelSupplier.getSelectionModel().isSelectionEmpty()) {
                s.setIDSupplier(listSupplier.get(row).getIDSupplier());
            } else {
                dateChooser.setEnabled(true);
            }

            s.setNamaBelakang(tfBelakang.getText());
            s.setAlamat(taAlamat.getText());
            s.setNamaPerusahaan(tfPerusahaan.getText());
            s.setNoTelepon(Integer.parseInt(tfTelepon.getText()));
            s.setCatatan(taCatatan.getText());
            s.setTanggalMulai(dateChooser.getDate());

            daoSup.addOrUpdateSupplier(s);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            showAllData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
        }
    }

    private void showDetailPembelian() {
        Supplier pilihan = listSupplier.get(row);
        dtmDetail.getDataVector().removeAllElements();
        if (!tabelSupplier.getSelectionModel().isSelectionEmpty()) {
            List<Pembelian> listPenjualan = daoPembelian.getPembelianBySupplier(pilihan.getIDSupplier());
            for (Pembelian p : listPenjualan) {
                dtmDetail.addRow(new Object[]{
                    p.getIDOrder(),
                    p.getIDSupplier(),
                    p.getIDSupplier().getNamaDepan() + " " + p.getIDSupplier().getNamaBelakang(),
                    p.getIDSupplier().getNamaPerusahaan(),
                    p.getMetodePembayaran(),
                    p.getTanggalPembelian()
                });
            }
            labelPenjelasan.setText("Pembelian - pembelian dari supplier dengan ID : " + pilihan.getIDSupplier()
                    + " dan dengan nama perusahaan " + pilihan.getNamaPerusahaan()
                    + " adalah : ");
            panelDetail.setVisible(true);
            JOptionPane.showMessageDialog(this, panelDetail, null, JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada karyawan yang dipilih!");
        }
    }

    /*
    ---------
    End of methods 
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == tabelSupplier.getSelectionModel()) {
            row = tabelSupplier.getSelectedRow();
        }

        dateChooser.setEnabled(false);
        tfID.setText(String.valueOf(listSupplier.get(row).getIDSupplier()));
        tfDepan.setText(listSupplier.get(row).getNamaDepan());
        tfBelakang.setText(listSupplier.get(row).getNamaBelakang());
        tfPerusahaan.setText(listSupplier.get(row).getNamaPerusahaan());
        taAlamat.setText(listSupplier.get(row).getAlamat());
        tfTelepon.setText(String.valueOf(listSupplier.get(row).getNoTelepon()));
        taCatatan.setText(listSupplier.get(row).getCatatan());
        dateChooser.setDate(listSupplier.get(row).getTanggalMulai());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel39 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfBelakang = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        tfTelepon = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane21 = new javax.swing.JScrollPane();
        taCatatan = new javax.swing.JTextArea();
        jLabel46 = new javax.swing.JLabel();
        btnDetail = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        tfDepan = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        tfPerusahaan = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        panelDetail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        labelPenjelasan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(240, 240, 240));
        jLabel39.setText("Supplier");
        add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 11, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 638, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add / Update Data Supplier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(240, 240, 240));
        jLabel40.setText("Nama Kontak :");

        tfID.setToolTipText("Nama Depan");
        tfID.setEnabled(false);

        tfBelakang.setToolTipText("Nama Belakang");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(240, 240, 240));
        jLabel42.setText("Alamat :");

        taAlamat.setColumns(20);
        taAlamat.setLineWrap(true);
        taAlamat.setRows(3);
        jScrollPane20.setViewportView(taAlamat);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(240, 240, 240));
        jLabel43.setText("Nomor Telepon :");

        tfTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTeleponActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(240, 240, 240));
        jLabel45.setText("Tanggal Mulai :");

        taCatatan.setColumns(20);
        taCatatan.setLineWrap(true);
        taCatatan.setRows(3);
        jScrollPane21.setViewportView(taCatatan);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(240, 240, 240));
        jLabel46.setText("Catatan :");

        btnDetail.setText("Detail Pengiriman");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(240, 240, 240));
        jLabel47.setText("ID Supplier :");

        tfDepan.setToolTipText("Nama Depan");

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        tfPerusahaan.setToolTipText("Nama Depan");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(240, 240, 240));
        jLabel48.setText("Nama Perusahaan :");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(tfDepan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBelakang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addComponent(tfPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBelakang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(tfDepan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(tfPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 339, -1, -1));

        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Supplier", "Nama Perusahaan", "Nama Depan", "Nama Belakang", "Alamat", "No Telepon", "Catatan", "Tanggal Mulai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane19.setViewportView(tabelSupplier);

        add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 921, 277));

        panelDetail.setBackground(new java.awt.Color(0, 51, 51));
        panelDetail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelDetailFocusGained(evt);
            }
        });
        panelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Order", "ID Customer", "Nama Lengkap", "Nama Perusahaan", "Metode Pembayaran", "Tanggal Pembayaran"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tableDetail.setMinimumSize(new java.awt.Dimension(150, 100));
        jScrollPane1.setViewportView(tableDetail);

        panelDetail.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 660, -1));

        labelPenjelasan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPenjelasan.setForeground(new java.awt.Color(240, 240, 240));
        labelPenjelasan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelDetail.add(labelPenjelasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 630, 20));

        add(panelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        deleteData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tfTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTeleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTeleponActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addOrUpdate();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        showDetailPembelian();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void panelDetailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelDetailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panelDetailFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JLabel labelPenjelasan;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextArea taCatatan;
    private javax.swing.JTable tabelSupplier;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTextField tfBelakang;
    private javax.swing.JTextField tfDepan;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfPerusahaan;
    private javax.swing.JTextField tfTelepon;
    // End of variables declaration//GEN-END:variables

}
