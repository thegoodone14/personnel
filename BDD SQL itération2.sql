
CREATE TABLE APPLICATION (
    ID_application INT PRIMARY KEY,
    type_application VARCHAR(255)
);


CREATE TABLE EMPLOYE (
    ID_employe INT PRIMARY KEY,
    habilitation VARCHAR(255),
    nom VARCHAR(255),
    prenom VARCHAR(255)
);


CREATE TABLE LIGUE (
    ID_ligue INT PRIMARY KEY,
    nom_ligue VARCHAR(255)
);

