/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.customerDAO;
import Controller.penjualanDAO;
import Model.Customer;
import Model.Pembelian;
import Model.Penjualan;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRE
 */
public class customer extends javax.swing.JPanel implements ListSelectionListener {

    /**
     * Creates new form customer
     */
    private customerDAO daoCus;
    private penjualanDAO daoPenjualan;
    private DefaultTableModel dtmCustomer;
    private List<Customer> listCustomer;

    private DefaultTableModel dtmDetail;

    int row;

    public customer() {
        initComponents();
        daoCus = new customerDAO();
        daoPenjualan = new penjualanDAO();

        dtmDetail = (DefaultTableModel) tableDetail.getModel();
        dtmCustomer = (DefaultTableModel) tabelCustomer.getModel();
        listCustomer = daoCus.getAllCustomers();
        
        panelDetail.setVisible(false);
        showAllData();
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk menampilkan seluruh data
    private void showAllData() {
        tabelCustomer.getSelectionModel().removeListSelectionListener(this);
        listCustomer = daoCus.getAllCustomers();
        dtmCustomer.getDataVector().removeAllElements();
        for (Customer s : listCustomer) {
            dtmCustomer.addRow(new Object[]{
                s.getIDCustomer(),
                setNamaPerusahaan(s),
                s.getNamaDepan(),
                s.getNamaBelakang(),
                s.getAlamat(),
                s.getNoTelepon(),
                s.getCatatan(),
                s.getTanggalMulai()
            });
        }
        tabelCustomer.getSelectionModel().addListSelectionListener(this);
    }

    //Untuk menghapus data
    private void deleteData() {
        try {
            Customer s = new Customer();

            //Untuk membedakan antara update dan add
            if (tabelCustomer.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada data dipilih!");
                throw new Exception();
            }
            s.setIDCustomer(Integer.parseInt(tfID.getText()));
            s.setNamaDepan(tfDepan.getText());
            s.setNamaBelakang(tfBelakang.getText());
            s.setAlamat(taAlamat.getText());
            s.setNamaPerusahaan(tfPerusahaan.getText());
            s.setNoTelepon(tfTelepon.getText());
            s.setCatatan(taCatatan.getText());
            s.setTanggalMulai(dateChooser.getDate());

            daoCus.deleteCustomer(s);
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
            Customer s = new Customer();
            if (!tfDepan.getText().equals("")) {
                s.setNamaDepan(tfDepan.getText());
            } else {
                throw new Exception();
            }

            //Untuk membedakan antara update dan add
            if (!tabelCustomer.getSelectionModel().isSelectionEmpty()) {
                s.setIDCustomer(listCustomer.get(row).getIDCustomer());
            } else {
                dateChooser.setEnabled(true);
            }

            s.setNamaBelakang(tfBelakang.getText());
            s.setAlamat(taAlamat.getText());
            s.setNamaPerusahaan(tfPerusahaan.getText());
            s.setNoTelepon(tfTelepon.getText());
            s.setCatatan(taCatatan.getText());
            s.setTanggalMulai(dateChooser.getDate());

            daoCus.addOrUpdateCustomer(s);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            showAllData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
        }
    }

    private void showDetailPembelian() {
        Customer pilihan = listCustomer.get(row);
        dtmDetail.getDataVector().removeAllElements();
        if (!tabelCustomer.getSelectionModel().isSelectionEmpty()) {
            List<Penjualan> listPenjualan = daoPenjualan.getPenjualanByCustomer(pilihan.getIDCustomer());
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
                    p.getIDCustomer().getNamaDepan() + " " + p.getIDCustomer().getNamaBelakang(),
                    perusahaan,
                    p.getMetodePembayaran(),
                    p.getTanggalPembayaran(),
                    lunas
                });
            }
            labelPenjelasan.setText("Pembelian - pembelian oleh customer dengan ID : " + pilihan.getIDCustomer()
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
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == tabelCustomer.getSelectionModel()) {
            row = tabelCustomer.getSelectedRow();
        }

