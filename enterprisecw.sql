-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2022 at 02:28 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `enterprisecw`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `ID` int(11) NOT NULL,
  `AddressLine1` varchar(255) NOT NULL,
  `AddressLine2` varchar(255) DEFAULT NULL,
  `AddressLine3` varchar(255) DEFAULT NULL,
  `AddressLine4` varchar(255) DEFAULT NULL,
  `PostCode` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`ID`, `AddressLine1`, `AddressLine2`, `AddressLine3`, `AddressLine4`, `PostCode`, `City`) VALUES
(1, 'London', NULL, NULL, NULL, 'SE10 0YW', 'Greenwich'),
(2, 'St.Pancras St', NULL, NULL, NULL, 'se10 22w', 'London'),
(3, 'City St', NULL, NULL, NULL, 'SE10 8WW', 'London'),
(5, 'Lucy St.', 'House nr 12', '', '', 'SE10 8EJ', 'London');

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `ID` int(10) NOT NULL,
  `AddressID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`ID`, `AddressID`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `branch_orders`
--

CREATE TABLE `branch_orders` (
  `Branch_ID` int(11) NOT NULL,
  `order_Number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `ID` int(10) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Emailaddress` varchar(100) NOT NULL,
  `PhoneNumber` int(100) NOT NULL,
  `username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`ID`, `FirstName`, `LastName`, `Emailaddress`, `PhoneNumber`, `username`, `Password`) VALUES
(1, 'Eric', 'Karlsson', 'Eric7722@gmail.com', 291827222, 'Eric77', '123'),
(3, 'Dan', 'Erc', 'daneRC@gmail.com', 12461552, 'Dan77', '123'),
(4, 'Ernest', 'Kafer', 'Ernest8822@gmail.com', 76644211, 'Ernest21', '123'),
(8, 'Danil', 'Anderson', 'AndersonDanil73@gmail.com', 273221231, 'DanilA', '123'),
(9, 'Daniels', 'Magonis', 'dmagonis77@gmail.com', 123, 'DanielsMagonis', '123');

-- --------------------------------------------------------

--
-- Table structure for table `client_address`
--

