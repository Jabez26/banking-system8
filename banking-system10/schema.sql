-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2025 at 10:12 AM
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
-- Database: `banking-system9`
--

-- --------------------------------------------------------

--
-- Table structure for table `transactionhistory`
--

CREATE TABLE `transactionhistory` (
  `transactionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `amount` double NOT NULL,
  `transactionType` varchar(50) NOT NULL,
  `dateAndTime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionhistory`
--

INSERT INTO `transactionhistory` (`transactionID`, `userID`, `amount`, `transactionType`, `dateAndTime`) VALUES
(1, 3, 500, 'Checking withdraw', '2025-02-19 17:28:16'),
(2, 3, 1000, 'Savings withdraw', '2025-02-19 17:28:48'),
(3, 2, 25000, 'Savings withdraw', '2025-02-19 17:58:58'),
(4, 4, 60000, 'Savings withdraw', '2025-02-20 07:32:11'),
(5, 4, 20000, 'Savings deposit', '2025-02-20 08:34:05'),
(6, 4, 500, 'Savings withdraw', '2025-02-20 10:40:53'),
(7, 4, 500, 'Savings withdraw', '2025-02-20 10:45:14'),
(8, 4, 9000, 'Savings withdraw', '2025-02-20 10:46:02'),
(9, 4, 1000, 'Savings deposit', '2025-02-20 10:47:02'),
(10, 4, 500, 'Penalty', '2025-02-20 10:47:03'),
(11, 4, 0, 'Penalty', '2025-02-20 10:47:03'),
(12, 4, 1500, 'Savings deposit', '2025-02-20 11:27:29'),
(13, 4, 1000, 'Penalty', '2025-02-20 11:27:30'),
(14, 4, 500, 'Penalty', '2025-02-20 11:27:30'),
(15, 4, 0, 'Penalty', '2025-02-20 11:27:39'),
(16, 4, 2500, 'Savings deposit', '2025-02-20 11:34:55'),
(17, 4, 2000, 'Penalty', '2025-02-20 11:34:55'),
(18, 4, 2000, 'Savings deposit', '2025-02-20 11:53:33'),
(19, 4, 4000, 'Savings withdraw', '2025-02-20 11:54:48'),
(20, 4, 2500, 'Savings deposit', '2025-02-20 11:55:00'),
(21, 4, 2000, 'Penalty', '2025-02-20 11:55:01'),
(22, 4, 500, 'Savings deposit', '2025-02-20 11:55:19'),
(23, 4, 0, 'Savings withdraw', '2025-02-20 11:55:45'),
(24, 4, 2499, 'Savings withdraw', '2025-02-20 12:05:45'),
(25, 4, 2499, 'Savings deposit', '2025-02-20 12:05:53'),
(26, 4, 2000, 'Penalty', '2025-02-20 12:05:54'),
(27, 4, 2000, 'Savings withdraw', '2025-02-20 12:09:04'),
(28, 4, 2500, 'Savings deposit', '2025-02-20 12:09:44'),
(29, 4, 2000, 'Penalty', '2025-02-20 12:09:54'),
(30, 4, 500, 'Savings deposit', '2025-02-20 12:17:44'),
(31, 4, 2500, 'Savings withdraw', '2025-02-20 12:18:11'),
(32, 4, 2500, 'Savings deposit', '2025-02-20 12:20:18'),
(33, 4, 2000, 'Penalty', '2025-02-20 12:20:19'),
(34, 4, 0, 'Savings withdraw', '2025-02-20 12:20:29'),
(35, 4, 2000, 'Savings withdraw', '2025-02-20 12:20:56'),
(36, 4, 2500, 'Savings deposit', '2025-02-20 12:24:36'),
(37, 4, 2000, 'Penalty', '2025-02-20 12:24:37'),
(38, 4, 2000, 'Savings withdraw', '2025-02-20 12:29:30'),
(39, 4, 2500, 'Savings deposit', '2025-02-20 12:31:25'),
(40, 4, 2000, 'Penalty', '2025-02-20 12:31:26'),
(41, 4, 2000, 'Savings withdraw', '2025-02-20 12:50:12'),
(42, 4, 2500, 'Savings deposit', '2025-02-20 12:54:22'),
(43, 4, 2000, 'Penalty', '2025-02-20 12:54:22'),
(44, 4, 2000, 'Savings withdraw', '2025-02-20 13:02:48'),
(45, 4, 2500, 'Savings deposit', '2025-02-20 13:03:36'),
(46, 4, 500, 'Penalty', '2025-02-20 13:03:36'),
(47, 4, 2000, 'Savings withdraw', '2025-02-20 13:03:52'),
(48, 4, 2500, 'Savings deposit', '2025-02-20 13:05:54'),
(49, 4, 2000, 'Penalty', '2025-02-20 13:05:54'),
(50, 4, 2000, 'Savings withdraw', '2025-02-20 13:12:31'),
(51, 4, 2500, 'Savings deposit', '2025-02-20 13:13:53'),
(52, 4, 500, 'Penalty', '2025-02-20 13:13:53'),
(53, 4, 2000, 'Savings withdraw', '2025-02-20 13:13:59'),
(54, 4, 2500, 'Savings deposit', '2025-02-20 13:24:07'),
(55, 4, 500, 'Penalty', '2025-02-20 13:24:07'),
(56, 4, 2000, 'Savings withdraw', '2025-02-20 13:24:28'),
(57, 4, 2500, 'Savings deposit', '2025-02-20 13:25:43'),
(58, 4, 500, 'Penalty', '2025-02-20 13:25:43'),
(59, 4, 2000, 'Savings withdraw', '2025-02-20 13:25:46'),
(60, 4, 2500, 'Savings deposit', '2025-02-20 13:29:16'),
(61, 4, 500, 'Penalty', '2025-02-20 13:29:16'),
(62, 4, 0, 'Penalty', '2025-02-20 13:29:35'),
(63, 4, 2000, 'Savings withdraw', '2025-02-20 13:29:35'),
(64, 4, 2500, 'Savings deposit', '2025-02-20 13:33:44'),
(65, 4, 500, 'Penalty', '2025-02-20 13:33:44'),
(66, 4, 2000, 'Savings withdraw', '2025-02-20 13:33:49'),
(67, 4, 2500, 'Savings deposit', '2025-02-20 13:34:49'),
(68, 4, 500, 'Penalty', '2025-02-20 13:34:49'),
(69, 4, 2000, 'Savings withdraw', '2025-02-20 13:34:53'),
(70, 4, 25000, 'Savings deposit', '2025-02-20 13:35:50'),
(71, 4, 500, 'Penalty', '2025-02-20 13:35:50'),
(72, 4, 24500, 'Savings withdraw', '2025-02-20 13:35:58'),
(73, 4, 2500, 'Savings deposit', '2025-02-20 13:36:08'),
(74, 4, 500, 'Penalty', '2025-02-20 13:36:08'),
(75, 4, 2000, 'Savings withdraw', '2025-02-20 13:36:28'),
(76, 4, 2000, 'Savings deposit', '2025-02-20 13:37:59'),
(77, 4, 500, 'Penalty', '2025-02-20 13:37:59'),
(78, 4, 0, 'Penalty', '2025-02-20 13:37:59'),
(79, 4, 1000, 'Savings deposit', '2025-02-20 13:38:19'),
(80, 4, 2500, 'Savings withdraw', '2025-02-20 13:38:25'),
(81, 4, 2500, 'Savings deposit', '2025-02-20 13:38:54'),
(82, 4, 500, 'Penalty', '2025-02-20 13:38:54'),
(83, 4, 2000, 'Savings withdraw', '2025-02-20 13:39:15'),
(84, 4, 2500, 'Savings deposit', '2025-02-20 13:39:20'),
(85, 4, 500, 'Penalty', '2025-02-20 13:39:20'),
(86, 4, 2000, 'Savings withdraw', '2025-02-20 14:28:52'),
(87, 4, 2500, 'Savings deposit', '2025-02-20 14:29:56'),
(88, 4, 500, 'Penalty', '2025-02-20 14:29:56'),
(89, 4, 2000, 'Checking deposit', '2025-02-20 14:33:23'),
(90, 4, 2000, 'Savings deposit', '2025-02-20 14:33:32'),
(91, 4, 2000, 'Savings deposit', '2025-02-20 14:33:42'),
(92, 4, 2000, 'Savings withdraw', '2025-02-20 14:33:46'),
(93, 4, 2000, 'Checking withdraw', '2025-02-20 14:33:52'),
(94, 4, 4000, 'Checking deposit', '2025-02-20 14:34:45'),
(95, 4, 8000, 'Checking withdraw', '2025-02-20 14:41:37'),
(96, 4, 1500, 'Checking deposit', '2025-02-20 14:42:00'),
(97, 4, 150, 'Penalty', '2025-02-20 14:42:02'),
(98, 4, 150, 'Checking deposit', '2025-02-20 14:42:10'),
(99, 4, 0, 'Penalty', '2025-02-20 14:44:38'),
(100, 4, 0, 'Penalty', '2025-02-20 14:47:01'),
(101, 4, 1, 'Savings withdraw', '2025-02-20 14:47:40'),
(102, 4, 0, 'Penalty', '2025-02-20 14:47:40'),
(103, 4, 0, 'Penalty', '2025-02-20 14:47:41'),
(104, 4, 0, 'Penalty', '2025-02-20 14:47:57'),
(105, 4, 1, 'Savings withdraw', '2025-02-20 14:47:59'),
(106, 4, 0, 'Penalty', '2025-02-20 14:47:59'),
(107, 4, 0, 'Penalty', '2025-02-20 14:47:59'),
(108, 4, 502, 'Savings deposit', '2025-02-20 14:48:11'),
(109, 4, 2000, 'Savings withdraw', '2025-02-20 14:48:20'),
(110, 4, 2500, 'Savings deposit', '2025-02-20 14:48:38'),
(111, 4, 500, 'Penalty', '2025-02-20 14:48:38'),
(112, 4, 600, 'Checking withdraw', '2025-02-20 19:27:29'),
(113, 4, 0, 'Penalty', '2025-02-20 19:27:30'),
(114, 4, 0, 'Penalty', '2025-02-20 19:27:30'),
(115, 4, 250, 'Checking deposit', '2025-02-20 19:28:04'),
(116, 4, 150, 'Checking withdraw', '2025-02-20 19:29:47'),
(117, 4, 0, 'Penalty', '2025-02-20 19:30:38'),
(118, 4, 0, 'Penalty', '2025-02-20 19:30:54'),
(119, 4, 0, 'Penalty', '2025-02-20 19:31:19'),
(120, 4, 1500, 'Savings withdraw', '2025-02-20 19:31:25'),
(121, 4, 2500, 'Savings deposit', '2025-02-20 19:34:12'),
(122, 4, 500, 'Penalty', '2025-02-20 19:34:12'),
(123, 4, 2000, 'Checking withdraw', '2025-02-20 19:34:28'),
(124, 4, 1650, 'Checking deposit', '2025-02-20 19:37:16'),
(125, 4, 150, 'Penalty', '2025-02-20 19:37:17'),
(126, 4, 150, 'Checking withdraw', '2025-02-20 19:38:05'),
(127, 4, 0, 'Penalty', '2025-02-20 19:38:05'),
(128, 4, 0, 'Penalty', '2025-02-20 19:38:09'),
(129, 4, 150, 'Checking deposit', '2025-02-20 19:38:18'),
(130, 4, 1, 'Checking withdraw', '2025-02-20 19:38:34'),
(131, 4, 0, 'Penalty', '2025-02-20 19:38:34'),
(132, 4, 0, 'Penalty', '2025-02-20 19:38:35'),
(133, 4, 0, 'Penalty', '2025-02-20 19:38:42'),
(134, 4, 1, 'Checking deposit', '2025-02-20 19:38:45'),
(135, 4, 1, 'Checking withdraw', '2025-02-20 19:38:49'),
(136, 4, 0, 'Penalty', '2025-02-20 19:38:49'),
(137, 4, 0, 'Penalty', '2025-02-20 19:38:50'),
(138, 4, 0, 'Savings Penalty', '2025-02-20 21:16:28'),
(139, 4, 0, 'Savings Penalty', '2025-02-20 21:16:28'),
(140, 4, 501, 'Savings deposit', '2025-02-20 21:16:35'),
(141, 4, 0, 'Checking Penalty', '2025-02-20 21:16:38'),
(142, 4, 4000, 'Savings deposit', '2025-02-21 13:02:42'),
(143, 4, 100, 'Savings deposit', '2025-02-21 13:07:28'),
(144, 4, 100, 'Savings deposit', '2025-02-21 13:21:27'),
(145, 4, 400, 'Send Money', '2025-02-21 13:52:33'),
(146, 2, 400, 'Send Money', '2025-02-21 13:52:33'),
(147, 2, 150, 'Savings Penalty', '2025-02-21 14:32:58'),
(148, 2, 0, 'Checking Penalty', '2025-02-21 14:33:00'),
(149, 2, 0, 'Checking Penalty', '2025-02-21 14:33:03'),
(150, 2, 0, 'Checking Penalty', '2025-02-21 14:33:03'),
(151, 2, 0, 'Savings Penalty', '2025-02-21 14:33:04'),
(152, 2, 0, 'Savings Penalty', '2025-02-21 14:33:05'),
(153, 2, 0, 'Checking Penalty', '2025-02-21 14:33:06'),
(154, 4, 1000, 'Savings withdraw', '2025-02-21 16:19:48'),
(155, 4, 1000, 'Savings deposit', '2025-02-21 16:20:06'),
(156, 4, 1000, 'Checking withdraw', '2025-02-21 16:20:19');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `accountNumber` int(9) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `PIN` int(11) NOT NULL,
  `Balance` double NOT NULL,
  `penalties` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `accountNumber`, `userName`, `PIN`, `Balance`, `penalties`) VALUES
(1, 0, 'testUser', 123456, 100, 0),
(2, 476192358, 'testUser2', 123456, 850, 0),
(3, 364958172, 'testUser3', 112233, 48500, 0),
(4, 251674398, 'testUser4', 987654, 2000, 0),
(5, 182547936, 'testUser5', 123456, 50000, 0),
(6, 492538167, 'testUser6', 987654, 50000, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD PRIMARY KEY (`transactionID`),
  ADD KEY `transactions_UserID_FK` (`userID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=157;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD CONSTRAINT `transactions_UserID_FK` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
