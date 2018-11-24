-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2018 at 10:45 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ppsi`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun_user`
--

CREATE TABLE IF NOT EXISTS `akun_user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `no_telp` varchar(21) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `konfirmasi` varchar(50) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akun_user`
--

INSERT INTO `akun_user` (`id_user`, `nama`, `username`, `password`, `no_telp`, `email`, `konfirmasi`, `level`) VALUES
(1, 'bhisma', 'bhisma', 'bhisma10', '085730986700', 'bhismadw@gmail.com', '1', 'satpam'),
(2, 'Herher', 'herher', 'herher10', '0812', 'herher@gmail.com', '1', 'satpam'),
(3, 'bum', 'bum', 'bum10', '0123', 'bhismadw@gmail.com', '1', 'admin'),
(4, 'Saikoji', 'saikoji', 'saikoji10', '0987654321', 'saikoji@gmail.com', '1', 'pegawai'),
(5, 'Saito', 'saito', 'saito10', '0987654321', 'saito@gmail.com', '1', 'pegawai'),
(6, 'Sahid', 'Sahid', 'sahid10', '09876554', 'sahid@gmail.com', '1', 'satpam'),
(7, 'Dwi', 'Dwi', 'dwi10', '0894688', 'Dwi@gmail.com', '1', 'satpam'),
(8, 'Ramdani Agus', 'Ramdani', 'ramdani10', '0857309478100', 'ramdani@gmail.com', '1', 'satpam');

-- --------------------------------------------------------

--
-- Table structure for table `db_kunjungan`
--

CREATE TABLE IF NOT EXISTS `db_kunjungan` (
  `id_kujungan` int(11) NOT NULL,
  `nama` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alamat` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `instansi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `foto` text COLLATE utf8_unicode_ci,
  `no_ktp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `keperluan` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no_pol` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jam_masuk` time DEFAULT NULL,
  `jam_keluar` time DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_security` int(11) DEFAULT NULL,
  `id_employee` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=296 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_kunjungan`
--

INSERT INTO `db_kunjungan` (`id_kujungan`, `nama`, `alamat`, `instansi`, `foto`, `no_ktp`, `keperluan`, `no_pol`, `jam_masuk`, `jam_keluar`, `tanggal`, `status`, `id_security`, `id_employee`) VALUES
(295, 'Dwi Wandana', 'Jl. Maju Mundur', 'Stikom', 'http://192.168.0.16:50/ppsi/android/upload_image/images/ejzs7jzz9gn86d8wig10.png', '31601233000', 'KP', 'W 4130 L', '10:18:15', '11:43:27', '2018-07-13', 'Dapat Menemui', 1, '5'),
(294, 'Salim', 'JL Intisari', 'UNY', 'http://192.168.0.16:50/ppsi/android/upload_image/images/t38vgreyzbfupq6jqydm.png', '340599688', 'KP', 'W 4130 L', '04:14:44', NULL, '2018-07-12', 'Dapat Menemui', 1, '4'),
(293, 'Sadiil', 'JL Okedeh', 'UNESA', 'http://192.168.43.4:50/ppsi/android/upload_image/images/ez1krfnbbmwge8j3m8a2.png', '32155668900', 'KP', 'W 4130 L', NULL, NULL, '2018-07-12', 'Dapat Menemui', 1, '4'),
(291, 'Dwi', 'Jl Sidoarjo', 'Stikom', 'http://192.168.43.4:50/ppsi/android/upload_image/images/1gvw1ffysdzs8ir4at4h.png', '1232', 'KP', 'W 123 D', '08:27:39', NULL, '2018-07-12', 'Dapat Menemui', 1, '5'),
(290, 'Sunaro', '123123', 'Stikom', 'http://192.168.0.16:50/ppsi/android/upload_image/images/89ffe1i1jc4psfgchn7p.png', '123123', 'Kp', '1321', NULL, NULL, '2018-07-12', 'Dapat Menemui', 1, '4'),
(285, 'Heru', 'Jl Maju Mundur', 'Stikom Surabaya', 'http://192.168.43.4:50/ppsi/android/upload_image/images/bf7tka4q0i5hp9s9dtue.png', '12312', 'Ngeprint Cover', 'WF 123 D', '06:58:14', NULL, '2018-07-11', 'Dapat Menemui', 1, '5'),
(286, 'Budi', '1312', 'UNESA', 'http://192.168.43.4:50/ppsi/android/upload_image/images/x0n8yv07ix07kikpawmn.png', '123', 'KP', '123', NULL, NULL, '2018-07-11', NULL, 1, '5'),
(287, 'Sadil', '12312', 'PERBANAS', 'http://192.168.43.4:50/ppsi/android/upload_image/images/f04i35qdn14iwy8eh3fy.png', '1232', 'KP', '12312', NULL, NULL, '2018-07-11', NULL, 1, '5'),
(288, 'Syahrini', '13131', 'Medokan', 'http://192.168.43.4:50/ppsi/android/upload_image/images/bf7tka4q0i5hp9s9dtue.png', '113', 'KP', '11233', '09:16:16', '09:16:26', '2018-07-11', 'Dapat Menemui', 1, '5'),
(289, 'Shinji', 'Jl Sidoarjo', 'ITS', 'http://192.168.43.4:50/ppsi/android/upload_image/images/n2kgrvyp3v3tnx815hkh.png', '1122', 'KP', 'W 3445 D', '08:17:43', NULL, '2018-07-12', 'Dapat Menemui', 1, '5'),
(292, 'Siti', 'Jl TPI Indah', 'UGM', 'http://192.168.43.4:50/ppsi/android/upload_image/images/0qxxr028czak1g7se4a2.png', '351283900023', 'KP', 'W 4130 L', NULL, NULL, '2018-07-12', 'Dapat Menemui', 1, '4'),
(282, 'Syaid', '567', 'Stikom', NULL, '123', 'KP', '324', '08:50:47', '08:50:47', '2018-07-10', 'Dapat Menemui', 1, '4'),
(283, 'Ahay', '13123', 'Stikom', NULL, '12312', 'KP', '1231', '07:15:19', '07:18:08', '2018-07-10', 'Dapat Menemui', 1, '4'),
(284, 'Bhisma', 'J;. Maju mundur', 'Stikom', NULL, '12334', 'KP', 'W 123 W', '08:50:47', '08:17:56', '2018-07-10', 'Dapat Menemui', 1, '4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun_user`
--
ALTER TABLE `akun_user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `db_kunjungan`
--
ALTER TABLE `db_kunjungan`
  ADD PRIMARY KEY (`id_kujungan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun_user`
--
ALTER TABLE `akun_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `db_kunjungan`
--
ALTER TABLE `db_kunjungan`
  MODIFY `id_kujungan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=296;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
