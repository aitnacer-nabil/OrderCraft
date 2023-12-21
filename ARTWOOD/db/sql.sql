CREATE TABLE `produit` (
                                         `produit_id` int NOT NULL AUTO_INCREMENT,
                                         `name` varchar(40) NOT NULL,
    `description` text NOT NULL,
    `prix` float NOT NULL,
    `qte_stock` int NOT NULL DEFAULT '0',
    `qte_order` int NOT NULL DEFAULT '0',
    `produit_uuid` varchar(6) NOT NULL,
    PRIMARY KEY (`produit_id`)
    )
CREATE TABLE IF NOT EXISTS `client` (
                                        `client_id` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `phone` varchar(50) NOT NULL,
    `adress` varchar(100) NOT NULL,
    `client_uuid` varchar(6) NOT NULL,
    PRIMARY KEY (`client_id`)
    )
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
    )


CREATE TABLE IF NOT EXISTS `commande_produit` (
                                                  `commande_id` int NOT NULL,
                                                  `produit_id` int NOT NULL,
                                                  PRIMARY KEY (`commande_id`,`produit_id`),
    KEY `produit_id` (`produit_id`),
    CONSTRAINT `commande_produit_ibfk_1` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`commande_id`),
    CONSTRAINT `commande_produit_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`produit_id`)
    )
