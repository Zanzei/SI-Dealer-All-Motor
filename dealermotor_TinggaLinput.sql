-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2017 at 05:31 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dealermotor`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `IDCustomer` int(10) NOT NULL,
  `NamaPerusahaan` varchar(255) DEFAULT NULL,
  `NamaDepan` varchar(100) NOT NULL,
  `NamaBelakang` varchar(100) NOT NULL,
  `Alamat` varchar(255) NOT NULL,
  `NoTelepon` varchar(20) NOT NULL,
  `Catatan` varchar(255) DEFAULT NULL,
  `TanggalMulai` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`IDCustomer`, `NamaPerusahaan`, `NamaDepan`, `NamaBelakang`, `Alamat`, `NoTelepon`, `Catatan`, `TanggalMulai`) VALUES
(1, NULL, 'Neneng', 'Sutisna', 'Jl. Soekarno Hatta No 15', '0812 2222 5322', 'Pembeli Baru', '2017-04-02'),
(2, NULL, 'Titik', 'Sutisna', 'Jl. Riau No. 19A', '0812 5322 3422', 'Saudaranya neneng', '2017-04-04'),
(3, 'Langgeng Jaya', 'Kembar', 'Djantoro', 'Jl. Sudirman No. 15C', '0812 5953 3411', 'Perusahaan kue', '2017-04-05'),
(4, 'Gio Corporation', 'Giovanno', 'Reynalda', 'Jl. Batununggal No 100 - 200A', '0888 2322 5345', 'Boss besar', '2017-04-02'),
(5, NULL, 'Abang', 'Jek', 'Jl. Gang No. 1125', '0812 9533 5322', NULL, '2017-04-09');

-- --------------------------------------------------------

--
-- Table structure for table `detailpembelian`
--

CREATE TABLE `detailpembelian` (
  `IDOrderDetails` int(10) NOT NULL,
  `IDProduk` int(10) NOT NULL,
  `IDOrder` int(10) NOT NULL,
  `Harga` int(100) NOT NULL,
  `Jumlah` int(100) NOT NULL,
  `PotonganHarga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailpembelian`
--

INSERT INTO `detailpembelian` (`IDOrderDetails`, `IDProduk`, `IDOrder`, `Harga`, `Jumlah`, `PotonganHarga`) VALUES
(1, 26, 1, 18450000, 6, 0),
(2, 16, 2, 15650000, 12, 0),
(3, 18, 9, 15000000, 10, 300000),
(4, 25, 1, 14000000, 11, 400000),
(5, 2, 1, 16650000, 8, 25000),
(6, 29, 1, 17500000, 9, 100000),
(7, 32, 10, 19000000, 15, 250000),
(8, 34, 10, 64000000, 5, 500000);

-- --------------------------------------------------------

--
-- Table structure for table `detailpenjualan`
--

CREATE TABLE `detailpenjualan` (
  `IDOrderDetails` int(10) NOT NULL,
  `IDProduk` int(10) NOT NULL,
  `IDOrder` int(10) NOT NULL,
  `Harga` int(100) NOT NULL,
  `Jumlah` int(100) NOT NULL,
  `PotonganHarga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailpenjualan`
--

INSERT INTO `detailpenjualan` (`IDOrderDetails`, `IDProduk`, `IDOrder`, `Harga`, `Jumlah`, `PotonganHarga`) VALUES
(1, 2, 1, 17000000, 1, 0),
(2, 3, 1, 18000000, 1, 0),
(3, 4, 1, 22000000, 1, 0),
(4, 6, 1, 18000000, 1, 0),
(5, 3, 2, 17300000, 1, 0),
(6, 9, 2, 15000000, 1, 375000),
(7, 9, 3, 14000000, 50, 1375000),
(8, 12, 3, 27075000, 2, 0),
(9, 5, 4, 18000000, 1, 0),
(10, 17, 5, 17000000, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `detailretur`
--

CREATE TABLE `detailretur` (
  `IDReturDetails` int(10) NOT NULL,
  `IDProduk` int(10) NOT NULL,
  `IDRetur` int(10) NOT NULL,
  `Keterangan` varchar(255) NOT NULL,
  `Disetujui` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailretur`
