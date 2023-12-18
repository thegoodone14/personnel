CREATE DATABASE IF NOT EXISTS  m2l;

USE m2l;

CREATE TABLE IF NOT EXISTS superadmin (
    superadmin_id INT AUTO_INCREMENT PRIMARY KEY,
    designation VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ligue (
  id_ligue INT AUTO_INCREMENT,
  nom_ligue VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_ligue));

CREATE TABLE IF NOT EXISTS employe (
  id_emp INT AUTO_INCREMENT,
  nom_emp VARCHAR(45) NOT NULL,
  prenom_emp VARCHAR(45) NOT NULL,
  mail_emp VARCHAR(255) NOT NULL,
  password_emp VARCHAR(255) NOT NULL,
  date_arrive DATE,
  date_depart DATE,
  superadmin_id INT,
  id_ligue INT NULL,
  PRIMARY KEY (id_emp),
    FOREIGN KEY (id_ligue)REFERENCES ligue(id_ligue),
     FOREIGN KEY (superadmin_id)REFERENCES superadmin(superadmin_id)
);
