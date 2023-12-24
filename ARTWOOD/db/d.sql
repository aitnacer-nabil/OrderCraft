-- Dumping database structure for wood
drop database wood;
CREATE DATABASE IF NOT EXISTS `wood` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wood`;
-- Dumping structure for table wood.produit
CREATE TABLE IF NOT EXISTS `produit`
(

    `name`         varchar(40) NOT NULL,
    `description`  text        NOT NULL,
    `prix`         float       NOT NULL,
    `qte_stock`    int         NOT NULL DEFAULT '0',
    `produit_uuid` varchar(6)  NOT NULL unique,
    `qte_order` int not null default 0,

    PRIMARY KEY (`produit_uuid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
-- Dumping structure for table wood.client
CREATE TABLE IF NOT EXISTS `client`
(
    `name`        varchar(100) NOT NULL,
    `email`       varchar(100) NOT NULL unique ,
    `phone`       varchar(50)  NOT NULL,
    `adress`      varchar(100) NOT NULL,
    `client_uuid` varchar(6)   NOT NULL unique ,
    PRIMARY KEY (`client_uuid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table wood.client: ~3 rows (approximately)
INSERT INTO `client` ( `name`, `email`, `phone`, `adress`, `client_uuid`)
VALUES ('Mohammed Benali', 'mohammed.benali@example.com', '+212612345678', 'Rue Hassan II, Casablanca', '05492d'),
       ( 'Fatima El Amrani', 'fatima.elamrani@example.com', '+212623456789', 'Avenue Mohammed V, Rabat', '054934'),
       ( 'Ahmed Belhaj', 'ahmed.belhaj@example.com', '+212634567890', 'Boulevard Moulay Youssef, Tangier', '054936'),
       ( 'Amina Chakir', 'amina.chakir@example.com', '+212645678901', 'Rue Ibn Rochd, Marrakech', '054786'),
       ( 'Hassan El Fassi', 'hassan.elfassi@example.com', '+212656789012', 'Avenue Mohammed VI, Fes', '054937'),
       ( 'Nadia Moussaoui', 'nadia.moussaoui@example.com', '+212667890123', 'Place des Nations Unies, Agadir',
        '054938'),
       ('Karim El Kaddouri', 'karim.elkaddouri@example.com', '+212678901234', 'Rue Ibn Khaldoun, Meknes', '054939'),
       ( 'Loubna El Mansouri', 'loubna.elmansouri@example.com', '+212689012345', 'Boulevard Zerktouni, Oujda',
        '05493a'),
       ( 'Youssef Bouzid', 'youssef.bouzid@example.com', '+212690123456', 'Avenue Hassan II, Kenitra', '05493b'),
       ( 'Sanaa Benjelloun', 'sanaa.benjelloun@example.com', '+212601234567', 'Rue Mohammed El Baamrani, Tetouan',
        '05493c');

-- Dumping structure for table wood.commande
CREATE TABLE IF NOT EXISTS `commande`
(
    `date_ajoute`     timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
    `date_update`     timestamp  NOT NULL default CURRENT_TIMESTAMP,
    `client_uuid`     varchar(6) DEFAULT NULL,
    `commande_status` enum ('PREPARATION','COMPLET','ANNULLE') DEFAULT 'PREPARATION',
    `commande_uuid`   varchar(6) NOT NULL unique,
    `commande_total` float not null default '0.0',
    PRIMARY KEY (`commande_uuid`),
    KEY `client_id` (`client_uuid`),
    CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`client_uuid`) REFERENCES `client` (`client_uuid`) on delete cascade  on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table wood.commande: ~0 rows (approximately)
INSERT INTO `commande` ( `date_ajoute`, `date_update`,  `commande_status`, `commande_uuid`)
VALUES ( '2023-12-19 20:08:31', '2023-12-19 20:08:31',  'PREPARATION', '28378b'),
       ('2023-12-19 20:08:31', '2023-12-19 20:08:31',  'COMPLET', '283793'),
       ('2023-12-19 20:08:31', '2023-12-19 20:08:31',  'PREPARATION', '283795'),
       ('2023-12-19 20:08:31', '2023-12-19 20:08:31',  'PREPARATION', '283797');

-- Dumping structure for table wood.commande_produit
CREATE TABLE IF NOT EXISTS `commande_produit`
(
    `commande_uuid`   varchar(6) NOT NULL ,
    `produit_uuid` varchar(6)  NOT NULL ,
    `amount` int not null default 0,
    PRIMARY KEY (`commande_uuid`, `produit_uuid`),
    KEY `produit_id` (`produit_uuid`),
    CONSTRAINT `commande_produit_ibfk_1` FOREIGN KEY (`commande_uuid`) REFERENCES `commande` (`commande_uuid`) on delete cascade  on update cascade,
    CONSTRAINT `commande_produit_ibfk_2` FOREIGN KEY (`produit_uuid`) REFERENCES `produit` (`produit_uuid`) on delete cascade  on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
-- Dumping data for table wood.produit: ~10 rows (approximately)
INSERT INTO `produit` ( `name`, `description`, `prix`, `qte_stock`,  `produit_uuid`)
VALUES ( 'Moroccan Table', 'Handcrafted wooden table inspired by Moroccan design.', 250, 16, '3a12bb'),
       ( 'Atlas Carpet', 'Authentic Moroccan carpet with traditional patterns.', 180, 11,  '3a12c8'),
       ('Argan Wood Chair', 'Elegant chair made from Argan wood, known for its durability.', 320, 16,  '3a12cb'),
       ('Safi Ceramic Vase', 'Hand-painted ceramic vase from the city of Safi.', 120, 10,  '3a12cf'),
       ( 'Fez Lantern', 'Intricate metal lantern inspired by traditional Fez craftsmanship.', 150, 20, '3a12d3'),
       ( 'Chefchaouen Pillow Set', 'Set of vibrant pillows inspired by the colors of Chefchaouen.', 90, 10,
        '3a12d7'),
       ( 'Essaouira Hammock', 'Comfortable hammock made with traditional weaving techniques.', 200, 9,  '3a12df'),
       ( 'Tangier Tea Set', 'Artisanal tea set with intricate patterns from Tangier.', 180, 10,  '3a12ea'),
       ('Marrakech Mirror', 'Decorative mirror featuring designs inspired by Marrakech.', 280, 14,  '3a12f9'),
       ( 'Atlas Mountains Painting', 'Canvas painting depicting the scenic beauty of the Atlas Mountains.', 300, 8,
         '3a12fb');
-- Dumping data for table wood.commande_produit: ~0 rows (approximately)


-- Dumping structure for table wood.user
CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`        int         NOT NULL AUTO_INCREMENT,
    `user_name`      varchar(40) NOT NULL,
    `user_email`     varchar(50) NOT NULL,
    `user_password`  varchar(50) NOT NULL,
    `user_status_id` int         NOT NULL,
    `user_status`    enum ('ADMIN','USER') DEFAULT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table wood.user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;

update client
set name   = ?,
    email  = ?,
    phone  = ?,
    adress = ?
where client_uuid = ?;
select client_uuid, name, email, adress, phone
from client;
select *
from client
where client_uuid = ?;



ALTER TABLE `commande`
    ADD COLUMN `total_amount` INTEGER default 0;

INSERT INTO  `produit` (name, description, prix, produit_uuid,qte_stock) values (?,?,?,?);

SELECT  name, description, prix, produit_uuid,qte_stock from produit;
select * from  client;
select client_uuid,name,email,adress,phone from client;
select * from  client;
SELECT  name, description, prix, produit_uuid,qte_stock from produit;
insert into commande_produit (commande_uuid, produit_uuid) values (?,?);
insert into commande (date_ajoute, date_update, client_uuid, commande_status, commande_uuid, commande_total) values (?,?,?,?,?,?);
select * from produit;
select  * from commande;

SELECT produit.*
FROM commande_produit
         JOIN produit ON commande_produit.produit_uuid = produit.produit_uuid
WHERE commande_produit.commande_uuid = '8549c6';
update commande set commande_status = ? where commande_uuid = ?;
delete from commande where commande_uuid = ?;