--

INSERT INTO `detailretur` (`IDReturDetails`, `IDProduk`, `IDRetur`, `Keterangan`, `Disetujui`) VALUES
(1, 17, 1, 'Masalah mesin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `IDFeedback` int(10) NOT NULL,
  `IDKaryawan` int(10) NOT NULL,
  `Keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gaji`
--

CREATE TABLE `gaji` (
  `Jabatan` varchar(5) NOT NULL,
  `Gaji` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaji`
--

INSERT INTO `gaji` (`Jabatan`, `Gaji`) VALUES
('ADM', 3500000),
('CEO', 20000000),
('HRD', 3500000),
('MNGR', 5000000),
('OB', 3000000),
('SPV', 10000000);

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `IDKaryawan` int(10) NOT NULL,
  `NamaDepan` varchar(255) NOT NULL,
  `NamaBelakang` varchar(255) NOT NULL,
  `Jabatan` varchar(5) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Alamat` varchar(255) NOT NULL,
  `NoTelepon` text NOT NULL,
  `Catatan` varchar(255) NOT NULL,
  `Supervisor` int(100) NOT NULL,
  `TanggalMulai` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`IDKaryawan`, `NamaDepan`, `NamaBelakang`, `Jabatan`, `Password`, `Alamat`, `NoTelepon`, `Catatan`, `Supervisor`, `TanggalMulai`) VALUES
(1, 'Dedi', 'Mulyono', 'ADM', '12345', 'Jl. Riau No 15', '0812 2222 5234', 'Jarang terlambat', 2, '2017-04-03'),
(2, 'Handi', 'Thean', 'SPV', '54321', 'Jl. Dago No 12', '0812 2222 0532', 'Anaknya Bos', 5, '2017-04-04'),
(3, 'Dean', 'Solo', 'HRD', '12345', 'Jl. Batununggal No. 01', '0811 2532 8532', '', 2, '2017-04-05'),
(4, 'Giovanna', 'Reynaldi', 'CEO', '54321', 'Jl. Titik No. 152 - 180', '0888 1111 0000', 'Boss besar', 0, '2017-04-03'),
(5, 'Gio', 'Yanto', 'MNGR', '12345', 'Jl. Sibuk No. 00', '0822 2545 2123', 'Anak baik', 4, '2017-04-19'),
(6, 'Adil', 'Best', 'OB', '11111', 'Jl. Batubuah No 11', '0822 3543 4664', 'Performa buruk sekali', 5, '2017-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `kategorimotor`
--

CREATE TABLE `kategorimotor` (
  `IDKategori` int(5) NOT NULL,
  `NamaKategori` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategorimotor`
--

INSERT INTO `kategorimotor` (`IDKategori`, `NamaKategori`) VALUES
(1, 'Cub'),
(2, 'Matic'),
(3, 'Sport'),
(4, 'CBU (Completely Built Up)');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `IDOrder` int(10) NOT NULL,
  `IDSupplier` int(10) NOT NULL,
  `MetodePembayaran` varchar(255) NOT NULL,
  `TanggalPembelian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`IDOrder`, `IDSupplier`, `MetodePembayaran`, `TanggalPembelian`) VALUES
(1, 1, 'CREDIT', '2017-04-04'),
(2, 2, 'CREDIT', '2017-04-02'),
(9, 3, 'CASH', '2017-04-03'),
(10, 2, 'DEBIT', '2017-04-04');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `IDOrder` int(10) NOT NULL,
  `IDKaryawan` int(10) NOT NULL,
  `IDCustomer` int(10) NOT NULL,
  `MetodePembayaran` varchar(100) NOT NULL,
  `TanggalPembayaran` date NOT NULL,
  `Lunas` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`IDOrder`, `IDKaryawan`, `IDCustomer`, `MetodePembayaran`, `TanggalPembayaran`, `Lunas`) VALUES
(1, 1, 3, 'CREDIT', '2017-04-02', 0),
(2, 2, 5, 'CASH', '2017-04-02', 1),
(3, 4, 1, 'DEBIT', '2017-04-02', 1),
(4, 1, 5, 'CASH', '2017-04-02', 1),
(5, 3, 1, 'CREDIT', '2017-04-02', 0),
(6, 1, 2, 'CREDIT', '2017-04-02', 0),
(7, 1, 4, 'DEBIT', '2017-04-02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `retur`
--

CREATE TABLE `retur` (
  `IDRetur` int(10) NOT NULL,
  `IDOrder` int(10) NOT NULL,
  `IDCustomer` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retur`
--

INSERT INTO `retur` (`IDRetur`, `IDOrder`, `IDCustomer`) VALUES
(1, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `stokdigudang`
--

CREATE TABLE `stokdigudang` (
  `IDBarang` int(5) NOT NULL,
  `NamaBarang` varchar(255) NOT NULL,
  `Stok` int(10) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Harga` int(15) NOT NULL,
  `IDKategori` int(10) NOT NULL,
  `IDSupplier` int(10) NOT NULL,
  `TanggalDidapat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stokdigudang`
--

INSERT INTO `stokdigudang` (`IDBarang`, `NamaBarang`, `Stok`, `Brand`, `Harga`, `IDKategori`, `IDSupplier`, `TanggalDidapat`) VALUES
(1, 'asdas', 2, 'Honda', 121, 2, 3, '2017-04-11'),
(2, 'Blade 125 FI Disc Brake MMC', 8, 'Honda', 16675000, 1, 1, '2017-04-10'),
(3, 'Blade 125 FI Repsol MMC', 7, 'Honda', 17075000, 1, 1, '2017-04-04'),
(4, 'All New Supra GTR 150 Sporty Nictic Orange', 7, 'Honda', 21550000, 1, 1, '2017-04-04'),
(5, 'All New Scoopy Sporty', 9, 'Honda', 17800000, 2, 1, '2017-04-05'),
(6, 'All New Scoopy Stylish', 8, 'Honda', 17800000, 2, 1, '2017-04-05'),
(9, 'New BeAT Sporty CBS', 7, 'Honda', 15375000, 2, 1, '2017-04-05'),
(10, 'New Vario 110 eSP CBS - ISS Advanced', 6, 'Honda', 17575000, 2, 1, '2017-04-11'),
(11, 'New CBR 150 R Standar', 5, 'Honda', 32975000, 3, 1, '2017-04-07'),
(12, 'New Honda CB150R Streetfire SE', 9, 'Honda', 27075000, 3, 1, '2017-04-05'),
(13, 'New CBR250RR Racing Red ABS', 6, 'Honda', 70125000, 3, 4, '2017-04-04'),
(14, 'New CBR250RR Repsol Edition ABS', 3, 'Honda', 72000000, 3, 5, '2017-04-12'),
(15, 'NMAX  ABS', 3, 'Yamaha', 27900000, 2, 2, '2017-04-05'),
(16, 'All New Soul GT', 12, 'Yamaha', 15650000, 2, 5, '2017-04-11'),
(17, 'GT 125', 11, 'Yamaha', 16400000, 2, 6, '2017-04-10'),
(18, 'X-Ride', 10, 'Yamaha', 15300000, 2, 5, '2017-04-11'),
(19, 'Xeon RC', 11, 'Yamaha', 15750000, 2, 2, '2017-04-05'),
(20, 'GT 125 Garuda', 11, 'Yamaha', 16500000, 2, 3, '2017-04-05'),
(21, 'R25 ABS', 3, 'Yamaha', 59000000, 3, 5, '2017-04-05'),
(22, 'R25 Non Abs', 4, 'Yamaha', 53000000, 3, 4, '2017-04-09'),
(23, 'Vixion KS Moto GP', 3, 'Yamaha', 24650000, 3, 4, '2017-04-20'),
(24, 'R25 Movistar', 5, 'Yamaha', 53600000, 3, 5, '2017-04-04'),
(25, 'FORCE', 11, 'Yamaha', 14400000, 1, 5, '2017-04-03'),
(26, 'Jupiter MX 150', 6, 'Yamaha', 18450000, 1, 4, '2017-04-05'),
(29, 'Jupiter MX CW', 9, 'Yamaha', 17600000, 1, 6, '2017-04-03'),
(30, 'All New R1', 1, 'Yamaha', 495000000, 4, 2, '2017-04-09'),
(31, 'TMAX', 2, 'Yamaha', 185000000, 4, 3, '2017-04-11'),
(32, 'MX KING 150', 15, 'Yamaha', 19250000, 1, 4, '2017-04-04'),
(33, 'Classic 350', 3, 'Royal Enfield', 79000000, 4, 4, '2017-04-10'),
(34, 'Bullet 350', 5, 'Royal Enfield', 64500000, 4, 4, '2017-04-05'),
(35, 'Bullet 500 EFI', 3, 'Royal Enfield', 81500000, 4, 3, '2017-04-12'),
(36, 'Classic 500', 5, 'Royal Enfield', 86900000, 4, 3, '2017-04-09'),
(50, 'asasdsada', 2, 'Honda', 432432, 1, 1, '2017-04-02'),
(51, 'asdasdasdasada', 2, 'Honda', 123221123, 2, 3, '2017-04-11'),
(52, 'asdasdsadsa', 2, 'Honda', 12312, 2, 3, '2017-04-11'),
(53, 'dsaa', 2, 'Honda', 12312, 2, 3, '2017-04-11'),
(54, 'dsaaadsadsa', 2, 'Honda', 12312, 2, 3, '2017-04-11');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `IDSupplier` int(10) NOT NULL,
  `NamaPerusahaan` varchar(255) NOT NULL,
  `NamaDepan` varchar(100) NOT NULL,
  `NamaBelakang` varchar(100) NOT NULL,
  `Alamat` varchar(255) NOT NULL,
  `NoTelepon` int(20) NOT NULL,
  `Catatan` varchar(255) NOT NULL,
  `TanggalMulai` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`IDSupplier`, `NamaPerusahaan`, `NamaDepan`, `NamaBelakang`, `Alamat`, `NoTelepon`, `Catatan`, `TanggalMulai`) VALUES
(1, 'Astra Honda Motor', 'Dedi', 'Kaswani', 'Jl. Dr. Djundjunan No.192', 221524263, 'Tutup sabtu dan minggu', '2017-04-01'),
(2, 'Yamaha Indonesia Motor', 'Hani', 'Putri', 'JL Soekarno Hatta, No. 474 A', 221267543, '', '2017-04-02'),
(3, 'Mids Motor Indonesia', 'Kenny', 'Mulyadi', 'JL. Soekarno Hatta, No. 101 B', 226444932, '', '2017-04-02'),
(4, 'Bimantara Motor', 'Gia', 'Mulyono', 'JL. Buah Batu, No. 130 B', 227773212, '', '2017-04-02'),
(5, 'Sarana Teknik', 'Loni', 'Handoyo', 'Jl. Djunjunan, No. 152 A', 226669321, '', '2017-04-04'),
(6, 'Interjaya Surya Megah', 'Daniel', 'Tan', 'Jl. Batununggal, No 15 - 17', 220338532, 'Tutup hari senin', '2017-04-21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`IDCustomer`);

--
-- Indexes for table `detailpembelian`
--
ALTER TABLE `detailpembelian`
  ADD PRIMARY KEY (`IDOrderDetails`),
  ADD KEY `IDProduk` (`IDProduk`),
  ADD KEY `IDOrder` (`IDOrder`);

--
-- Indexes for table `detailpenjualan`
--
ALTER TABLE `detailpenjualan`
  ADD PRIMARY KEY (`IDOrderDetails`),
  ADD KEY `ID Produk` (`IDProduk`),
  ADD KEY `IDOrder` (`IDOrder`);

--
-- Indexes for table `detailretur`
--
ALTER TABLE `detailretur`
  ADD PRIMARY KEY (`IDReturDetails`),
  ADD KEY `IDProduk` (`IDProduk`),
  ADD KEY `IDRetur` (`IDRetur`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`IDFeedback`),
  ADD KEY `ID Karyawan` (`IDKaryawan`);

--
-- Indexes for table `gaji`
--
ALTER TABLE `gaji`
  ADD PRIMARY KEY (`Jabatan`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`IDKaryawan`),
  ADD KEY `Jabatan` (`Jabatan`);

--
-- Indexes for table `kategorimotor`
--
ALTER TABLE `kategorimotor`
  ADD PRIMARY KEY (`IDKategori`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`IDOrder`),
  ADD KEY `IDSupplier` (`IDSupplier`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`IDOrder`),
  ADD KEY `ID Karyawan` (`IDKaryawan`),
  ADD KEY `ID Customer` (`IDCustomer`);

--
-- Indexes for table `retur`
--
ALTER TABLE `retur`
  ADD PRIMARY KEY (`IDRetur`),
  ADD KEY `IDCustomer` (`IDCustomer`),
  ADD KEY `IDOrder` (`IDOrder`);

--
-- Indexes for table `stokdigudang`
--
ALTER TABLE `stokdigudang`
  ADD PRIMARY KEY (`IDBarang`),
  ADD KEY `ID Kategori` (`IDKategori`),
  ADD KEY `ID Supplier` (`IDSupplier`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`IDSupplier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `IDCustomer` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `detailpembelian`
--
ALTER TABLE `detailpembelian`
  MODIFY `IDOrderDetails` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `detailpenjualan`
--
ALTER TABLE `detailpenjualan`
  MODIFY `IDOrderDetails` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `detailretur`
--
ALTER TABLE `detailretur`
  MODIFY `IDReturDetails` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `IDFeedback` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `IDKaryawan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `kategorimotor`
--
ALTER TABLE `kategorimotor`
  MODIFY `IDKategori` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `IDOrder` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `IDOrder` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `retur`
--
ALTER TABLE `retur`
  MODIFY `IDRetur` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `stokdigudang`
--
ALTER TABLE `stokdigudang`
  MODIFY `IDBarang` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `IDSupplier` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `detailpembelian`
--
ALTER TABLE `detailpembelian`
  ADD CONSTRAINT `detailpembelian_ibfk_1` FOREIGN KEY (`IDProduk`) REFERENCES `stokdigudang` (`IDBarang`),
  ADD CONSTRAINT `detailpembelian_ibfk_2` FOREIGN KEY (`IDOrder`) REFERENCES `pembelian` (`IDOrder`);

--
-- Constraints for table `detailpenjualan`
--
ALTER TABLE `detailpenjualan`
  ADD CONSTRAINT `detailpenjualan_ibfk_1` FOREIGN KEY (`IDProduk`) REFERENCES `stokdigudang` (`IDBarang`),
  ADD CONSTRAINT `detailpenjualan_ibfk_2` FOREIGN KEY (`IDOrder`) REFERENCES `penjualan` (`IDOrder`);

--
-- Constraints for table `detailretur`
--
ALTER TABLE `detailretur`
  ADD CONSTRAINT `detailretur_ibfk_1` FOREIGN KEY (`IDProduk`) REFERENCES `stokdigudang` (`IDBarang`),
  ADD CONSTRAINT `detailretur_ibfk_2` FOREIGN KEY (`IDRetur`) REFERENCES `retur` (`IDRetur`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`IDKaryawan`) REFERENCES `karyawan` (`IDKaryawan`);

--
-- Constraints for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD CONSTRAINT `karyawan_ibfk_1` FOREIGN KEY (`Jabatan`) REFERENCES `gaji` (`Jabatan`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`IDSupplier`) REFERENCES `supplier` (`IDSupplier`);

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`IDKaryawan`) REFERENCES `karyawan` (`IDKaryawan`),
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`IDCustomer`) REFERENCES `customer` (`IDCustomer`);

--
-- Constraints for table `retur`
--
ALTER TABLE `retur`
  ADD CONSTRAINT `retur_ibfk_1` FOREIGN KEY (`IDOrder`) REFERENCES `penjualan` (`IDOrder`);

--
-- Constraints for table `stokdigudang`
--
ALTER TABLE `stokdigudang`
  ADD CONSTRAINT `stokdigudang_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategorimotor` (`IDKategori`),
  ADD CONSTRAINT `stokdigudang_ibfk_2` FOREIGN KEY (`IDSupplier`) REFERENCES `supplier` (`IDSupplier`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
