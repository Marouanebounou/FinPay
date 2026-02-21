CREATE DATABASE IF NOT EXISTS finpay_db;
USE finpay_db;

CREATE TABLE client (
                        idClient INT AUTO_INCREMENT PRIMARY KEY,
                        clientName VARCHAR(255) NOT NULL,
                        age INT,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        passwordClient VARCHAR(255) NOT NULL
);

CREATE TABLE prestataire (
                             id_pre INT AUTO_INCREMENT PRIMARY KEY,
                             nom VARCHAR(255) NOT NULL,
                             typePre VARCHAR(100),
                             created_at DATE
);

CREATE TABLE facture (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         balance DECIMAL(15, 2) NOT NULL,
                         date DATETIME NOT NULL,
                         status VARCHAR(50),
                         idClient INT,
                         id_pre INT,
                         FOREIGN KEY (idClient) REFERENCES client(idClient) ON DELETE CASCADE,
                         FOREIGN KEY (id_pre) REFERENCES prestataire(id_pre) ON DELETE CASCADE
);

CREATE TABLE paiment (
                         id_pai INT AUTO_INCREMENT PRIMARY KEY,
                         date_pai DATE NOT NULL,
                         balance DECIMAL(15, 2) NOT NULL,
                         id_fact INT,
                         id_client INT,
                         FOREIGN KEY (id_fact) REFERENCES facture(id) ON DELETE CASCADE,
                         FOREIGN KEY (id_client) REFERENCES client(idClient) ON DELETE CASCADE
);

CREATE TABLE statistiaue (
                             id_stat INT AUTO_INCREMENT PRIMARY KEY,
                             id_paiement INT,
                             totalAmount DECIMAL(15, 2) NOT NULL,
                             totalComission DECIMAL(15, 2) NOT NULL,
                             date_op DATE NOT NULL,
                             FOREIGN KEY (id_paiement) REFERENCES paiment(id_pai) ON DELETE CASCADE
);

CREATE TABLE utilisateur (
                             id_user INT AUTO_INCREMENT PRIMARY KEY,
                             email VARCHAR(255) UNIQUE NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             role VARCHAR(50) NOT NULL,
                             id_prestataire INT NULL,
                             FOREIGN KEY (id_prestataire) REFERENCES prestataire(id_pre) ON DELETE SET NULL
);

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE statistiaue;
TRUNCATE TABLE paiment;
TRUNCATE TABLE facture;
TRUNCATE TABLE utilisateur;
TRUNCATE TABLE prestataire;
TRUNCATE TABLE client;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO client (clientName, age, email, passwordClient) VALUES
                                                                ('Yassine', 34, 'yassine@email.com', 'pass123'),
                                                                ('Sara', 28, 'sara@email.com', 'secure456'),
                                                                ('Karim', 42, 'karim@email.com', 'pwd789');

INSERT INTO prestataire (nom, typePre, created_at) VALUES
                                                       ('Tech Solutions', 'IT Services', '2025-01-15'),
                                                       ('Creative Studio', 'Design & Marketing', '2025-06-20');

INSERT INTO utilisateur (email, password, role, id_prestataire) VALUES
                                                                    ('admin@finpay.com', 'adminpass', 'ADMIN', NULL),
                                                                    ('yassine@email.com', 'pass123', 'CLIENT', NULL),
                                                                    ('contact@techsolutions.com', 'techpass', 'PRESTATAIRE', 1);

INSERT INTO facture (balance, date, status, idClient, id_pre) VALUES
                                                                  (1500.00, '2026-01-10 10:00:00', 'Payé', 1, 1),
                                                                  (3200.50, '2026-02-05 14:30:00', 'not payed', 2, 1),
                                                                  (850.00, '2026-02-15 09:15:00', 'En attente', 1, 2),
                                                                  (5000.00, '2026-01-20 16:45:00', 'Payé', 3, 2);

INSERT INTO paiment (date_pai, balance, id_fact, id_client) VALUES
                                                                ('2026-01-12', 1500.00, 1, 1),
                                                                ('2026-02-16', 400.00, 3, 1),
                                                                ('2026-01-22', 5000.00, 4, 3);

INSERT INTO statistiaue (id_paiement, totalAmount, totalComission, date_op) VALUES
                                                                                (1, 1500.00, 30.00, '2026-01-12'),
                                                                                (2, 400.00, 8.00, '2026-02-16'),
                                                                                (3, 5000.00, 100.00, '2026-01-22');