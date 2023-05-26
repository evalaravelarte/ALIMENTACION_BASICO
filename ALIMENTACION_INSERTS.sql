-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema alimentacion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alimentacion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alimentacion` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `alimentacion` ;

-- -----------------------------------------------------
-- Table `alimentacion`.`alimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alimentacion`.`alimento` (
  `idAlimento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `grasas` DOUBLE NOT NULL,
  `grasasSaturadas` DOUBLE NOT NULL,
  `hidratos` DOUBLE NOT NULL,
  `azucar` DOUBLE NOT NULL,
  `proteinas` DOUBLE NOT NULL,
  `sal` DOUBLE NOT NULL,
  `calorias` DOUBLE NOT NULL,
  PRIMARY KEY (`idAlimento`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `alimentacion`.`alimento`
-- -----------------------------------------------------
START TRANSACTION;
USE `alimentacion`;
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Pan', 3.29, 0.7, 50.6, 4.3, 7.6, 0.6, 266);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Chocolate', 30.6, 16.2, 52, 48, 4.1, 0.02, 520);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Aguacate', 15, 13.4, 9, 0.7, 2, 0.7, 160);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Huevo', 11, 3.3, 1.1, 1, 7.2, 0.12, 155);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Platano', 0.3, 0.1, 21.1, 17, 1.1, 1, 91);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Avena', 1.4, 0.2, 12, 0.5, 2.4, 0.4, 68);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Ternera', 8, 3, 0, 0, 24, 0.3, 172);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Arroz', 0.3, 0.1, 28, 0.1, 2.7, 0.1, 130);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Naranja', 0.1, 0, 12, 9, 0.9, 0, 47);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Tomate', 0.1, 0, 3.5, 1.3, 1, 0.9, 22);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Pepino', 0.11, 0, 3.6, 1.6, 0.6, 0.2, 16);
INSERT INTO `alimentacion`.`alimento` (`idAlimento`, `nombre`, `grasas`, `grasasSaturadas`, `hidratos`, `azucar`, `proteinas`, `sal`, `calorias`) VALUES (DEFAULT, 'Pollo', 14, 3.8, 0, 0, 27, 0.8, 239);

COMMIT;

