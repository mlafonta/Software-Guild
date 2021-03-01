DROP DATABASE IF EXISTS HotelDB;

CREATE DATABASE HotelDB;

USE HotelDB;

CREATE TABLE Room (
	RoomNumber INT PRIMARY KEY,
    ADAAccess BOOL NOT NULL,
    RoomType VARCHAR(10) NOT NULL
);

CREATE TABLE RoomType (
	RoomType VARCHAR(10) PRIMARY KEY,
    StandardOccupancy INT NOT NULL,
    MaximumOccupancy INT NOT NULL,
    ExtraPersonCharge DECIMAL (5, 2) NULL,
    BasePrice DECIMAL(6, 2) NOT NULL
);

CREATE TABLE RoomAmenity (
	RoomNumber INT,
    AmenityType VARCHAR(50),
    PRIMARY KEY pk_RoomAmenity(RoomNumber, AmenityType)
);

CREATE TABLE Amenity (
	AmenityType VARCHAR(50) PRIMARY KEY,
    Upcharge DECIMAL(7, 2) NULL
);


CREATE TABLE Reservation (
	ReservationId INT PRIMARY KEY AUTO_INCREMENT,
    GuestId INT NOT NULL,
    Adults INT NOT NULL,
    Children INT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    RoomNumber INT NOT NULL,
    TotalCost DECIMAL(7, 2) NOT NULL
);

CREATE TABLE Guest (
	GuestId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(100) NOT NULL,
    State CHAR(2) NOT NULL,
    Zip CHAR(5) NOT NULL,     
    Phone CHAR(14) NOT NULL
);

ALTER TABLE Room
	ADD CONSTRAINT fk_Room_RoomType
		FOREIGN KEY (RoomType)
        REFERENCES RoomType(RoomType);

ALTER TABLE Reservation
	ADD CONSTRAINT fk_Reservation_Guest
		FOREIGN KEY (GuestId)
        REFERENCES Guest(GuestId),
	ADD CONSTRAINT
		FOREIGN KEY (RoomNumber)
        REFERENCES Room(RoomNumber);
        
ALTER TABLE RoomAmenity
	ADD CONSTRAINT fk_RoomAmenity_Room
		FOREIGN KEY (RoomNumber)
        REFERENCES Room(RoomNumber),
	ADD CONSTRAINT fk_RoomAmenity_Amenity
		FOREIGN KEY (AmenityType)
        REFERENCES Amenity(AmenityType);
        