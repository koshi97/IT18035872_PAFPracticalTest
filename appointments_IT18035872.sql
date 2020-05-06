-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 03:04 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appID` int(11) NOT NULL,
  `appNo` varchar(10) CHARACTER SET latin1 NOT NULL,
  `appType` varchar(30) CHARACTER SET latin1 NOT NULL,
  `appDate` varchar(10) CHARACTER SET latin1 NOT NULL,
  `appDescription` varchar(200) CHARACTER SET latin1 NOT NULL,
  `appPrice` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appID`, `appNo`, `appType`, `appDate`, `appDescription`, `appPrice`) VALUES
(104, '004', 'Cash', '2020-04-02', 'HeadAche', 599.45),
(105, '005', 'CreditCard', '2020-04-06', 'StomatchAche', 500.09),
(106, '006', 'CreditCard', '2020-02-08', 'StomatchAche', 500.09),
(107, '007', 'Cash', '2020-04-04', 'InsertDataviaPostman', 405.78),
(111, '012', 'CreditCard', '2020-05-04', 'Fever', 390.99),
(113, '013', 'Cash', '2020-05-05', 'TesttheAPI', 2099.99),
(114, '014', 'Cash', '2020-04-04', 'SoreThroat', 400.00),
(117, '015', 'CreditCard', '2020-05-05', 'TestAndPutToGitHub', 1000.99),
(122, '016', 'CreditCard', '2020-05-06', 'AJAX', 500.99),
(128, '017', 'CreditCard', '2020-05-06', 'Fever', 400.99),
(129, '018', 'Cash', '2020-05-06', 'Cough', 340.99),
(130, '020', 'Credit Card', '2020-05-06', 'Stomatch Ache', 456.99);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
