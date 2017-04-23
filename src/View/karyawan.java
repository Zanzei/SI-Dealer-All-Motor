/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.gajiDAO;
import Controller.karyawanDAO;
import Controller.penjualanDAO;
import Model.Gaji;
import Model.Karyawan;
import Model.Kategorimotor;
import Model.Penjualan;
import Model.Supplier;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class karyawan extends javax.swing.JPanel implements ListSelectionListener {

    /**
     * Creates new form karyawan
     */
    private karyawanDAO daoKaryawan;
    private gajiDAO daoJabatan;
    private penjualanDAO daoPenjualan;

    private List<Karyawan> listKaryawan;
    private List<Gaji> listJabatan;
    private List<Karyawan> listSpv;
    private DefaultTableModel dtmKaryawan;
    private DefaultTableModel dtmDetail;
    private DefaultComboBoxModel dcbmJabatan;
    private DefaultComboBoxModel dcbmSupervisor;

    int row;

    public karyawan() {
        initComponents();
        daoKaryawan = new karyawanDAO();
        daoJabatan = new gajiDAO();
        daoPenjualan = new penjualanDAO();
        dtmKaryawan = (DefaultTableModel) tableKaryawan.getModel();
        dtmDetail = (DefaultTableModel) tableDetail.getModel();
        dcbmJabatan = new DefaultComboBoxModel();
        dcbmSupervisor = new DefaultComboBoxModel();

        listJabatan = daoJabatan.getAllGaji();
        listSpv = daoKaryawan.getKaryawanSpv();

        panelDetail.setVisible(false);
        showAllData();
        addToComboBox();
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk menampilkan seluruh data
    private void showAllData() {
        tableKaryawan.getSelectionModel().removeListSelectionListener(this);
        listKaryawan = daoKaryawan.getAllKaryawans();
        dtmKaryawan.getDataVector().removeAllElements();
        for (Karyawan s : listKaryawan) {
            dtmKaryawan.addRow(new Object[]{
                s.getIDKaryawan(),
                s.getNamaDepan(),
                s.getNamaBelakang(),
                s.getJabatan(),
                s.getJabatan().getGaji(),
                s.getAlamat(),
                s.getNoTelepon(),
                s.getCatatan(),
                s.getSupervisor(),
                s.getTanggalMulai()
            });
        }
        tableKaryawan.getSelectionModel().addListSelectionListener(this);
    }

    //Untuk menghapus data
    private void deleteData() {
        try {
            if (tableKaryawan.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada data dipilih!");
                throw new Exception();
            }

            Karyawan s = new Karyawan();
            s.setIDKaryawan(listKaryawan.get(row).getIDKaryawan());
            s.setNamaDepan(tfDepan.getText());
            s.setNamaBelakang(tfBelakang.getText());
            s.setJabatan((Gaji) cbJabatan.getSelectedItem());
            s.setAlamat(taAlamat.getText());
            s.setNoTelepon(tfTelepon.getText());
            s.setCatatan(taCatatan.getText());
            s.setPassword(listKaryawan.get(row).getPassword());
            s.setTanggalMulai(dateChooser.getDate());
            s.setSupervisor(Integer.parseInt(cbSupervisor.getSelectedItem().toString().substring(0, 1)));

            daoKaryawan.deleteKaryawan(s);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            showAllData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
        }
    }

    //Untuk memasukkan atau mengupdate data
    private void addOrUpdate() {
        try {
            Karyawan s = new Karyawan();
            String password = "";
            if (!tfDepan.getText().equals("")) {
                s.setNamaDepan(tfDepan.getText());
            } else {
                throw new Exception();
            }

            if (!tableKaryawan.getSelectionModel().isSelectionEmpty()) {
                s.setIDKaryawan(listKaryawan.get(row).getIDKaryawan());
                password = listKaryawan.get(row).getPassword();
            } else {
                dateChooser.setEnabled(true);
                password = JOptionPane.showInputDialog(this, "Masukkan password untuk karyawan baru :");
            }

            s.setNamaBelakang(tfBelakang.getText());
            s.setJabatan((Gaji) cbJabatan.getSelectedItem());
            s.setAlamat(taAlamat.getText());
            s.setNoTelepon(tfTelepon.getText());
            s.setCatatan(taCatatan.getText());
            s.setPassword(password);
            s.setTanggalMulai(dateChooser.getDate());
            s.setSupervisor(Integer.parseInt(cbSupervisor.getSelectedItem().toString().substring(0, 1)));

            daoKaryawan.addOrUpdateKaryawan(s);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            showAllData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
        }
    }

    //Untuk menampilkan data gaji
    private void showGaji() {
        Karyawan pilihan = listKaryawan.get(row);
        if (!tableKaryawan.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Karyawan bernama : " + pilihan.getNamaDepan() + " " + pilihan.getNamaBelakang()
                    + "\nJabatan       : " + pilihan.getJabatan()
                    + "\nGaji          : Rp." + pilihan.getJabatan().getGaji());
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada karyawan yang dipilih!");
        }
    }

    private void showDetailPenjualan() {
        Karyawan pilihan = listKaryawan.get(row);
        dtmDetail.getDataVector().removeAllElements();
        if (!tableKaryawan.getSelectionModel().isSelectionEmpty()) {
            List<Penjualan> listPenjualan = daoPenjualan.getPenjualanByKaryawan(pilihan.getIDKaryawan());
            for (Penjualan p : listPenjualan) {
                String perusahaan = p.getIDCustomer().getNamaPerusahaan();
                if (p.getIDCustomer().getNamaPerusahaan() == null) {
                    perusahaan = "-- Tidak Ada --";
                }

                String lunas = "Belum Lunas";
                if (p.getLunas()) {
                    lunas = "Sudah Lunas";
                }

                dtmDetail.addRow(new Object[]{
                    p.getIDOrder(),
                    p.getIDCustomer(),
                    p.getIDCustomer().getNamaDepan()+" "+p.getIDCustomer().getNamaBelakang(),
                    perusahaan,
                    p.getMetodePembayaran(),
                    p.getTanggalPembayaran(),
                    lunas
                });
            }
            labelPenjelasan.setText("Penjualan - penjualan oleh karyawan dengan ID : " + pilihan.getIDKaryawan()
                    + " dan dengan nama lengkap " + pilihan.getNamaDepan() + " " + pilihan.getNamaBelakang()
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
    private void addToComboBox() {
        for (Gaji k : listJabatan) {
            dcbmJabatan.addElement(k);
        }
        Karyawan b = new Karyawan(0, "-- Tidak Ada --", "");
        listSpv.add(0, b);

        for (Karyawan s : listSpv) {
            dcbmSupervisor.addElement(s.getIDKaryawan() + " - " + s.getNamaDepan() + " " + s.getNamaBelakang());
        }

        cbJabatan.setModel(dcbmJabatan);
        cbSupervisor.setModel(dcbmSupervisor);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == tableKaryawan.getSelectionModel()) {
            row = tableKaryawan.getSelectedRow();
        }

        dateChooser.setEnabled(false);
        tfID.setText(String.valueOf(listKaryawan.get(row).getIDKaryawan()));
        tfDepan.setText(listKaryawan.get(row).getNamaDepan());
        tfBelakang.setText(listKaryawan.get(row).getNamaBelakang());
        cbJabatan.setSelectedItem(listKaryawan.get(row).getJabatan());
        taAlamat.setText(listKaryawan.get(row).getAlamat());
        tfTelepon.setText(listKaryawan.get(row).getNoTelepon());
        taCatatan.setText(listKaryawan.get(row).getCatatan());
        dateChooser.setDate(listKaryawan.get(row).getTanggalMulai());

        List<Karyawan> spv = daoKaryawan.getKaryawanById(listKaryawan.get(row).getSupervisor());

        if (listKaryawan.get(row).getSupervisor() != 0) {
            cbSupervisor.setSelectedItem(spv.get(0).getIDKaryawan() + " - " + spv.get(0).getNamaDepan() + " " + spv.get(0).getNamaBelakang());
        } else {
            cbSupervisor.setSelectedItem(listSpv.get(0).getIDKaryawan() + " - " + listSpv.get(0).getNamaDepan() + " " + listSpv.get(0).getNamaBelakang());
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfBelakang = new javax.swing.JTextField();
        cbJabatan = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        tfTelepon = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        cbSupervisor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCatatan = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnGaji = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tfDepan = new javax.swing.JTextField();
        btnDetail = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnBack = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableKaryawan = new javax.swing.JTable();
        panelDetail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        labelPenjelasan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Karyawan");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 9, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add / Update Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(240, 240, 240))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Nama :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 59, 91, -1));

        tfID.setToolTipText("Nama Depan");
        tfID.setEnabled(false);
        jPanel1.add(tfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 27, 127, -1));

        tfBelakang.setToolTipText("Nama Belakang");
        jPanel1.add(tfBelakang, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 56, 116, -1));

        cbJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Jabatan --", " " }));
        jPanel1.add(cbJabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 87, 127, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Jabatan :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 90, 91, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Alamat :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 118, 91, -1));

        taAlamat.setColumns(20);
        taAlamat.setLineWrap(true);
        taAlamat.setRows(3);
        jScrollPane3.setViewportView(taAlamat);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 118, 238, 65));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("Nomor Telepon :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 204, -1, -1));

        tfTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTeleponActionPerformed(evt);
            }
        });
        jPanel1.add(tfTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 201, 147, -1));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 90, 49));

        cbSupervisor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Ada", " " }));
        jPanel1.add(cbSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 127, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Supervisor :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 91, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Tanggal Mulai :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 112, 91, -1));

        taCatatan.setColumns(20);
        taCatatan.setLineWrap(true);
        taCatatan.setRows(3);
        jScrollPane2.setViewportView(taCatatan);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 27, 238, 65));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("Catatan :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 30, 91, -1));

        btnGaji.setText("Penggajian");
        btnGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGajiActionPerformed(evt);
            }
        });
        jPanel1.add(btnGaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 126, 49));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("ID Karyawan :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, 91, -1));

        tfDepan.setToolTipText("Nama Depan");
        jPanel1.add(tfDepan, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 56, 127, -1));

        btnDetail.setText("Detail Penjualan");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 126, 49));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 80, 50));
        jPanel1.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 140, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 349, 850, 260));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, -1, -1));

        tableKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Karyawan", "Nama Depan", "Nama Belakang", "Jabatan", "Gaji", "Alamat", "No Telepon", "Catatan", "Supervisor", "Tanggal Mulai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tableKaryawan);

        add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, 892, 277));

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
                "ID Order", "ID Customer", "Nama Lengkap", "Nama Perusahaan", "Metode Pembayaran", "Tanggal Pembayaran", "Lunas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

    private void tfTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTeleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTeleponActionPerformed
    /*
    ------
    Start of button controls
    ------
     */
    private void btnGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGajiActionPerformed
        showGaji();
    }//GEN-LAST:event_btnGajiActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        showDetailPenjualan();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addOrUpdate();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        deleteData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void panelDetailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelDetailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panelDetailFocusGained
    /*
    ------
    End of button controls
    ------
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnGaji;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbJabatan;
    private javax.swing.JComboBox<String> cbSupervisor;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel labelPenjelasan;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextArea taCatatan;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTable tableKaryawan;
    private javax.swing.JTextField tfBelakang;
    private javax.swing.JTextField tfDepan;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfTelepon;
    // End of variables declaration//GEN-END:variables

}
