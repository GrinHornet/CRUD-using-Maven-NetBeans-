-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2024 at 03:56 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employeeoop`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `assignment_id` int(11) NOT NULL,
  `peripheral_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `date_assigned` varchar(55) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`assignment_id`, `peripheral_id`, `id`, `date_assigned`, `status`) VALUES
(1, 1, 1, '2023-12-30', 0),
(4, 3, 1, '2023-12-30', 0),
(5, 5, 9, '2023-12-30', 0),
(6, 1, 1, '2023-12-31', 1),
(7, 3, 1, '2023-12-31', 0),
(8, 5, 9, '2023-12-31', 1),
(9, 8, 3, '2023-12-30', 1),
(10, 3, 2, '2023-12-31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `mnane` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `usertype` varchar(25) NOT NULL,
  `gender` varchar(15) NOT NULL,
  `contact` varchar(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `fname`, `mnane`, `lname`, `username`, `password`, `usertype`, `gender`, `contact`, `address`, `status`) VALUES
(1, 'Johnny', 'Romagos', 'Zyrus', '1', '1', 'Admin', 'Male', '09123456789', 'Banaybanay, Davao Oriental', 1),
(2, 'was', 'asda', 'asdas', '2', '2', 'Admin', 'Male', 'asd', 'asd', 1),
(3, 'Cordero', '1233', 'Janessa', '3', '3', 'Admin', 'Female', '0566165465', 'Taga Wanang', 1),
(5, '', '', '', '', '', 'Employee', 'Male', '', '', 0),
(6, 'Kugmo', 'K', 'Yoka', '', '', 'Employee', 'Male', '', '', 0),
(7, 'Kugmo', 'K', 'Yokapo', '4', '4', 'Admin', 'Male', '09255456565', 'Bagsak', 1),
(8, 'Kugmo', 'K', 'Yoka', '6', '6', 'Admin', 'Male', '09211452124', 'Maski Asasa', 1),
(9, 'Neil', '', 'Mutia', 'neil123', 'mutia123', 'Admin', 'Male', '09123456789', 'Barangay Rang-ay', 1),
(10, 'Jareth ', '', 'Baur', 'jarjar123', 'baur07', 'Admin', 'Male', '09224433121', 'Barangay,Sanvicente', 1);

-- --------------------------------------------------------

--
-- Table structure for table `peripheral`
--

CREATE TABLE `peripheral` (
  `peripheral_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(55) NOT NULL,
  `category` varchar(55) NOT NULL,
  `date_added` varchar(55) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peripheral`
--

INSERT INTO `peripheral` (`peripheral_id`, `name`, `type`, `category`, `date_added`, `status`) VALUES
(1, 'A4 Tech Keyboard', 'Input', 'Keyboard', '2023-12-29', 2),
(2, 'A4 Tech Mouse', 'Input', 'Mouse', '2023-12-29', 1),
(3, 'Panasonic Mouse', 'Input', 'Mouse', '2023-12-29', 2),
(4, 'A4 Tech Mouse', 'Input', 'Mouse', '2023-12-29', 1),
(5, '210T Printer', 'Output', 'Printer', '2023-12-29', 2),
(6, 'A4Tech', 'Output', 'Scanner', '2023-12-31', 1),
(7, 'Panasonic', 'Output', 'Scanner', '2023-12-31', 1),
(8, 'Taylor Swift', 'Input', 'Keyboard', '2023-12-30', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`assignment_id`),
  ADD KEY `peripheral_id` (`peripheral_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `peripheral`
--
ALTER TABLE `peripheral`
  ADD PRIMARY KEY (`peripheral_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignment`
--
ALTER TABLE `assignment`
  MODIFY `assignment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `peripheral`
--
ALTER TABLE `peripheral`
  MODIFY `peripheral_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`peripheral_id`) REFERENCES `peripheral` (`peripheral_id`),
  ADD CONSTRAINT `assignment_ibfk_2` FOREIGN KEY (`id`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