        dateChooser.setEnabled(false);
        tfID.setText(String.valueOf(listCustomer.get(row).getIDCustomer()));
        tfDepan.setText(listCustomer.get(row).getNamaDepan());
        tfBelakang.setText(listCustomer.get(row).getNamaBelakang());
        tfPerusahaan.setText(setNamaPerusahaan(listCustomer.get(row)));
        taAlamat.setText(listCustomer.get(row).getAlamat());
        tfTelepon.setText(listCustomer.get(row).getNoTelepon());
        taCatatan.setText(listCustomer.get(row).getCatatan());
        dateChooser.setDate(listCustomer.get(row).getTanggalMulai());
    }

    private String setNamaPerusahaan(Customer s) {
        if (s.getNamaPerusahaan() == null) {
            return "-- Tidak Ada --";
        }
        return s.getNamaPerusahaan();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane13 = new javax.swing.JScrollPane();
        tabelCustomer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfBelakang = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        tfTelepon = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane15 = new javax.swing.JScrollPane();
        taCatatan = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        btnDetail = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        tfPerusahaan = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfDepan = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        panelDetail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        labelPenjelasan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Customer", "Nama Perusahaan", "Nama Depan", "Nama Belakang", "Alamat", "No Telepon", "Catatan", "Tanggal Mulai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane13.setViewportView(tabelCustomer);

        add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 62, 932, 277));

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add / Update Data Customer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(240, 240, 240));
        jLabel23.setText("Nama Perusahaan :");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 93, -1, -1));

        tfID.setToolTipText("Nama Depan");
        tfID.setEnabled(false);
        jPanel2.add(tfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 27, 127, -1));

        tfBelakang.setToolTipText("Nama Belakang");
        jPanel2.add(tfBelakang, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 64, 116, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(240, 240, 240));
        jLabel25.setText("Alamat :");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 128, -1, -1));

        taAlamat.setColumns(20);
        taAlamat.setLineWrap(true);
        taAlamat.setRows(3);
        jScrollPane14.setViewportView(taAlamat);

        jPanel2.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 128, 238, 65));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(240, 240, 240));
        jLabel26.setText("Nomor Telepon :");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 202, -1, -1));

        tfTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTeleponActionPerformed(evt);
            }
        });
        jPanel2.add(tfTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 199, 147, -1));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 80, 50));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(240, 240, 240));
        jLabel28.setText("Tanggal Mulai :");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 101, -1, -1));
        jPanel2.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 101, 172, -1));

        taCatatan.setColumns(20);
        taCatatan.setLineWrap(true);
        taCatatan.setRows(3);
        jScrollPane15.setViewportView(taCatatan);

        jPanel2.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 16, 238, 65));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(240, 240, 240));
        jLabel29.setText("Catatan :");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 16, -1, -1));

        btnDetail.setText("Detail Pembelian");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jPanel2.add(btnDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 126, 49));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(240, 240, 240));
        jLabel30.setText("ID Customer :");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        tfPerusahaan.setToolTipText("Nama Depan");
        jPanel2.add(tfPerusahaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 90, 127, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(240, 240, 240));
        jLabel32.setText("Nama Kontak :");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 67, 94, -1));

        tfDepan.setToolTipText("Nama Depan");
        jPanel2.add(tfDepan, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 64, 127, -1));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 90, 40));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 770, 240));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(240, 240, 240));
        jLabel31.setText("Customer");
        add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 22, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, -1));

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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        addOrUpdate();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        showDetailPembelian();
    }//GEN-LAST:event_btnDetailActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JLabel labelPenjelasan;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextArea taCatatan;
    private javax.swing.JTable tabelCustomer;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTextField tfBelakang;
    private javax.swing.JTextField tfDepan;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfPerusahaan;
    private javax.swing.JTextField tfTelepon;
    // End of variables declaration//GEN-END:variables

}
