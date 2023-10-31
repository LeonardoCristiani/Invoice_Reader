-- Active: 1693928682567@@127.0.0.1@3306@FATTURAZIONE
DROP DATABASE FATTURAZIONE;
CREATE DATABASE FATTURAZIONE;

use FATTURAZIONE;

CREATE TABLE Log
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Messaggio VARCHAR(255),
    Data DATE
);

CREATE TABLE Aziende
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(255),
    P_I VARCHAR(11),
    Indirizzo VARCHAR(255)
);

CREATE TABLE Tipi_Documenti
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Tipo VARCHAR(10)
);

CREATE TABLE Tipi
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Tipo VARCHAR(255)
);

CREATE Table Banche 
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(255)
);


CREATE Table Conti_Correnti
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Iban VARCHAR(27),
    ID_Banca INT,
        Foreign Key (ID_Banca) REFERENCES Banche(ID),
    ID_Azienda INT,
        Foreign Key (ID_Azienda) REFERENCES Aziende(ID)
);


CREATE Table associazione 
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_Azienda int ,
        Foreign Key (ID_Azienda) REFERENCES Aziende(ID),
    ID_Tipo INT,
        Foreign Key (ID_Tipo) REFERENCES Tipi(ID)
);

CREATE Table Castelletti
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_Conto_Corrente INT,
        Foreign Key (ID_Conto_Corrente) REFERENCES Conti_Correnti(ID),
    Valore_MAX DOUBLE
);

CREATE TABLE Fattura_Attiva
(
    ID int PRIMARY KEY AUTO_INCREMENT,
    Numero_Fattura INT ,
    ID_TD INT,
        Foreign Key (ID_TD) REFERENCES Tipi_Documenti(ID),
    ID_Azienda_Prestatore INT,
        Foreign Key (ID_azienda_Prestatore) REFERENCES Aziende(ID),
    ID_azienda_Committente INT,
        Foreign Key (ID_azienda_Committente) REFERENCES Aziende(ID),
    Imponibile DOUBLE,
    Iva DOUBLE,
    ID_CC INT,
        Foreign Key (ID_CC) REFERENCES Conti_Correnti(ID),
    Ritenuta_Dacconto DOUBLE,
    Causale VARCHAR(255),
    Data_Emissione DATE,
    Data_Scadenza DATE,
    Data_Incasso DATE,
    Anticipata BOOLEAN,
    Stornata BOOLEAN,
    Info VARCHAR(255)
);
