-- Dumping database structure for wood
drop database wood;
CREATE DATABASE IF NOT EXISTS `wood` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wood`;
-- Dumping structure for table wood.produit
CREATE TABLE IF NOT EXISTS `produit` (
                                         `produit_id` int NOT NULL AUTO_INCREMENT,
                                         `name` varchar(40) NOT NULL,
                                         `description` text NOT NULL,
                                         `prix` float NOT NULL,
                                         `qte_stock` int NOT NULL DEFAULT '0',
                                         `qte_order` int NOT NULL DEFAULT '0',
                                         `produit_uuid` varchar(6) NOT NULL,
                                         PRIMARY KEY (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Dumping structure for table wood.client
CREATE TABLE IF NOT EXISTS `client` (
                                        `client_id` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(100) NOT NULL,
                                        `email` varchar(100) NOT NULL,
                                        `phone` varchar(50) NOT NULL,
                                        `adress` varchar(100) NOT NULL,
                                        `client_uuid` varchar(6) NOT NULL,
                                        PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table wood.client: ~3 rows (approximately)
INSERT INTO `client` (`client_id`, `name`, `email`, `phone`, `adress`, `client_uuid`) VALUES
                                                                                          (1, 'Mohammed Benali', 'mohammed.benali@example.com', '+212612345678', 'Rue Hassan II, Casablanca', '05492d'),
                                                                                          (2, 'Fatima El Amrani', 'fatima.elamrani@example.com', '+212623456789', 'Avenue Mohammed V, Rabat', '054934'),
                                                                                          (3, 'Ahmed Belhaj', 'ahmed.belhaj@example.com', '+212634567890', 'Boulevard Moulay Youssef, Tangier', '054936'),
                                                                                          (4, 'Amina Chakir', 'amina.chakir@example.com', '+212645678901', 'Rue Ibn Rochd, Marrakech', '054936'),
                                                                                          (5, 'Hassan El Fassi', 'hassan.elfassi@example.com', '+212656789012', 'Avenue Mohammed VI, Fes', '054937'),
                                                                                          (6, 'Nadia Moussaoui', 'nadia.moussaoui@example.com', '+212667890123', 'Place des Nations Unies, Agadir', '054938'),
                                                                                          (7, 'Karim El Kaddouri', 'karim.elkaddouri@example.com', '+212678901234', 'Rue Ibn Khaldoun, Meknes', '054939'),
                                                                                          (8, 'Loubna El Mansouri', 'loubna.elmansouri@example.com', '+212689012345', 'Boulevard Zerktouni, Oujda', '05493a'),
                                                                                          (9, 'Youssef Bouzid', 'youssef.bouzid@example.com', '+212690123456', 'Avenue Hassan II, Kenitra', '05493b'),
                                                                                          (10, 'Sanaa Benjelloun', 'sanaa.benjelloun@example.com', '+212601234567', 'Rue Mohammed El Baamrani, Tetouan', '05493c');

-- Dumping structure for table wood.commande
CREATE TABLE IF NOT EXISTS `commande` (
                                          `commande_id` int NOT NULL AUTO_INCREMENT,
                                          `date_ajoute` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                          `date_update` timestamp NOT NULL,
                                          `client_id` int DEFAULT NULL,
                                          `commande_status` enum('PREPARATION','COMPLET','ANNULLE') DEFAULT NULL,
                                          `commande_uuid` varchar(6) NOT NULL,
                                          PRIMARY KEY (`commande_id`),
                                          KEY `client_id` (`client_id`),
                                          CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table wood.commande: ~0 rows (approximately)
INSERT INTO `commande` (`commande_id`, `date_ajoute`, `date_update`, `client_id`, `commande_status`, `commande_uuid`) VALUES
                                                                                                                          (1, '2023-12-19 20:08:31', '2023-12-19 20:08:31', 1, 'PREPARATION', '28378b'),
                                                                                                                          (2, '2023-12-19 20:08:31', '2023-12-19 20:08:31', 1, 'COMPLET', '283793'),
                                                                                                                          (3, '2023-12-19 20:08:31', '2023-12-19 20:08:31', 2, 'PREPARATION', '283795'),
                                                                                                                          (4, '2023-12-19 20:08:31', '2023-12-19 20:08:31', 3, 'PREPARATION', '283797');

-- Dumping structure for table wood.commande_produit
CREATE TABLE IF NOT EXISTS `commande_produit` (
                                                  `commande_id` int NOT NULL,
                                                  `produit_id` int NOT NULL,
                                                  PRIMARY KEY (`commande_id`,`produit_id`),
                                                  KEY `produit_id` (`produit_id`),
                                                  CONSTRAINT `commande_produit_ibfk_1` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`commande_id`),
                                                  CONSTRAINT `commande_produit_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`produit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Dumping data for table wood.produit: ~10 rows (approximately)
INSERT INTO `produit` (`produit_id`, `name`, `description`, `prix`, `qte_stock`, `qte_order`, `produit_uuid`) VALUES
                                                                                                                  (1, 'Moroccan Table', 'Handcrafted wooden table inspired by Moroccan design.', 250, 16, 0, '3a12bb'),
                                                                                                                  (2, 'Atlas Carpet', 'Authentic Moroccan carpet with traditional patterns.', 180, 11, 0, '3a12c8'),
                                                                                                                  (3, 'Argan Wood Chair', 'Elegant chair made from Argan wood, known for its durability.', 320, 16, 0, '3a12cb'),
                                                                                                                  (4, 'Safi Ceramic Vase', 'Hand-painted ceramic vase from the city of Safi.', 120, 10, 0, '3a12cf'),
                                                                                                                  (5, 'Fez Lantern', 'Intricate metal lantern inspired by traditional Fez craftsmanship.', 150, 20, 0, '3a12d3'),
                                                                                                                  (6, 'Chefchaouen Pillow Set', 'Set of vibrant pillows inspired by the colors of Chefchaouen.', 90, 10, 0, '3a12d7'),
                                                                                                                  (7, 'Essaouira Hammock', 'Comfortable hammock made with traditional weaving techniques.', 200, 9, 0, '3a12df'),
                                                                                                                  (8, 'Tangier Tea Set', 'Artisanal tea set with intricate patterns from Tangier.', 180, 10, 0, '3a12ea'),
                                                                                                                  (9, 'Marrakech Mirror', 'Decorative mirror featuring designs inspired by Marrakech.', 280, 14, 0, '3a12f9'),
                                                                                                                  (10, 'Atlas Mountains Painting', 'Canvas painting depicting the scenic beauty of the Atlas Mountains.', 300, 8, 0, '3a12fb');
-- Dumping data for table wood.commande_produit: ~0 rows (approximately)
INSERT INTO `commande_produit` (`commande_id`, `produit_id`) VALUES
                                                                 (1, 1),
                                                                 (4, 1),
                                                                 (2, 2),
                                                                 (4, 2),
                                                                 (1, 3),
                                                                 (3, 3),
                                                                 (4, 3),
                                                                 (2, 4),
                                                                 (4, 4),
                                                                 (1, 5),
                                                                 (3, 5),
                                                                 (4, 5),
                                                                 (2, 6),
                                                                 (4, 6),
                                                                 (1, 7),
                                                                 (3, 7),
                                                                 (4, 7),
                                                                 (2, 8),
                                                                 (4, 8),
                                                                 (3, 9),
                                                                 (4, 9),
                                                                 (2, 10),
                                                                 (4, 10);





-- Dumping structure for table wood.user
CREATE TABLE IF NOT EXISTS `user` (
                                      `user_id` int NOT NULL AUTO_INCREMENT,
                                      `user_name` varchar(40) NOT NULL,
                                      `user_email` varchar(50) NOT NULL,
                                      `user_password` varchar(50) NOT NULL,
                                      `user_status_id` int NOT NULL,
                                      `user_status` enum('ADMIN','USER') DEFAULT NULL,
                                      PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table wood.user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;

update client set name = ?, email = ?, phone = ?, adress = ? where client_uuid = ?;
select client_uuid,name,email,adress,phone from client ;
select * from  client where  client_uuid = ?;

SELECT
    c.client_id,
    c.client_uuid,
    c.name AS client_name,
    cmd.commande_id,
    cmd.date_ajoute,
    cmd.commande_uuid,
    cmd.date_update,
    cmd.commande_status,
    p.produit_id,
    p.produit_uuid,
    p.name AS produit_name,
    p.description AS produit_description,
    p.prix

FROM
    commande cmd
        JOIN
    client c ON cmd.client_id = c.client_id
        JOIN
    commande_produit cp ON cmd.commande_id = cp.commande_id
        JOIN
    produit p ON cp.produit_id = p.produit_id

WHERE
        cmd.commande_id = 1;
