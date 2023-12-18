CREATE DATABASE IF NOT EXISTS  m2l;

USE m2l;

DROP TABLE IF EXISTS employe;
DROP TABLE IF EXISTS ligue;


CREATE TABLE ligue(
    Id_Ligue INT NOT NULL AUTO_INCREMENT,
    Nom_Ligue VARCHAR(40),
    PRIMARY KEY (Id_Ligue)
)ENGINE = INNODB;


CREATE TABLE employe(
    User_Id INT NOT NULL AUTO_INCREMENT,
    Id_Ligue INT,
    Nom VARCHAR(25),
    Prenom VARCHAR(25),
    Mdp VARCHAR (50),
    Date_Arrivee DATE,
    Date_Depart DATE,
    Mail VARCHAR(25),
    PRIMARY KEY (User_Id),
    FOREIGN KEY (Id_Ligue) REFERENCES Ligue (Id_Ligue)
)ENGINE = INNODB;