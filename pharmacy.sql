
-- 1 ========== cash_flow ==============
  DROP TABLE IF EXISTS `cash_flow`;
  CREATE TABLE IF NOT EXISTS `cash_flow` (
      `id_cash_flow` int(11) NOT NULL AUTO_INCREMENT,
      `purpose_of_cash` varchar(50) NOT NULL,
      `total` decimal(13,2) NOT NULL,
      `id_delivery` int(11) NOT NULL,
      `id_purch` int(11) NOT NULL,
      -- `id_balance` int(11) NOT NULL,
      PRIMARY KEY (`id_cash_flow`),
      FOREIGN KEY (`id_delivery`) REFERENCES `delivery`(`id_delivery`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
      FOREIGN KEY (`id_purch`) REFERENCES `purchase`(`id_purch`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 2 ========== balance ==============
--change date id AUTO_INCREMENT to date
  DROP TABLE IF EXISTS `balance`;
  CREATE TABLE IF NOT EXISTS `balance` (
    `id_balance` int(11) NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `income` decimal(13,2) NOT NULL,
    `spending` decimal(13,2) NOT NULL,
    `cash_balance` decimal(13,2) NOT NULL,
    `id_cash_flow` int(11) NOT NULL,
    `id_pharmacy` int(11) NOT NULL,
    PRIMARY KEY (`id_balance`),
    FOREIGN KEY (`id_cash_flow`) REFERENCES `cash_flow`(`id_cash_flow`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy`(`id_pharmacy`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE 
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 3 ========== pharmacy ==============
  DROP TABLE IF EXISTS `pharmacy`;
  CREATE TABLE IF NOT EXISTS `pharmacy` (
    `id_pharmacy` int(11) NOT NULL AUTO_INCREMENT,
    `pharm_title` varchar(50) NOT NULL,
    `address` varchar(255) NOT NULL,
    `extra` double NOT NULL,
    PRIMARY KEY (`id_pharmacy`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 4 ========== purchase ==============
  DROP TABLE IF EXISTS `purchase`;
  CREATE TABLE IF NOT EXISTS `purchase` (
    `id_purch` int(11) NOT NULL AUTO_INCREMENT,
    `date` datetime NOT NULL,
    `id_prescr` int(11) NOT NULL,
    `id_pharmacy` int(11) NOT NULL,
    `id_patient` int(11) NOT NULL,
    PRIMARY KEY (`id_purch`),
    FOREIGN KEY (`id_prescr`) REFERENCES `prescription`(`id_prescr`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy`(`id_pharmacy`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_patient`) REFERENCES `patient`(`id_patient`)
      ON DELETE NO ACTION
    ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 5 ========== delivery ==============
  DROP TABLE IF EXISTS `delivery`;
  CREATE TABLE IF NOT EXISTS `delivery` (
    `id_delivery` int(11) NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `id_pharmacy` int(11) NOT NULL,
    -- `id_cash_flow` int(11) NOT NULL,
    PRIMARY KEY (`id_delivery`),
    FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy`(`id_pharmacy`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 6 ========== medicine ==============
  DROP TABLE IF EXISTS `medicine`;
  CREATE TABLE IF NOT EXISTS `medicine` (
    `id_medicine` int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `producer` varchar(255) NOT NULL,
    `box_price` decimal(13,2) NOT NULL,
    `quantity_per_box` int(11) NOT NULL,
    PRIMARY KEY (`id_medicine`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 7 ========== delivery_medicine ==============
  DROP TABLE IF EXISTS `delivery_medicine`;
  CREATE TABLE IF NOT EXISTS `delivery_medicine` (
    `id_delivery` int(11) NOT NULL,
    `id_medicine` int(11) NOT NULL,
    `box_quantity` int(11) NOT NULL,
    FOREIGN KEY (`id_medicine`) REFERENCES `medicine`(`id_medicine`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_delivery`) REFERENCES `delivery`(`id_delivery`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 8 ========== pharmacy_medicine ==============
  DROP TABLE IF EXISTS `pharmacy_medicine`;
  CREATE TABLE IF NOT EXISTS `pharmacy_medicine` (
    `id_pharmacy` int(11) NOT NULL,
    `id_medicine` int(11) NOT NULL,
    `pack_price` decimal(13,2) NOT NULL,
-- purpose of this date? what "date"? sold_date? 
-- It's day of changing price!!!
    `date_of_change` date NOT NULL,
    `pack_quantity` int(11) NOT NULL,
    PRIMARY KEY (`id_pharmacy`, `id_medicine`, `date_of_change`),
    FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy`(`id_pharmacy`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_medicine`) REFERENCES `medicine`(`id_medicine`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 9 ========== doctor ==============
  DROP TABLE IF EXISTS `doctor`;
  CREATE TABLE IF NOT EXISTS `doctor` (
    `id_doctor` int(11) NOT NULL AUTO_INCREMENT,
    `surname` varchar(50) NOT NULL,
    `name` varchar(50) NOT NULL,
    `speciality` varchar(50) NOT NULL,
    `years_of_practice` int(11) NOT NULL,
    PRIMARY KEY (`id_doctor`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 10 ========== patient ==============
  DROP TABLE IF EXISTS `patient`;
  CREATE TABLE IF NOT EXISTS `patient` (
    `id_patient` int(11) NOT NULL AUTO_INCREMENT,
    `surname` varchar(50) NOT NULL,
    `name` varchar(50) NOT NULL,
    `date_of_birth` date NOT NULL,
    `phone` varchar(16) NOT NULL,
    PRIMARY KEY (`id_patient`),
    UNIQUE KEY `phone` (`phone`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 11 ========== prescription ==============
  DROP TABLE IF EXISTS `prescription`;
  CREATE TABLE IF NOT EXISTS `prescription` (
    `id_prescr` int(11) NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `id_doctor` int(11) NOT NULL,
    `id_patient` int(11) NOT NULL,
    PRIMARY KEY (`id_prescr`),
    FOREIGN KEY (`id_doctor`)  REFERENCES `doctor`(`id_doctor`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_patient`) REFERENCES `patient`(`id_patient`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 12 ========== prescr_medicine ==============
  DROP TABLE IF EXISTS `prescr_medicine`;
  CREATE TABLE IF NOT EXISTS `prescr_medicine` (
    `id_prescr` int(11) NOT NULL,
    `id_medicine` int(11) NOT NULL,
    `pack_quantity` int(11) NOT NULL,
    `pack_bought` int(11) DEFAULT NULL,
    FOREIGN KEY (`id_prescr`) REFERENCES `prescription`(`id_prescr`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_medicine`) REFERENCES `medicine`(`id_medicine`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  -- 13 ========== purchase_medicine ==============
  DROP TABLE IF EXISTS `purch_medicine`;
  CREATE TABLE IF NOT EXISTS `purch_medicine` (
    `id_purch` int(11) NOT NULL,
    `id_medicine` int(11) NOT NULL,
    `pack_quantity` int(11) NOT NULL,
    FOREIGN KEY (`id_purch`) REFERENCES `purchase`(`id_purch`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE,
    FOREIGN KEY (`id_medicine`) REFERENCES `medicine`(`id_medicine`)
      ON DELETE NO ACTION
      ON UPDATE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

