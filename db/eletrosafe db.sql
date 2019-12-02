--
-- Script was generated by Devart dbForge Studio for MySQL, Version 7.2.76.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 02/12/2019 18:13:28
-- Server version: 5.5.46
-- Client version: 4.1
--


-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set SQL mode
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Set default database
--
USE eletrosafe;

--
-- Definition for table conserto
--
DROP TABLE IF EXISTS conserto;
CREATE TABLE conserto (
  id INT(11) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) DEFAULT NULL,
  id_loja INT(11) NOT NULL,
  valor DECIMAL(10, 2) DEFAULT 0.00,
  id_contr_garantia INT(11) DEFAULT NULL,
  data DATE DEFAULT NULL,
  chave_NF VARCHAR(255) NOT NULL,
  id_produto INT(11) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 6
AVG_ROW_LENGTH = 3276
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table contrato_garantia
--
DROP TABLE IF EXISTS contrato_garantia;
CREATE TABLE contrato_garantia (
  id INT(11) NOT NULL AUTO_INCREMENT,
  data_vigencia DATE DEFAULT NULL,
  valor DECIMAL(10, 2) DEFAULT 0.00,
  descricao VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table loja
--
DROP TABLE IF EXISTS loja;
CREATE TABLE loja (
  id INT(11) NOT NULL AUTO_INCREMENT,
  cnpj VARCHAR(18) DEFAULT NULL,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(15) DEFAULT NULL,
  site VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table nota_fiscal
--
DROP TABLE IF EXISTS nota_fiscal;
CREATE TABLE nota_fiscal (
  id INT(11) NOT NULL AUTO_INCREMENT,
  chave VARCHAR(255) DEFAULT NULL,
  data DATE DEFAULT NULL,
  valor DECIMAL(10, 2) DEFAULT 0.00,
  nota_fiscal VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table produto
--
DROP TABLE IF EXISTS produto;
CREATE TABLE produto (
  id INT(11) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  valor DECIMAL(10, 2) DEFAULT 0.00,
  datacompra DATE DEFAULT NULL,
  id_loja INT(11) DEFAULT NULL,
  chave_NF VARCHAR(255) NOT NULL,
  id_contr_garantia INT(11) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 56
AVG_ROW_LENGTH = 303
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;