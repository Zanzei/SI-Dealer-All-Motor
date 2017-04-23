/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.stokDAO;
import Model.Stokdigudang;
import Controller.HibernateUtil;
import Controller.kategoriDAO;
import Controller.supplierDAO;
import Model.Kategorimotor;
import Model.Supplier;
import java.util.ArrayList;
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
public class stokDiGudang extends javax.swing.JPanel implements ListSelectionListener {

    /**
     * Creates new form stokDiGudang
     */
    private stokDAO daoStok;
    private DefaultTableModel dtmStok;
    private List<Stokdigudang> listStok;
    private List<Kategorimotor> listKategori;
    private List<Supplier> listSupplier;
    private kategoriDAO daoKategori;
    private supplierDAO daoSupplier;

    //Buat DCBM
    private DefaultComboBoxModel dcbmKategori;
    private DefaultComboBoxModel dcbmSupplier;

    int row;

    public stokDiGudang() {
        initComponents();
        daoStok = new stokDAO();
        daoKategori = new kategoriDAO();
        daoSupplier = new supplierDAO();
        dcbmKategori = new DefaultComboBoxModel();
        dcbmSupplier = new DefaultComboBoxModel();

        dtmStok = (DefaultTableModel) tableStok.getModel();
        listStok = daoStok.getAllStok();
        listKategori = daoKategori.getAllKategori();
        listSupplier = daoSupplier.getAllSupplier();
        //Call methods
        showAllDataStok();
        addToComboBox();
    }

    /*
    ---------
    Start of methods 
     */
    //Untuk menampilkan seluruh data stok
    private void showAllDataStok() {
        tableStok.getSelectionModel().removeListSelectionListener(this);
        listStok = daoStok.getAllStok();
        dtmStok.getDataVector().removeAllElements();
        for (Stokdigudang s : listStok) {
            dtmStok.addRow(new Object[]{
                s.getIDBarang(),
                s.getNamaBarang(),
                s.getStok(),
                s.getBrand(),
                s.getHarga(),
                s.getIDKategori().getNamaKategori(),
                s.getIDSupplier().getNamaPerusahaan(),
                s.getTanggalDidapat()
            });
        }
        tableStok.getSelectionModel().addListSelectionListener(this);
    }

    //Untuk menampilkan data sesuai filter yang dipilih
    private void showFilter() {
        dtmStok.getDataVector().removeAllElements();
        String pilih = cbFilter.getSelectedItem().toString();
        List<Stokdigudang> hasil = new ArrayList<>();

        int pan = tfFilter.getText().length();
        String banding = "";

        if (pilih.equals("Brand")) {
            for (Stokdigudang s : listStok) {
                banding = s.getBrand().substring(0, pan);
                if (banding.equals(tfFilter.getText())) {
                    hasil.add(s);
                }
            }
        } else if (pilih.equals("Kategori")) {
            for (Stokdigudang s : listStok) {
                banding = s.getIDKategori().getNamaKategori().substring(0, pan);
                if (banding.equals(tfFilter.getText())) {
                    hasil.add(s);
                }
            }
        } else if (pilih.equals("Supplier")) {
            for (Stokdigudang s : listStok) {
                banding = s.getIDSupplier().getNamaPerusahaan().substring(0, pan);
                if (banding.equals(tfFilter.getText())) {
                    hasil.add(s);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Filter !");
        }

        if (hasil.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada stok yang ditemukan !");
        } else {
            for (Stokdigudang s : hasil) {
                dtmStok.addRow(new Object[]{
                    s.getIDBarang(),
                    s.getNamaBarang(),
                    s.getStok(),
                    s.getBrand(),
                    s.getHarga(),
                    s.getIDKategori().getNamaKategori(),
                    s.getIDSupplier().getNamaPerusahaan(),
                    s.getTanggalDidapat()
                });
            }

            tableStok.setModel(dtmStok);
        }
    }

    //Untuk menghapus data
    private void deleteData() {
        Stokdigudang s = new Stokdigudang();
        s.setIDBarang(listStok.get(row).getIDBarang());
        s.setNamaBarang(tfNama.getText());
        s.setHarga(new Integer(tfHarga.getText()));
        s.setStok((int) spJumlah.getValue());
        s.setBrand(tfBrand.getText());
        s.setIDKategori((Kategorimotor) cbKategori.getSelectedItem());
        s.setIDSupplier((Supplier) cbSupplier.getSelectedItem());
        s.setTanggalDidapat(dateChooser.getDate());

        try {
            daoStok.deleteStok(s);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            showAllDataStok();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
        }
    }

    //Untuk memasukkan atau mengupdate data
    private void addOrUpdate() {
        try {
            Stokdigudang s = new Stokdigudang();
            if (!tableStok.getSelectionModel().isSelectionEmpty()) {
                s.setIDBarang(listStok.get(row).getIDBarang());
            }
            s.setNamaBarang(tfNama.getText());
            s.setHarga(new Integer(tfHarga.getText()));
            s.setStok((int) spJumlah.getValue());
            s.setBrand(tfBrand.getText());
            s.setIDKategori((Kategorimotor) cbKategori.getSelectedItem());
            s.setIDSupplier((Supplier) cbSupplier.getSelectedItem());
            s.setTanggalDidapat(dateChooser.getDate());

            daoStok.addOrUpdateStok(s);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            showAllDataStok();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
        }
    }

    /*
    ---------
    End of methods 
     */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == tableStok.getSelectionModel()) {
            row = tableStok.getSelectedRow();
        }

        tfNama.setText(listStok.get(row).getNamaBarang());
        tfHarga.setText(String.valueOf(listStok.get(row).getHarga()));
        spJumlah.setValue(listStok.get(row).getStok());
        tfBrand.setText(listStok.get(row).getBrand());
        cbKategori.setSelectedItem(listStok.get(row).getIDKategori());
        cbSupplier.setSelectedItem(listStok.get(row).getIDSupplier());
        dateChooser.setDate(listStok.get(row).getTanggalDidapat());
    }

