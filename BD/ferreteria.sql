-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.41 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para ferreteria
CREATE DATABASE IF NOT EXISTS `ferreteria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ferreteria`;

-- Volcando estructura para tabla ferreteria.buy_orderliness
CREATE TABLE IF NOT EXISTS `buy_orderliness` (
  `orderliness_id` bigint NOT NULL AUTO_INCREMENT,
  `total_amount` decimal(10,2) NOT NULL DEFAULT (0),
  `status` varchar(15) NOT NULL DEFAULT '0',
  `orderliness_date` date NOT NULL,
  `client_id` bigint NOT NULL DEFAULT (0),
  `employee_id` bigint NOT NULL DEFAULT (0),
  PRIMARY KEY (`orderliness_id`),
  KEY `client_id` (`client_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.buy_orderliness: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.client
CREATE TABLE IF NOT EXISTS `client` (
  `client_id` bigint NOT NULL  AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT '0',
  `address` varchar(50) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.client: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '0',
  `load` varchar(50) NOT NULL DEFAULT '0',
  `pay` decimal(10,2) NOT NULL DEFAULT (0),
  `role` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.employee: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.orderliness_detail
CREATE TABLE IF NOT EXISTS `orderliness_detail` (
  `detail_id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL DEFAULT (0),
  `unit_price` decimal(10,2) NOT NULL DEFAULT (0),
  `subtotal` decimal(10,2) NOT NULL DEFAULT (0),
  `orderliness_id` bigint NOT NULL DEFAULT (0),
  `proceeds_id` bigint NOT NULL DEFAULT (0),
  PRIMARY KEY (`detail_id`),
  KEY `orderliness_id` (`orderliness_id`),
  KEY `proceeds_id` (`proceeds_id`),
  CONSTRAINT `orderliness_id` FOREIGN KEY (`orderliness_id`) REFERENCES `buy_orderliness` (`orderliness_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `proceeds_id` FOREIGN KEY (`proceeds_id`) REFERENCES `proceeds` (`proceeds_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.orderliness_detail: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.proceeds
CREATE TABLE IF NOT EXISTS `proceeds` (
  `proceeds_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '0',
  `category` varchar(50) NOT NULL DEFAULT '0',
  `price` decimal(10,2) NOT NULL DEFAULT (0),
  `quantity` int NOT NULL DEFAULT (0),
  `min_stock` int NOT NULL DEFAULT (0),
  `supplier_id` bigint NOT NULL,
  PRIMARY KEY (`proceeds_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.proceeds: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `supplier_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.supplier: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.vending
CREATE TABLE IF NOT EXISTS `vending` (
  `vending_id` bigint NOT NULL AUTO_INCREMENT,
  `total_amount` decimal(10,2) NOT NULL DEFAULT (0),
  `vending_date` datetime NOT NULL DEFAULT (0),
  `client_id` bigint NOT NULL DEFAULT (0),
  `employee_id` bigint NOT NULL DEFAULT (0),
  PRIMARY KEY (`vending_id`),
  KEY `client_id` (`client_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `client-id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee-id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.vending: ~0 rows (aproximadamente)

-- Volcando estructura para tabla ferreteria.vending_detail
CREATE TABLE IF NOT EXISTS `vending_detail` (
  `vdetail_id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL DEFAULT (0),
  `unit_price` decimal(10,2) NOT NULL DEFAULT (0),
  `subtotal` decimal(10,2) NOT NULL DEFAULT (0),
  `vending_id` bigint NOT NULL DEFAULT (0),
  `proceeds_id` bigint NOT NULL DEFAULT (0),
  PRIMARY KEY (`vdetail_id`),
  KEY `vending_id` (`vending_id`),
  KEY `proceeds_id` (`proceeds_id`),
  CONSTRAINT `proceeds-id` FOREIGN KEY (`proceeds_id`) REFERENCES `proceeds` (`proceeds_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vending_id` FOREIGN KEY (`vending_id`) REFERENCES `vending` (`vending_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla ferreteria.vending_detail: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
