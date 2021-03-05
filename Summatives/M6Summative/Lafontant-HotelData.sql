USE HotelDB;

INSERT INTO RoomType (RoomType, StandardOccupancy, MaximumOccupancy, ExtraPersonCharge, BasePrice) VALUES
	('Single', 2, 2, NULL, 149.99),
    ('Double', 2, 4, 10, 174.99),
    ('Suite', 3, 8, 20, 399.99);
    
INSERT INTO Amenity (AmenityType, Upcharge) VALUES
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
    
INSERT INTO Room (RoomNumber, ADAAccess, RoomType) VALUES
	(201, 0, 'Double'),
    (202, 1, 'Double'),
    (203, 0, 'Double'),
    (204, 1, 'Double'),
    (205, 0, 'Single'),
    (206, 1, 'Single'),
    (207, 0, 'Single'),
    (208, 1, 'Single'),
	(301, 0, 'Double'),
    (302, 1, 'Double'),
    (303, 0, 'Double'),
    (304, 1, 'Double'),
    (305, 0, 'Single'),
    (306, 1, 'Single'),
    (307, 0, 'Single'),
    (308, 1, 'Single'),
    (401, 1, 'Suite'),
    (402, 1, 'Suite');
    
INSERT INTO RoomAmenity (RoomNumber, AmenityType) VALUES
	(201, 'Microwave'),
    (201, 'Jacuzzi'),
    (202, 'Refrigerator'),
    (203, 'Microwave'),
    (203, 'Jacuzzi'),
    (204, 'Refrigerator'),
    (205, 'Microwave'),
    (205, 'Jacuzzi'),
    (205, 'Refrigerator'),
    (206, 'Microwave'),
    (206, 'Refrigerator'),
    (207, 'Microwave'),
    (207, 'Jacuzzi'),
    (207, 'Refrigerator'),
    (208, 'Microwave'),
    (208, 'Refrigerator'),
	(301, 'Microwave'),
    (301, 'Jacuzzi'),
    (302, 'Refrigerator'),
    (303, 'Microwave'),
    (303, 'Jacuzzi'),
    (304, 'Refrigerator'),
    (305, 'Microwave'),
    (305, 'Jacuzzi'),
    (305, 'Refrigerator'),
    (306, 'Microwave'),
    (306, 'Refrigerator'),
    (307, 'Microwave'),
    (307, 'Jacuzzi'),
    (307, 'Refrigerator'),
    (308, 'Microwave'),
    (308, 'Refrigerator'),
    (401, 'Microwave'),
    (401, 'Refrigerator'),
    (401, 'Oven'),
    (402, 'Microwave'),
    (402, 'Refrigerator'),
    (402, 'Oven');
    
INSERT INTO Reservation (GuestId, Adults, Children, StartDate, EndDate, RoomNumber, TotalCost) VALUES
	(2, 1, NULL, '2023-02-02', '2023-02-04', 308, 299.98),
	(3, 2, 1, '2023-02-05', '2023-02-10', 203, 999.95),
	(4, 2, NULL, '2023-02-22', '2023-02-24', 305, 349.98),
	(5, 2, 2, '2023-03-06', '2023-03-07', 201, 199.99),
	(1, 1, 1, '2023-03-17', '2023-03-20', 307, 524.97),
	(6, 3, NULL, '2023-03-18', '2023-03-23', 302, 924.95),
	(7, 2, 2, '2023-03-29', '2023-03-31', 202, 349.98),
	(8, 2, NULL, '2023-03-31', '2023-04-05', 304, 874.95),
	(9, 1, NULL, '2023-04-09', '2023-04-13', 301, 799.96),
	(10, 1, 1, '2023-04-23', '2023-04-24', 207, 174.99),
	(11, 2, 4, '2023-05-30', '2023-06-02', 401, 1199.97),
	(12, 2, NULL, '2023-06-10', '2023-06-14', 206, 599.96),
	(12, 1, NULL, '2023-06-10', '2023-06-14', 208, 599.96),
	(6, 3, NULL, '2023-06-17', '2023-06-18', 304, 184.99),
	(1, 2, NULL, '2023-06-28', '2023-07-02', 205, 699.96),
	(9, 3, 1, '2023-07-13', '2023-07-14', 204, 184.99),
	(10, 4, 2, '2023-07-18', '2023-07-21', 401, 1259.97),
	(3, 2, 1, '2023-07-28', '2023-07-29', 303, 199.99),
	(3, 1, NULL, '2023-08-30', '2023-09-01', 305, 349.98),
	(2, 2, NULL, '2023-09-16', '2023-09-17', 208, 149.99),
	(5, 2, 2, '2023-09-13', '2023-09-15', 203, 399.98),
	(4, 2, 2, '2023-11-22', '2023-11-25', 401, 1199.97),
	(2, 2, NULL, '2023-11-22', '2023-11-25', 206, 449.97),
    (2, 2, 2, '2023-11-22', '2023-11-25', 301, 599.97),
	(11, 2, NULL, '2023-12-24', '2023-12-28', 302, 699.96);
	
DELETE FROM Reservation
WHERE GuestId = 8;

DELETE FROM Guest
WHERE GuestId = 8;