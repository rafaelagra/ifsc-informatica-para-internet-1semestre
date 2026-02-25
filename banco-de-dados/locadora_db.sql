-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema locadora_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema locadora_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `locadora_db` DEFAULT CHARACTER SET utf8 ;
USE `locadora_db` ;

-- -----------------------------------------------------
-- Table `locadora_db`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora_db`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `locadora_db`.`Cliente` (
  `CPF` VARCHAR(11) NOT NULL,
  `RG` VARCHAR(20) NOT NULL,
  `Nome` VARCHAR(100) NOT NULL,
  `Endereco` VARCHAR(200) NOT NULL,
  `CNH` VARCHAR(20) NULL,
  `DataNascimento` DATE NOT NULL,
  PRIMARY KEY (`CPF`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locadora_db`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora_db`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `locadora_db`.`Categoria` (
  `Codigo` INT NOT NULL,
  `Nome` VARCHAR(50) NOT NULL,
  `PrecoDiaria` DECIMAL(10,2) NOT NULL,
  `Descricao` VARCHAR(255) NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Carro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora_db`.`Carro` ;

CREATE TABLE IF NOT EXISTS `locadora_db`.`Carro` (
  `Placa` VARCHAR(7) NOT NULL,
  `Chassi` VARCHAR(50) NOT NULL,
  `Marca` VARCHAR(50) NOT NULL,
  `Modelo` VARCHAR(50) NOT NULL,
  `Ano` INT NOT NULL,
  `Cor` VARCHAR(20) NOT NULL,
  `Categoria_Codigo` INT NOT NULL,
  PRIMARY KEY (`Placa`),
  UNIQUE INDEX `Chassi_UNIQUE` (`Chassi` ASC) VISIBLE,
  INDEX `fk_Carro_Categoria_idx` (`Categoria_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Carro_Categoria`
    FOREIGN KEY (`Categoria_Codigo`)
    REFERENCES `mydb`.`Categoria` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locadora_db`.`Manutencao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora_db`.`Manutencao` ;

CREATE TABLE IF NOT EXISTS `locadora_db`.`Manutencao` (
  `ID_Manutencao` INT NOT NULL AUTO_INCREMENT,
  `DataServico` DATE NOT NULL,
  `Valor` DECIMAL(10,2) NOT NULL,
  `DescricaoServico` VARCHAR(255) NOT NULL,
  `NomeOficina` VARCHAR(100) NOT NULL,
  `Carro_Placa` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`ID_Manutencao`),
  INDEX `fk_Manutencao_Carro1_idx` (`Carro_Placa` ASC) VISIBLE,
  CONSTRAINT `fk_Manutencao_Carro1`
    FOREIGN KEY (`Carro_Placa`)
    REFERENCES `mydb`.`Carro` (`Placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locadora_db`.`Locacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora_db`.`Locacao` ;

CREATE TABLE IF NOT EXISTS `locadora_db`.`Locacao` (
  `ID_Locacao` INT NOT NULL AUTO_INCREMENT,
  `DataHoraRetirada` DATETIME NOT NULL,
  `DataHoraDevolucao` DATETIME NULL,
  `Cliente_CPF` VARCHAR(11) NOT NULL,
  `Carro_Placa` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`ID_Locacao`),
  INDEX `fk_Locacao_Cliente1_idx` (`Cliente_CPF` ASC) VISIBLE,
  INDEX `fk_Locacao_Carro1_idx` (`Carro_Placa` ASC) VISIBLE,
  CONSTRAINT `fk_Locacao_Cliente1`
    FOREIGN KEY (`Cliente_CPF`)
    REFERENCES `mydb`.`Cliente` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Locacao_Carro1`
    FOREIGN KEY (`Carro_Placa`)
    REFERENCES `mydb`.`Carro` (`Placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