    private void addToComboBox() {
        for (Kategorimotor k : listKategori) {
            dcbmKategori.addElement(k);
        }
        for (Supplier s : listSupplier) {
            dcbmSupplier.addElement(s);
        }
        cbKategori.setModel(dcbmKategori);
        cbSupplier.setModel(dcbmSupplier);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableStok = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbFilter = new javax.swing.JComboBox<>();
        tfFilter = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        spJumlah = new javax.swing.JSpinner();
        tfBrand = new javax.swing.JTextField();
        btnKonfirmasi = new javax.swing.JButton();
        tfNama = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        cbSupplier = new javax.swing.JComboBox<>();
        btnApply = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Stok ", "Brand", "Harga", "Kategori", "ID Supplier", "Tanggal Didapat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tableStok);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 120, 590, 303));

        btnBack.setBackground(new java.awt.Color(255, 51, 51));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 72, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Stok Gudang");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Filter by :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 81, -1, -1));

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Filter --", "Brand", "Kategori", "Supplier" }));
        add(cbFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 79, -1, -1));
        add(tfFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 187, -1));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 74, 39));

        jPanel17.setBackground(new java.awt.Color(255, 51, 51));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Produk Baru", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Nama Produk :");
        jPanel17.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, 98, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(240, 240, 240));
        jLabel38.setText("Harga :");
        jPanel17.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 61, 98, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(240, 240, 240));
        jLabel41.setText("Jumlah :");
        jPanel17.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 99, 98, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(240, 240, 240));
        jLabel44.setText("Brand :");
        jPanel17.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 134, 98, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(240, 240, 240));
        jLabel49.setText("Kategori :");
        jPanel17.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 162, 98, -1));
        jPanel17.add(tfHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 58, 178, -1));
        jPanel17.add(spJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 96, 65, -1));
        jPanel17.add(tfBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 128, 178, -1));

        btnKonfirmasi.setText("Konfirmasi");
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });
        jPanel17.add(btnKonfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 254, 97, -1));
        jPanel17.add(tfNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 27, 178, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(240, 240, 240));
        jLabel50.setText("Supplier :");
        jPanel17.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 188, 98, -1));
        jPanel17.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 223, 178, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(240, 240, 240));
        jLabel51.setText("Tanggal Didapat :");
        jPanel17.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 223, -1, -1));

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Kategori --" }));
        jPanel17.add(cbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 180, -1));

        jPanel17.add(cbSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 170, -1));

        add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 330, 290));

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });
        add(btnApply, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 74, 39));
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        showFilter();
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        addOrUpdate();
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        deleteData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JComboBox<String> cbFilter;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JComboBox<String> cbSupplier;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spJumlah;
    private javax.swing.JTable tableStok;
    private javax.swing.JTextField tfBrand;
    private javax.swing.JTextField tfFilter;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfNama;
    // End of variables declaration//GEN-END:variables

}
