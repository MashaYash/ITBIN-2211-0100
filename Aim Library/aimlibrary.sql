-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2024 at 08:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aimlibrary`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `BookNo` varchar(20) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Availability` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`BookNo`, `Author`, `Category`, `Availability`) VALUES
('B0001', 'J.K. Rowlin', 'Horror', 'Yes'),
('B0002', 'Coleen Hoover', 'Romance', 'No'),
('B0003', 'ewrwrwerfwe', 'ewrwe', 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `BorrowID` int(11) NOT NULL,
  `BookID` varchar(50) NOT NULL,
  `StudentID` varchar(50) NOT NULL,
  `Fee` int(11) NOT NULL,
  `Date` date NOT NULL,
  `DueDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`BorrowID`, `BookID`, `StudentID`, `Fee`, `Date`, `DueDate`) VALUES
(6, 'B0003', 'S0001', 125, '2024-06-07', '2024-06-14'),
(11, 'B0002', 'S0006', 3456, '2024-06-13', '2024-06-14');

-- --------------------------------------------------------

--
-- Table structure for table `returnbook`
--

CREATE TABLE `returnbook` (
  `ReturnID` int(11) NOT NULL,
  `BookID` varchar(255) NOT NULL,
  `StudentID` varchar(255) NOT NULL,
  `returnDate` date NOT NULL,
  `elapse` int(11) NOT NULL,
  `fine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `returnbook`
--

INSERT INTO `returnbook` (`ReturnID`, `BookID`, `StudentID`, `returnDate`, `elapse`, `fine`) VALUES
(1, 'B0001', 'S0001', '2024-06-20', 5, 250),
(2, 'B0004', 'S0002', '2024-06-07', 1, 100),
(3, 'B0004', 'S0002', '2024-06-21', 4, 50),
(8, 'B0002', 'S0006', '2024-06-18', 3, 300),
(9, 'B0001', 'S0005', '2024-06-14', 2, 200);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `StudentID` varchar(20) NOT NULL,
  `Sname` varchar(50) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `StudentID`, `Sname`, `Address`, `PhoneNumber`, `Password`) VALUES
(1, 'S0001', 'Ake', 'Sri Lanka', '1234567', '1234'),
(2, 'S0002', 'Masha', 'Gampaha', '234567', '1234'),
(3, 'S0003', 'Selena', 'USA', '028739', '1234'),
(9, 'S0004', 'feewrw', 'vdscsd', '21312', '234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`BorrowID`);

--
-- Indexes for table `returnbook`
--
ALTER TABLE `returnbook`
  ADD PRIMARY KEY (`ReturnID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `borrow`
--
ALTER TABLE `borrow`
  MODIFY `BorrowID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `returnbook`
--
ALTER TABLE `returnbook`
  MODIFY `ReturnID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
