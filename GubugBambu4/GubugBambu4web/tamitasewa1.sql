-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2017 at 08:06 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tamitasewa1`
--

-- --------------------------------------------------------

--
-- Table structure for table `uploadfoto`
--

CREATE TABLE `uploadfoto` (
  `id` int(255) NOT NULL,
  `idPengguna` varchar(255) NOT NULL,
  `namaFoto` varchar(255) NOT NULL,
  `namaFoto1` varchar(255) NOT NULL,
  `namaFoto2` varchar(255) NOT NULL,
  `jenisToko` varchar(255) NOT NULL,
  `tanggal` varchar(255) NOT NULL,
  `namatoko` varchar(255) NOT NULL,
  `hargasewa` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `telepon` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploadfoto`
--

INSERT INTO `uploadfoto` (`id`, `idPengguna`, `namaFoto`, `namaFoto1`, `namaFoto2`, `jenisToko`, `tanggal`, `namatoko`, `hargasewa`, `deskripsi`, `telepon`, `alamat`) VALUES
(6, '2', 'TamitaSewa-38-07-6-2017-38.jpg', '', '', 'Toko', '07-6-2017', 'Koboi', '12000000', 'Toko bagus untuk jualan makanan', '081362318697', 'Jalan Ir Ahmad Tahir'),
(7, '2', 'TamitaSewa-71-07-6-2017-71.jpg', '', '', 'Rumah', '07-6-2017', 'Pudge', '150000000', 'Fresh Meat', '852364587', 'Jalan hook'),
(8, '2', 'TamitaSewa-4-07-6-2017-4.jpg', '', '', 'Kost', '07-6-2017', 'PUDGE', '150000000', 'Need fresh meat', '081362318697', '2 Tango or I Feed !!'),
(9, '9', 'TamitaSewa-89-08-6-2017-89.jpg', 'TamitaSewa-a-2017-06-08-04-31-48-45-9.jpg', '', 'Kost', '08-6-2017', 'man', '22', 'ez', '088', 'gg'),
(10, '9', 'TamitaSewa-65-09-6-2017-65.jpg', 'TamitaSewa-a-2017-06-08-04-31-48-45-9.jpg', '', 'Rumah', '09-6-2017', 'man', '66', 'aha', '5454', 'aa');

-- --------------------------------------------------------

--
-- Table structure for table `uploadfoto1`
--

CREATE TABLE `uploadfoto1` (
  `id` int(255) NOT NULL,
  `idPengguna` varchar(2000) NOT NULL,
  `namaFoto` varchar(255) NOT NULL,
  `namaFoto1` varchar(255) NOT NULL,
  `namaFoto2` varchar(255) NOT NULL,
  `jenisToko` varchar(255) NOT NULL,
  `tanggal` varchar(255) NOT NULL,
  `namatoko` varchar(255) NOT NULL,
  `hargasewa` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `telepon` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploadfoto1`
--

INSERT INTO `uploadfoto1` (`id`, `idPengguna`, `namaFoto`, `namaFoto1`, `namaFoto2`, `jenisToko`, `tanggal`, `namatoko`, `hargasewa`, `deskripsi`, `telepon`) VALUES
(10, '2', 'TamitaSewa-40-2017-06-07-06-31-51-78-40.jpg', '', '', 'Toko', '2017-06-07-06-31-51-78', 'jjs', '7979', 'zbbz', 5484);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `email` varchar(30) NOT NULL,
  `fullname` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL,
  `telepon` varchar(30) NOT NULL,
  `foto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `fullname`, `username`, `password`, `telepon`, `foto`) VALUES
(1, 'a', 'Neymar Jr', 'shhs', 'd6db23495bdf397ae6cfadb27e7ffdcf', '4994', 'TamitaSewa-r-2017-06-06-21-44-50-02-1.jpg'),
(2, 'y', 'Neymar', 'shjs', '415290769594460e2e485922904f345d', '33', 'TamitaSewa-y-2017-06-06-21-45-39-71-2.jpg'),
(7, 'sb', 'bsb', 'sgs', '26148d621ef74844918af182d63976b6', '22', 'logo.jpg'),
(8, 'k', 'k', 'k', '8ce4b16b22b58894aa86c421e8759df3', '2', 'logo.jpg'),
(9, 'l', 'pudge', 'pudge', '2db95e8e1a9267b7a1188556b2013b33', '66', 'TamitaSewa-a-2017-06-08-04-31-48-45-9.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `uploadfoto`
--
ALTER TABLE `uploadfoto`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploadfoto1`
--
ALTER TABLE `uploadfoto1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `uploadfoto`
--
ALTER TABLE `uploadfoto`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `uploadfoto1`
--
ALTER TABLE `uploadfoto1`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
