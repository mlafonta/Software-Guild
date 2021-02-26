USE HotelDB;

INSERT INTO RoomType (Type, StandardOccupancy, MaximumOccupancy, ExtraPersonCharge, BasePrice) VALUES
	('Single', 2, 2, NULL, 149.99),
    ('Double', 2, 4, 10, 174.99),
    ('Suite', 3, 8, 20, 399.99);
    
INSERT INTO Amenity (Type, Upcharge) VALUES
	('Microwave', NULL),
    ('Jacuzzi', 25),
    ('Refrigerator', NULL),
    ('Oven', NULL);
    
INSERT INTO Guest (FirstName, LastName, Address, City, State, Zip, Phone) VALUES
	('Maxwell', 'Lafontant', '720 6th Ave SE #208', 'Minneapolis', 'MN', '55414', '(319) 721-6722'),
	('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '(291) 553-0508'),
	('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '(478) 277-9632'),
	('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '(308) 494-0198'),
	('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214) 730-0298'),
	('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '(377) 507-0974'),
	('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '(814) 485-2615'),
	('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '(279) 491-0960'),
	('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '(446) 396-6785'),
	('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '(834) 727-1001'),
	('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '(446) 351-6860'),
	('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '(231) 893-2755');
    
INSERT INTO Room (RoomId, ADAAccess, RoomTypeId) VALUES
	(201, 0, 2),
    (202, 1, 2),
    (203, 0, 2),
    (204, 1, 2),
    (205, 0, 1),
    (206, 1, 1),
    (207, 0, 1),
    (208, 1, 1),
	(301, 0, 2),
    (302, 1, 2),
    (303, 0, 2),
    (304, 1, 2),
    (305, 0, 1),
    (306, 1, 1),
    (307, 0, 1),
    (308, 1, 1),
    (401, 1, 3),
    (402, 1, 3);
    
INSERT INTO RoomAmenity (RoomId, AmenityId) VALUES
	(201, 1),
    (201, 2),
    (202, 3),
    (203, 1),
    (203, 2),
    (204, 3),
    (205, 1),
    (205, 2),
    (205, 3),
    (206, 1),
    (206, 3),
    (207, 1),
    (207, 2),
    (207, 3),
    (208, 1),
    (208, 3),
	(301, 1),
    (301, 2),
    (302, 3),
    (303, 1),
    (303, 2),
    (304, 3),
    (305, 1),
    (305, 2),
    (305, 3),
    (306, 1),
    (306, 3),
    (307, 1),
    (307, 2),
    (307, 3),
    (308, 1),
    (308, 3),
    (401, 1),
    (401, 3),
    (401, 4),
    (402, 1),
    (402, 3),
    (402, 4);
    
INSERT INTO Reservation (GuestId, Adults, Children, StartDate, EndDate) VALUES
	(2, 1, NULL, '2023-02-02', '2023-02-04'),
	(3, 2, 1, '2023-02-05', '2023-02-10'),
	(4, 2, NULL, '2023-02-22', '2023-02-24'),
	(5, 2, 2, '2023-03-06', '2023-03-07'),
	(1, 1, 1, '2023-03-17', '2023-03-20'),
	(6, 3, NULL, '2023-03-18', '2023-03-23'),
	(7, 2, 2, '2023-03-29', '2023-03-31'),
	(8, 2, NULL, '2023-03-31', '2023-04-05'),
	(9, 1, NULL, '2023-04-09', '2023-04-13'),
	(10, 1, 1, '2023-04-23', '2023-04-24'),
	(11, 2, 4, '2023-05-30', '2023-06-02'),
	(12, 3, NULL, '2023-06-10', '2023-06-14'),
	(6, 3, NULL, '2023-06-17', '2023-06-18'),
	(1, 2, NULL, '2023-06-28', '2023-07-02'),
	(9, 3, 1, '2023-07-13', '2023-07-14'),
	(10, 4, 2, '2023-07-18', '2023-07-21'),
	(3, 2, 1, '2023-07-28', '2023-07-29'),
	(3, 1, NULL, '2023-08-30', '2023-09-01'),
	(2, 2, NULL, '2023-09-16', '2023-09-17'),
	(5, 2, 2, '2023-09-13', '2023-09-15'),
	(4, 2, 2, '2023-11-22', '2023-11-25'),
	(2, 4, 2, '2023-11-22', '2023-11-25'),
	(11, 2, NULL, '2023-12-24', '2023-12-28');
	
INSERT INTO RoomReservation (ReservationId, RoomId) VALUES
	(1, 308),
    (2, 203),
    (3, 305),
    (4, 201),
    (5, 307),
    (6, 302),
    (7, 202),
    (8, 304),
    (9, 301),
    (10, 207),
    (11, 401),
    (12, 206),
    (12, 208),
    (13, 304),
    (14, 205),
    (15, 204),
    (16, 401),
    (17, 303),
    (18, 305),
    (19, 208),
    (20, 203),
    (21, 401),
    (22, 206),
    (22, 301),
    (23, 302);
    
DELETE FROM RoomReservation
WHERE ReservationId = 8;

DELETE FROM Reservation
WHERE GuestId = 8;

DELETE FROM Guest
WHERE GuestId = 8;