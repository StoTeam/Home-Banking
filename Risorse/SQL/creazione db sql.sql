-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema stabanca!
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stabanca!` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `stabanca!` ;

-- -----------------------------------------------------
-- Table `stabanca!`.`amministratore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`amministratore` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `cognome` VARCHAR(255) NULL DEFAULT NULL,
  `telefono` CHAR(11) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `pass` VARCHAR(1024) NULL DEFAULT NULL,
  `tipo_utente` VARCHAR(20) NULL DEFAULT NULL,
  `indirizzo` VARCHAR(255) NULL DEFAULT NULL,
  `livello_accesso` VARCHAR(20) NULL DEFAULT NULL,
  `area_competenza` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`intestatario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`intestatario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 189
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`aziende`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`aziende` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `cognome` VARCHAR(255) NULL DEFAULT NULL,
  `telefono` CHAR(11) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `passw` VARCHAR(1024) NULL DEFAULT NULL,
  `tipo_utente` INT NULL DEFAULT NULL,
  `indirizzo` VARCHAR(255) NULL DEFAULT NULL,
  `ragione_sociale` VARCHAR(255) NULL DEFAULT NULL,
  `partita_iva` CHAR(22) NULL DEFAULT NULL,
  `id_intestatario` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `partita_iva` (`partita_iva` ASC) VISIBLE,
  INDEX `id_intestatario` (`id_intestatario` ASC) VISIBLE,
  CONSTRAINT `aziende_ibfk_1`
    FOREIGN KEY (`id_intestatario`)
    REFERENCES `stabanca!`.`intestatario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`conto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`conto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codice_conto` VARCHAR(255) NULL DEFAULT NULL,
  `iban` CHAR(27) NULL DEFAULT NULL,
  `saldo` DOUBLE NULL DEFAULT NULL,
  `saldo_contabile` DOUBLE NULL DEFAULT NULL,
  `id_intestatario` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codice_conto` (`codice_conto` ASC) VISIBLE,
  UNIQUE INDEX `iban` (`iban` ASC) VISIBLE,
  INDEX `id_intestatario` (`id_intestatario` ASC) VISIBLE,
  CONSTRAINT `conto_ibfk_1`
    FOREIGN KEY (`id_intestatario`)
    REFERENCES `stabanca!`.`intestatario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 96
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`carta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`carta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `is_block` TINYINT(1) NULL DEFAULT NULL,
  `spesa_mensile` DOUBLE NULL DEFAULT NULL,
  `data_rilascio` DATETIME NULL DEFAULT NULL,
  `data_scadenza` DATETIME NULL DEFAULT NULL,
  `codice_sicurezza` CHAR(3) NULL DEFAULT NULL,
  `pin` CHAR(5) NULL DEFAULT NULL,
  `limite` DOUBLE NULL DEFAULT NULL,
  `disponibilita` DOUBLE NULL DEFAULT NULL,
  `uso_pin` TINYINT(1) NULL DEFAULT NULL,
  `conto_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `conto_id` (`conto_id` ASC) VISIBLE,
  CONSTRAINT `carta_ibfk_1`
    FOREIGN KEY (`conto_id`)
    REFERENCES `stabanca!`.`conto` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`movimento_conto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`movimento_conto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_movimento` VARCHAR(255) NULL DEFAULT NULL,
  `importo` DOUBLE NULL DEFAULT NULL,
  `conto_iban_m` CHAR(27) NULL DEFAULT NULL,
  `data_esecuzione` DATETIME NULL DEFAULT NULL,
  `is_eseguito` TINYINT NULL DEFAULT NULL,
  `conto_iban_d` CHAR(27) NULL DEFAULT NULL,
  `data_arrivo` DATETIME NULL DEFAULT NULL,
  `causale` VARCHAR(1024) NULL DEFAULT NULL,
  `carta_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `carta_id` (`carta_id` ASC) VISIBLE,
  INDEX `movimento_conto_ibfk_1` (`conto_iban_m` ASC) VISIBLE,
  INDEX `movimento_conto_ibfk_2` (`conto_iban_d` ASC) VISIBLE,
  CONSTRAINT `movimento_conto_ibfk_1`
    FOREIGN KEY (`conto_iban_m`)
    REFERENCES `stabanca!`.`conto` (`iban`),
  CONSTRAINT `movimento_conto_ibfk_2`
    FOREIGN KEY (`conto_iban_d`)
    REFERENCES `stabanca!`.`conto` (`iban`),
  CONSTRAINT `movimento_conto_ibfk_3`
    FOREIGN KEY (`carta_id`)
    REFERENCES `stabanca!`.`carta` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`prestito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`prestito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `importo` DOUBLE NULL DEFAULT NULL,
  `dovuti` DOUBLE NULL DEFAULT NULL,
  `pagati` DOUBLE NULL DEFAULT NULL,
  `tan` DOUBLE NULL DEFAULT NULL,
  `taeg` DOUBLE NULL DEFAULT NULL,
  `tempo` INT NULL DEFAULT NULL,
  `is_fisso` TINYINT(1) NULL DEFAULT NULL,
  `is_approvato` TINYINT(1) NULL DEFAULT NULL,
  `data_inizio` DATE NULL DEFAULT NULL,
  `data_fine` DATE NULL DEFAULT NULL,
  `data_rata` DATE NULL DEFAULT NULL,
  `conto_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `conto_id` (`conto_id` ASC) VISIBLE,
  CONSTRAINT `prestito_ibfk_1`
    FOREIGN KEY (`conto_id`)
    REFERENCES `stabanca!`.`conto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stabanca!`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stabanca!`.`utente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `cognome` VARCHAR(255) NULL DEFAULT NULL,
  `telefono` CHAR(11) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `pass` VARCHAR(1024) NULL DEFAULT NULL,
  `tipo_utente` VARCHAR(20) NULL DEFAULT NULL,
  `indirizzo` VARCHAR(255) NULL DEFAULT NULL,
  `codice_fiscale` CHAR(16) NULL DEFAULT NULL,
  `id_intestatario` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `codice_fiscale` (`codice_fiscale` ASC) VISIBLE,
  INDEX `id_intestatario` (`id_intestatario` ASC) VISIBLE,
  CONSTRAINT `utente_ibfk_1`
    FOREIGN KEY (`id_intestatario`)
    REFERENCES `stabanca!`.`intestatario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 159
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
