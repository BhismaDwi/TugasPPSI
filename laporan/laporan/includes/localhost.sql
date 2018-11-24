-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 12, 2018 at 02:46 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u157331241_dbvis`
--
CREATE DATABASE IF NOT EXISTS `u157331241_dbvis` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `u157331241_dbvis`;

-- --------------------------------------------------------

--
-- Table structure for table `db_kunjungan`
--

CREATE TABLE `db_kunjungan` (
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
  `id_security` int(11) DEFAULT NULL,
  `id_employee` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_kunjungan`
--

INSERT INTO `db_kunjungan` (`id_kujungan`, `nama`, `alamat`, `instansi`, `foto`, `no_ktp`, `keperluan`, `no_pol`, `jam_masuk`, `jam_keluar`, `tanggal`, `id_security`, `id_employee`) VALUES
(102, 'Budi', 'Sidoarjo', 'UI', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/p90ktwa2jhx4jjc9yuh9.png', '12345678', 'KP', 'W 2331 UI', '08:50:47', NULL, '2018-02-12', 6, '4'),
(103, 'Seli', 'Sidoarjo', 'UI', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/zppfph0rmr2prqknp3ba.png', '321321', 'KP', '123123', '08:55:47', NULL, '2018-02-12', 6, '4'),
(104, 'jaja', 'akaj', 'nzns', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/5pjnbczsyjv1zhj7pkn5.png', 'ajqj', 'ansn', 'ajaj', '08:56:32', NULL, '2018-02-12', 10, '6'),
(105, 'Budi', 'Candi', 'UI', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/u60qumk22hq6uhx9wfku.png', '321321312', 'KP', 'QE 2331 UI', '08:57:41', NULL, '2018-02-12', 6, '4'),
(106, 'zaenal', 'sjsj', '1p2p', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/a70yzu4tuvzzxs9unfyu.png', 'hwhw', 'wuwu', 'wbab', '09:01:42', '00:00:00', '2018-02-12', 10, '6'),
(107, 'Sutejo', 'Candi', 'UNESA', 'http://t-rubbish.com/VisitorBook/android/upload_image/images/y0kg5d8hgiarjw2sh8c8.png', '12312232', 'KP', 'QR CODE LO', '09:08:18', '09:08:29', '2018-02-12', 10, '6');

-- --------------------------------------------------------

--
-- Table structure for table `db_pegawai`
--

CREATE TABLE `db_pegawai` (
  `id_pegawai` int(11) NOT NULL,
  `nama` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Reg_id` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_pegawai`
--

INSERT INTO `db_pegawai` (`id_pegawai`, `nama`, `username`, `password`, `Reg_id`) VALUES
(9, 'Nur', 'Nur', 'nur10', 'c1ZyjaG9-gY:APA91bGRjTeVsQcKnFnlfUDWWXaTa7AstulgSmAMqOlR8QeomY3HyUvOqjkHGxp4QKzbYFnZc_qVuhDiLjCOULaQNMsIHWBY4JhDzFI0gJbtmF17qTFEQv1bBHoXb_HAvWXL4_qNsyX6'),
(4, 'Sukarni', 'Sukarni', 'Sukarni10', 'ezZ9dBu9K3E:APA91bF0jpx6gxLNoi46jPba3nWx6CfSEzctqCIcdLMx4EQ1DptigSNQHI-gprVb5S9ICB8e1ccy8OmZi28tOfHFZU3GxaatnVUV5ypQH27iOhKedI8cuXuR7KbjBq3e1434_5RtZrBQ'),
(8, 'Amal', 'Amal', 'amal14', 'cM5DJAzrkiU:APA91bH_jvRBhbalOG_nVkthNDTUBSRpMeh0VIUPm_ki3FieMBxxitXKtHBBFkb94pMCR_4dJss8MC0-fcElzaGA8ivgRFQZSaFpFUy8xmhRYeEE1ChtzXhF2P9uATjTTwB6rD_UcdMD'),
(6, 'Abigail', 'Abigail', 'Abigail10', 'cFzhF1HZ6zA:APA91bFLA9VfrWfkUOXflFMbkg7pZvWvH_43ryTwYC5_lQoATjsoe4AtiD3C8_KfnHdCXYIJtH8TC2EC5jJs7JQUBImOV4sB5maSMM1CZGPr4UHS7qxkI6-CfNrqvlRByjkPmrH4cACo');

-- --------------------------------------------------------

--
-- Table structure for table `db_security`
--

CREATE TABLE `db_security` (
  `id_security` int(5) NOT NULL,
  `nama_security` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `Reg_id` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_security`
--

INSERT INTO `db_security` (`id_security`, `nama_security`, `username`, `password`, `Reg_id`) VALUES
(10, 'suratno', 'suratno', 'suratno10', 'ezZ9dBu9K3E:APA91bF0jpx6gxLNoi46jPba3nWx6CfSEzctqCIcdLMx4EQ1DptigSNQHI-gprVb5S9ICB8e1ccy8OmZi28tOfHFZU3GxaatnVUV5ypQH27iOhKedI8cuXuR7KbjBq3e1434_5RtZrBQ'),
(6, 'Sukarno', 'Sukarno', 'Sukarno10', 'cFzhF1HZ6zA:APA91bFLA9VfrWfkUOXflFMbkg7pZvWvH_43ryTwYC5_lQoATjsoe4AtiD3C8_KfnHdCXYIJtH8TC2EC5jJs7JQUBImOV4sB5maSMM1CZGPr4UHS7qxkI6-CfNrqvlRByjkPmrH4cACo');

-- --------------------------------------------------------

--
-- Table structure for table `db_user_laporan`
--

CREATE TABLE `db_user_laporan` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `db_kunjungan`
--
ALTER TABLE `db_kunjungan`
  ADD PRIMARY KEY (`id_kujungan`);

--
-- Indexes for table `db_pegawai`
--
ALTER TABLE `db_pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `db_security`
--
ALTER TABLE `db_security`
  ADD PRIMARY KEY (`id_security`);

--
-- Indexes for table `db_user_laporan`
--
ALTER TABLE `db_user_laporan`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `db_kunjungan`
--
ALTER TABLE `db_kunjungan`
  MODIFY `id_kujungan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `db_pegawai`
--
ALTER TABLE `db_pegawai`
  MODIFY `id_pegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `db_security`
--
ALTER TABLE `db_security`
  MODIFY `id_security` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `db_user_laporan`
--
ALTER TABLE `db_user_laporan`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