CREATE TABLE `client_address` (
  `Client_ID` int(10) NOT NULL,
  `Address_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `collection`
--

CREATE TABLE `collection` (
  `ID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` varchar(255) NOT NULL,
  `Journey` varchar(255) NOT NULL,
  `Frequency` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `collection_crate`
--

CREATE TABLE `collection_crate` (
  `Collection_ID` int(10) NOT NULL,
  `Crate_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `crate`
--

CREATE TABLE `crate` (
  `ID` int(10) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `Item Type` varchar(255) NOT NULL,
  `Shelf` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `crate`
--

INSERT INTO `crate` (`ID`, `ClientID`, `Item Type`, `Shelf`, `Status`) VALUES
(9, 3, 'Electronics', '29a', 'Created');

-- --------------------------------------------------------

--
-- Table structure for table `cratehistory`
--

CREATE TABLE `cratehistory` (
  `ID` int(10) NOT NULL,
  `CrateID` int(10) NOT NULL,
  `Date_Stored` varchar(255) NOT NULL,
  `Date_Accessed` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cratehistory`
--

INSERT INTO `cratehistory` (`ID`, `CrateID`, `Date_Stored`, `Date_Accessed`) VALUES
(9, 9, '2022-03-08', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customerusage`
--

CREATE TABLE `customerusage` (
  `ID` int(10) NOT NULL,
  `Times delivery made` int(255) NOT NULL,
  `Times collection made` int(255) NOT NULL,
  `ClientID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customerusage`
--

INSERT INTO `customerusage` (`ID`, `Times delivery made`, `Times collection made`, `ClientID`) VALUES
(1, 4, 5, 9),
(2, 2, 3, 4),
(3, 0, 0, 3),
(4, 0, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE `delivery` (
  `ID` int(10) NOT NULL,
  `Date` date NOT NULL,
  `Time` varchar(255) NOT NULL,
  `Journey` varchar(255) NOT NULL,
  `Frequency` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `delivery_crate`
--

CREATE TABLE `delivery_crate` (
  `Crate_ID` int(10) NOT NULL,
  `Delivery_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `ID` int(10) NOT NULL,
  `Department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`ID`, `Department`) VALUES
(1, 'Management'),
(2, 'Sales'),
(3, 'Accounting');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `ID` int(10) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `DepartmentID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `firstname`, `lastname`, `username`, `password`, `DepartmentID`) VALUES
(1, 'Adam', 'Stuart', 'AdamStuart', '123', 1),
(2, 'Andis', 'Laipis', 'AndisLaipis', '123', 3),
(3, 'Eiriks', 'Andalis', 'EiriksAndalis', '123', 2);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `ID` int(10) NOT NULL,
  `ClientID` int(255) NOT NULL,
  `Date` date NOT NULL,
  `Amount` int(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`ID`, `ClientID`, `Date`, `Amount`, `Status`) VALUES
(3, 1, '2022-03-14', 500, 'Payed');

-- --------------------------------------------------------

--
-- Table structure for table `itemslist`
--

CREATE TABLE `itemslist` (
  `ID` int(11) NOT NULL,
  `Item` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `itemslist`
--

INSERT INTO `itemslist` (`ID`, `Item`) VALUES
(1, 'Jolka'),
(2, 'Mantinas'),
(3, 'TV'),
(4, 'Jacket'),
(5, 'Files'),
(6, 'Mantinas');

-- --------------------------------------------------------

--
-- Table structure for table `itemslist_crate`
--

CREATE TABLE `itemslist_crate` (
  `Item_ID` int(10) NOT NULL,
  `Crate_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `itemslist_crate`
--

INSERT INTO `itemslist_crate` (`Item_ID`, `Crate_ID`) VALUES
(4, 9),
(5, 9),
(6, 9);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Number` int(10) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(255) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `CollectionDate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Number`, `Date`, `Status`, `ClientID`, `CollectionDate`) VALUES
(1, '2022-03-10', 'In Process', 12, '2022-03-14'),
(2, '2022-03-10', 'In Process', 12, '2022-03-15'),
(3, '2022-03-10', 'In Process', 12, '2022-03-14'),
(4, '2022-03-10', 'In Process', 15, '2022-03-24'),
(5, '2022-03-10', 'In Process', 1, '2022-03-21'),
(6, '2022-03-10', 'In Process', 2, '2022-03-30'),
(8, '2022-03-12', 'In Process', 3, '2022-03-17'),
(9, '2022-03-14', 'In Process', 3, '2022-03-08');

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `ID` int(10) NOT NULL,
  `AddressID` int(255) NOT NULL,
  `Capacity` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `warehouse`
--

INSERT INTO `warehouse` (`ID`, `AddressID`, `Capacity`) VALUES
(1, 3, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `warehouse_crate`
--

CREATE TABLE `warehouse_crate` (
  `Warehouse_ID` int(11) NOT NULL,
  `Crate_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `warehouse_crate`
--

INSERT INTO `warehouse_crate` (`Warehouse_ID`, `Crate_ID`) VALUES
(1, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `AddressID` (`AddressID`);

--
-- Indexes for table `branch_orders`
--
ALTER TABLE `branch_orders`
  ADD KEY `BranchID` (`Branch_ID`,`order_Number`),
  ADD KEY `order_Number` (`order_Number`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `client_address`
--
ALTER TABLE `client_address`
  ADD KEY `ClientID` (`Client_ID`,`Address_ID`),
  ADD KEY `Address_ID` (`Address_ID`);

--
-- Indexes for table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `collection_crate`
--
ALTER TABLE `collection_crate`
  ADD KEY `CollectionID` (`Collection_ID`,`Crate_ID`),
  ADD KEY `Crate_ID` (`Crate_ID`);

--
-- Indexes for table `crate`
--
ALTER TABLE `crate`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cratehistory`
--
ALTER TABLE `cratehistory`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CrateID` (`CrateID`);

--
-- Indexes for table `customerusage`
--
ALTER TABLE `customerusage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ClientID` (`ClientID`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `delivery_crate`
--
ALTER TABLE `delivery_crate`
  ADD KEY `CrateID` (`Crate_ID`,`Delivery_ID`),
  ADD KEY `Delivery_ID` (`Delivery_ID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `DepartmentID` (`DepartmentID`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ClientID` (`ClientID`);

--
-- Indexes for table `itemslist`
--
ALTER TABLE `itemslist`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `itemslist_crate`
--
ALTER TABLE `itemslist_crate`
  ADD KEY `ItemID` (`Item_ID`,`Crate_ID`),
  ADD KEY `Crate_ID` (`Crate_ID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Number`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `AddressID` (`AddressID`);

--
-- Indexes for table `warehouse_crate`
--
ALTER TABLE `warehouse_crate`
  ADD KEY `Warehouse_ID` (`Warehouse_ID`,`Crate_ID`),
  ADD KEY `Crate_ID` (`Crate_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `crate`
--
ALTER TABLE `crate`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cratehistory`
--
ALTER TABLE `cratehistory`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `customerusage`
--
ALTER TABLE `customerusage`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `itemslist`
--
ALTER TABLE `itemslist`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `Number` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `branch`
--
ALTER TABLE `branch`
  ADD CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`AddressID`) REFERENCES `address` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `branch_orders`
--
ALTER TABLE `branch_orders`
  ADD CONSTRAINT `branch_orders_ibfk_1` FOREIGN KEY (`order_Number`) REFERENCES `orders` (`Number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `branch_orders_ibfk_2` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `client_address`
--
ALTER TABLE `client_address`
  ADD CONSTRAINT `client_address_ibfk_1` FOREIGN KEY (`Client_ID`) REFERENCES `client` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `client_address_ibfk_2` FOREIGN KEY (`Address_ID`) REFERENCES `address` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `collection_crate`
--
ALTER TABLE `collection_crate`
  ADD CONSTRAINT `collection_crate_ibfk_1` FOREIGN KEY (`Crate_ID`) REFERENCES `crate` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `collection_crate_ibfk_2` FOREIGN KEY (`Collection_ID`) REFERENCES `collection` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cratehistory`
--
ALTER TABLE `cratehistory`
  ADD CONSTRAINT `cratehistory_ibfk_1` FOREIGN KEY (`CrateID`) REFERENCES `crate` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `customerusage`
--
ALTER TABLE `customerusage`
  ADD CONSTRAINT `customerusage_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `delivery_crate`
--
ALTER TABLE `delivery_crate`
  ADD CONSTRAINT `delivery_crate_ibfk_1` FOREIGN KEY (`Delivery_ID`) REFERENCES `delivery` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `delivery_crate_ibfk_2` FOREIGN KEY (`Crate_ID`) REFERENCES `crate` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `itemslist_crate`
--
ALTER TABLE `itemslist_crate`
  ADD CONSTRAINT `itemslist_crate_ibfk_1` FOREIGN KEY (`Item_ID`) REFERENCES `itemslist` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `itemslist_crate_ibfk_2` FOREIGN KEY (`Crate_ID`) REFERENCES `crate` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD CONSTRAINT `warehouse_ibfk_1` FOREIGN KEY (`AddressID`) REFERENCES `address` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `warehouse_crate`
--
ALTER TABLE `warehouse_crate`
  ADD CONSTRAINT `warehouse_crate_ibfk_1` FOREIGN KEY (`Crate_ID`) REFERENCES `crate` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `warehouse_crate_ibfk_2` FOREIGN KEY (`Warehouse_ID`) REFERENCES `warehouse` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
