DROP DATABASE IF EXISTS superheroSightingTest;
CREATE DATABASE superheroSightingTest;

USE superheroSightingTest;

CREATE TABLE Hero (
	HeroName VARCHAR(50) PRIMARY KEY,
    Description VARCHAR(255) NOT NULL
);

CREATE TABLE Location (
	LocationName VARCHAR(50) PRIMARY KEY,
    Description VARCHAR(255) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(45) NOT NULL,
    State CHAR(2) NOT NULL,
    ZIP CHAR(5) NOT NULL,
    Latitude VARCHAR(10) NOT NULL,
    Longitude VARCHAR(10) NOT NULL
);

CREATE TABLE Superpower (
	SuperpowerName VARCHAR(30) PRIMARY KEY,
    Description VARCHAR(255) NOT NULL
);

CREATE TABLE Organization (
	OrganizationName VARCHAR(100) PRIMARY KEY,
    Description VARCHAR(255) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(45) NOT NULL,
    State CHAR(2) NOT NULL,
    ZIP CHAR(5) NOT NULL,
    Phone CHAR(13) NULL,
    Email VARCHAR(45) NULL
);

CREATE TABLE Sighting (
	SightingId INT PRIMARY KEY AUTO_INCREMENT,
	Date DATETIME NOT NULL,
    LocationName VARCHAR(50) NOT NULL,
	HeroName VARCHAR(50) NOT NULL,
    FOREIGN KEY (LocationName) REFERENCES Location(LocationName),
    FOREIGN KEY (HeroName) REFERENCES Hero(HeroName)
);

CREATE TABLE Hero_Superpower (
	HeroName VARCHAR(50),
    SuperpowerName VARCHAR(30),
    PRIMARY KEY pk_Hero_Superpower(Heroname, SuperpowerName),
    FOREIGN KEY (HeroName) REFERENCES Hero(HeroName),
    FOREIGN KEY (SuperpowerName) REFERENCES Superpower(SuperpowerName)
);

CREATE TABLE Hero_Organization (
	HeroName VARCHAR(50),
    OrganizationName VARCHAR(100),
    PRIMARY KEY pk_Hero_Organization(HeroName, OrganizationName),
    FOREIGN KEY (HeroName) REFERENCES Hero(HeroName),
    FOREIGN KEY (OrganizationName) REFERENCES Organization(OrganizationName)
);    
    
    