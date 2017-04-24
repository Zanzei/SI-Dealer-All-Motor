/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Master
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home1
     */
    karyawan abc;
    kategoriMotor ktm;
    supplier sup;
    customer cus;
    stokDiGudang stok;
    penjualan penj;
    pembelian pemb;
    retur ret;
    contact con;
    tambahPembelian tPemb;
    tambahPenjualan tPenj;
    tambahRetur tRet;
    
    public Home() {
        initComponents();
        Home.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnStok = new javax.swing.JButton();
        btnKategori = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnPenj = new javax.swing.JButton();
        btnPemb = new javax.swing.JButton();
        btnRetur = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnKaryawan = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnTambahRet = new javax.swing.JButton();
        btnTambahPenj = new javax.swing.JButton();
        btnTambahPemb = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogout = new javax.swing.JMenuItem();
        btnFeedback = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        repCustomer = new javax.swing.JMenuItem();
        repSupplier = new javax.swing.JMenuItem();
        repKaryawan = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        repLaba = new javax.swing.JMenuItem();
        repPembelian = new javax.swing.JMenuItem();
        repPenjualan = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        repRetur = new javax.swing.JMenuItem();
        repGaji = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        repPengiriman = new javax.swing.JMenuItem();
        repStok = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        repBestCustomer = new javax.swing.JMenuItem();
        repBestProd = new javax.swing.JMenuItem();
        repBestKaryawan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnContact = new javax.swing.JMenuItem();
        btnAbout = new javax.swing.JMenuItem();
        btnHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(255, 0, 51));

        jPanel3.setBackground(new java.awt.Color(255, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Motor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        btnStok.setBackground(new java.awt.Color(0, 153, 153));
        btnStok.setForeground(new java.awt.Color(0, 0, 255));
        btnStok.setText("Stok di Gudang");
        btnStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStokActionPerformed(evt);
            }
        });

        btnKategori.setBackground(new java.awt.Color(0, 153, 153));
        btnKategori.setForeground(new java.awt.Color(0, 0, 255));
        btnKategori.setText("Kategori Motor");
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStok)
                    .addComponent(btnKategori))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnKategori, btnStok});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(btnStok)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 0, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));
        jPanel4.setForeground(new java.awt.Color(240, 240, 240));

        btnPenj.setBackground(new java.awt.Color(0, 153, 153));
        btnPenj.setForeground(new java.awt.Color(0, 0, 255));
        btnPenj.setText("Penjualan");
        btnPenj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjActionPerformed(evt);
            }
        });

        btnPemb.setBackground(new java.awt.Color(0, 153, 153));
        btnPemb.setForeground(new java.awt.Color(0, 0, 255));
        btnPemb.setText("Pembelian");
        btnPemb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembActionPerformed(evt);
            }
        });

        btnRetur.setBackground(new java.awt.Color(0, 153, 153));
        btnRetur.setForeground(new java.awt.Color(0, 0, 255));
        btnRetur.setText("Retur");
        btnRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPenj, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPemb)
                    .addComponent(btnRetur))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPemb, btnPenj, btnRetur});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPenj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetur)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 0, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kontak", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        btnKaryawan.setBackground(new java.awt.Color(0, 153, 153));
        btnKaryawan.setForeground(new java.awt.Color(0, 0, 255));
        btnKaryawan.setText("Karyawan");
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(0, 153, 153));
        btnCustomer.setForeground(new java.awt.Color(0, 0, 255));
        btnCustomer.setText("Customer");
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnSupplier.setBackground(new java.awt.Color(0, 153, 153));
        btnSupplier.setForeground(new java.awt.Color(0, 0, 255));
        btnSupplier.setText("Supplier");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btnCustomer)
                        .addComponent(btnSupplier)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCustomer, btnKaryawan, btnSupplier});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCustomer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Welcome, Andre !");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/logo.png"))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 0, 51));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Penambahan Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(240, 240, 240)));

        btnTambahRet.setBackground(new java.awt.Color(0, 153, 153));
        btnTambahRet.setForeground(new java.awt.Color(0, 0, 255));
        btnTambahRet.setText("Tambah Retur");
        btnTambahRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahRetActionPerformed(evt);
            }
        });

        btnTambahPenj.setBackground(new java.awt.Color(0, 153, 153));
        btnTambahPenj.setForeground(new java.awt.Color(0, 0, 255));
        btnTambahPenj.setText("Tambah Penjualan");

        btnTambahPemb.setBackground(new java.awt.Color(0, 153, 153));
        btnTambahPemb.setForeground(new java.awt.Color(0, 0, 255));
        btnTambahPemb.setText("Tambah Pembelian");
        btnTambahPemb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPembActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTambahPenj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTambahPemb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTambahRet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambahPemb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTambahPenj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTambahRet)
                .addContainerGap())
        );

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(150, 150, 150))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(41, 41, 41)))))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        getContentPane().add(Home, "card2");

        jMenuBar1.setBackground(new java.awt.Color(255, 0, 51));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N

        jMenu1.setText("File");

        btnLogout.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(btnLogout);

        btnFeedback.setText("Lihat Feedback");
        btnFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbackActionPerformed(evt);
            }
        });
        jMenu1.add(btnFeedback);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Report");

        jMenu4.setText("Data Kontak");

        repCustomer.setText("Data Customer");
        repCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repCustomerActionPerformed(evt);
            }
        });
        jMenu4.add(repCustomer);

        repSupplier.setText("Data Supplier");
        repSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repSupplierActionPerformed(evt);
            }
        });
        jMenu4.add(repSupplier);

        repKaryawan.setText("Data Karyawan");
        repKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repKaryawanActionPerformed(evt);
            }
        });
        jMenu4.add(repKaryawan);

        jMenu3.add(jMenu4);

        jMenu5.setText("Data Transaksi");

        repLaba.setText("Data Laba-Rugi");
        repLaba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repLabaActionPerformed(evt);
            }
        });
        jMenu5.add(repLaba);

        repPembelian.setText("Data Pembelian");
        repPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repPembelianActionPerformed(evt);
            }
        });
        jMenu5.add(repPembelian);

        repPenjualan.setText("Data Penjualan");
        repPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repPenjualanActionPerformed(evt);
            }
        });
        jMenu5.add(repPenjualan);

        jMenu3.add(jMenu5);

        jMenu6.setText("Data Pengeluaran");

        repRetur.setText("Data Retur Barang");
        repRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repReturActionPerformed(evt);
            }
        });
        jMenu6.add(repRetur);

        repGaji.setText("Data Penggajian");
        repGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repGajiActionPerformed(evt);
            }
        });
        jMenu6.add(repGaji);

        jMenu3.add(jMenu6);

        jMenu7.setText("Data Pengiriman");

        repPengiriman.setText("Data Status Pengiriman");
        repPengiriman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repPengirimanActionPerformed(evt);
            }
        });
        jMenu7.add(repPengiriman);

        jMenu3.add(jMenu7);

        repStok.setText("Data Stok di Gudang");
        repStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repStokActionPerformed(evt);
            }
        });
        jMenu3.add(repStok);

        jMenu8.setText("Data Teratas");

        repBestCustomer.setText("Data Customer Terbaik");
        repBestCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repBestCustomerActionPerformed(evt);
            }
        });
        jMenu8.add(repBestCustomer);

        repBestProd.setText("Data Produk Terbaik");
        repBestProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repBestProdActionPerformed(evt);
            }
        });
        jMenu8.add(repBestProd);

        repBestKaryawan.setText("Data Sales Terbaik");
        repBestKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repBestKaryawanActionPerformed(evt);
            }
        });
        jMenu8.add(repBestKaryawan);

        jMenu3.add(jMenu8);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("About");

        btnContact.setText("Contact");
        btnContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactActionPerformed(evt);
            }
        });
        jMenu2.add(btnContact);

        btnAbout.setText("About Us");
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        jMenu2.add(btnAbout);

        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });
        jMenu2.add(btnHelp);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void repPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repPenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repPenjualanActionPerformed

    private void btnStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStokActionPerformed
        // TODO add your handling code here:
        stok = new stokDiGudang();
        this.add(stok);
        Home.setVisible(false);
        stok.setVisible(true);
    }//GEN-LAST:event_btnStokActionPerformed

    private void btnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaryawanActionPerformed
        // TODO add your handling code here:
        abc = new karyawan();
        this.add(abc);
        Home.setVisible(false);
        abc.setVisible(true);
    }//GEN-LAST:event_btnKaryawanActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
        cus = new customer();
        this.add(cus);
        Home.setVisible(false);
        cus.setVisible(true);
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnPenjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjActionPerformed
        // TODO add your handling code here:
        penj = new penjualan();
        this.add(penj);
        Home.setVisible(false);
        penj.setVisible(true);
    }//GEN-LAST:event_btnPenjActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        // TODO add your handling code here:
        sup = new supplier();
        this.add(sup);
        Home.setVisible(false);
        sup.setVisible(true);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactActionPerformed
        // TODO add your handling code here:
        con = new contact();
        this.add(con);
        Home.setVisible(false);
        con.setVisible(true);
    }//GEN-LAST:event_btnContactActionPerformed
    
    
    private void btnTambahRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahRetActionPerformed
        // TODO add your handling code here:
        tRet = new tambahRetur();
        this.add(tRet);
        Home.setVisible(false);
        tRet.setVisible(true);
    }//GEN-LAST:event_btnTambahRetActionPerformed

    private void btnPembActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembActionPerformed
        // TODO add your handling code here:
        pemb = new pembelian();
        this.add(pemb);
        Home.setVisible(false);
        pemb.setVisible(true);
    }//GEN-LAST:event_btnPembActionPerformed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        // TODO add your handling code here:
        ktm = new kategoriMotor();
        this.add(ktm);
        Home.setVisible(false);
        ktm.setVisible(true);
    }//GEN-LAST:event_btnKategoriActionPerformed

    private void btnReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturActionPerformed
        // TODO add your handling code here:
        ret = new retur();
        this.add(ret);
        Home.setVisible(false);
        ret.setVisible(true);
    }//GEN-LAST:event_btnReturActionPerformed

    private void btnFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFeedbackActionPerformed

    /*
    ---------
    Start of JasperReport code
    ---------
    */
    private void repSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repSupplierActionPerformed

    private void repCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repCustomerActionPerformed

    private void repKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repKaryawanActionPerformed

    private void repLabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repLabaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repLabaActionPerformed

    private void repPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repPembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repPembelianActionPerformed

    private void repReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repReturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repReturActionPerformed

    private void repGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repGajiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repGajiActionPerformed

    private void repPengirimanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repPengirimanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repPengirimanActionPerformed

    private void repStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repStokActionPerformed

    private void repBestCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repBestCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repBestCustomerActionPerformed

    private void repBestProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repBestProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repBestProdActionPerformed

    private void repBestKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repBestKaryawanActionPerformed
        
    }//GEN-LAST:event_repBestKaryawanActionPerformed
    /*
    ---------
    End of JasperReport code
    ---------
    */
    
    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        dispose();
        Login l = new Login();
        l.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTambahPembActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPembActionPerformed
       tPemb = new tambahPembelian();
        this.add(tPemb);
        Home.setVisible(false);
        tPemb.setVisible(true);
    }//GEN-LAST:event_btnTambahPembActionPerformed
    
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home;
    private javax.swing.JMenuItem btnAbout;
    private javax.swing.JMenuItem btnContact;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JMenuItem btnFeedback;
    private javax.swing.JMenuItem btnHelp;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnKategori;
    private javax.swing.JMenuItem btnLogout;
    private javax.swing.JButton btnPemb;
    private javax.swing.JButton btnPenj;
    private javax.swing.JButton btnRetur;
    private javax.swing.JButton btnStok;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTambahPemb;
    private javax.swing.JButton btnTambahPenj;
    private javax.swing.JButton btnTambahRet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JMenuItem repBestCustomer;
    private javax.swing.JMenuItem repBestKaryawan;
    private javax.swing.JMenuItem repBestProd;
    private javax.swing.JMenuItem repCustomer;
    private javax.swing.JMenuItem repGaji;
    private javax.swing.JMenuItem repKaryawan;
    private javax.swing.JMenuItem repLaba;
    private javax.swing.JMenuItem repPembelian;
    private javax.swing.JMenuItem repPengiriman;
    private javax.swing.JMenuItem repPenjualan;
    private javax.swing.JMenuItem repRetur;
    private javax.swing.JMenuItem repStok;
    private javax.swing.JMenuItem repSupplier;
    // End of variables declaration//GEN-END:variables
}
