DROP DATABASE IF EXISTS HotelDB;

CREATE DATABASE HotelDB;

USE HotelDB;

CREATE TABLE Room (
	RoomId INT PRIMARY KEY,
    ADAAccess BOOL NOT NULL,
    RoomTypeId INT NOT NULL
);

CREATE TABLE RoomType (
	RoomTypeId INT PRIMARY KEY AUTO_INCREMENT,
    Type VARCHAR(10) NOT NULL,
    StandardOccupancy INT NOT NULL,
    MaximumOccupancy INT NOT NULL,
    ExtraPersonCharge DECIMAL (7, 2) NULL,
    BasePrice DECIMAL(7, 2) NOT NULL
);

CREATE TABLE RoomAmenity (
	RoomId INT,
    AmenityId INT
);

CREATE TABLE Amenity (
	AmenityId INT PRIMARY KEY AUTO_INCREMENT,
    Type VARCHAR(50) NOT NULL,
    Upcharge DECIMAL(7, 2) NULL
);

CREATE TABLE RoomReservation (
	ReservationId INT,
    RoomId INT
);

CREATE TABLE Reservation (
	ReservationId INT PRIMARY KEY AUTO_INCREMENT,
    GuestId INT NOT NULL,
    Adults INT NOT NULL,
    Children INT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL
);

CREATE TABLE Guest (
	GuestId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(100) NOT NULL,
    State VARCHAR(20) NOT NULL,
    Zip VARCHAR(10) NOT NULL,
     
    Phone VARCHAR(15) NOT NULL
);

ALTER TABLE Room
	ADD CONSTRAINT fk_Room_RoomType
		FOREIGN KEY (RoomTypeId)
        REFERENCES RoomType(RoomTypeId);

ALTER TABLE Reservation
	ADD CONSTRAINT fk_Reservation_Guest
		FOREIGN KEY (GuestId)
        REFERENCES Guest(GuestId);
        
ALTER TABLE RoomAmenity
	ADD CONSTRAINT fk_RoomAmenity_Room
		FOREIGN KEY (RoomId)
        REFERENCES Room(RoomId),
	ADD CONSTRAINT fk_RoomAmenity_Amenity
		FOREIGN KEY (AmenityId)
        REFERENCES Amenity(AmenityId);
        
ALTER TABLE RoomReservation
	ADD CONSTRAINT fk_RoomReservation_Room
    	FOREIGN KEY (RoomId)
        REFERENCES Room(RoomId),
	ADD CONSTRAINT fk_RoomReservation_Reservation
		FOREIGN KEY (ReservationId)
        REFERENCES Reservation(ReservationId);
