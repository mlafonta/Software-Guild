USE HOTELDB;

-- 1. Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation dates.

SELECT
	CONCAT(g.FirstName, ' ', g.LastName) GuestName,
    ro.RoomNumber RoomNumber,
    re.StartDate StartDate,
    re.EndDate EndDate
FROM Reservation re
JOIN Guest g ON g.GuestId = re.GuestId
JOIN Room ro ON ro.RoomNumber = re.RoomNumber
WHERE re.EndDate BETWEEN '2023-07-01' AND '2023-07-31';

-- RESULTS:
-- Maxwell Lafontant	205	2023-06-28	2023-07-02
-- Walter Holaway	204	2023-07-13	2023-07-14
-- Wilfred Vise	401	2023-07-18	2023-07-21
-- Bettyann Seery	303	2023-07-28	2023-07-29

-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.

SELECT
	CONCAT(g.FirstName, ' ', g.LastName) GuestName,
    ro.RoomNumber RoomNumber,
    re.StartDate StartDate,
    re.EndDate EndDate
FROM Reservation re
JOIN Guest g ON g.GuestId = re.GuestId
JOIN Room ro ON ro.RoomNumber = re.RoomNumber
JOIN RoomAmenity ra ON ra.RoomNumber = ro.RoomNumber
WHERE ra.AmenityType LIKE 'Jacuzzi';	

-- RESULTS:
-- Maxwell Lafontant	307	2023-03-17	2023-03-20
-- Maxwell Lafontant	205	2023-06-28	2023-07-02
-- Mack Simmer	301	2023-11-22	2023-11-25
-- Bettyann Seery	203	2023-02-05	2023-02-10
-- Bettyann Seery	303	2023-07-28	2023-07-29
-- Bettyann Seery	305	2023-08-30	2023-09-01
-- Duane Cullison	305	2023-02-22	2023-02-24
-- Karie Yang	201	2023-03-06	2023-03-07
-- Karie Yang	203	2023-09-13	2023-09-15
-- Walter Holaway	301	2023-04-09	2023-04-13
-- Wilfred Vise	207	2023-04-23	2023-04-24

-- 3. Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. (Choose a guest's name from the existing data.)

SELECT
	CONCAT(g.FirstName, ' ', g.LastName) GuestName,
    ro.RoomNumber RoomNumber,
    (re.Adults + (IFNULL(re.Children, 0))) TotalGuests,
    re.StartDate StartDate
FROM Reservation re
JOIN Guest g ON g.GuestId = re.GuestId
JOIN Room ro ON ro.RoomNumber = re.RoomNumber
WHERE g.FirstName = 'Bettyann' AND g.LastName = 'Seery';

-- RESULTS:
-- Bettyann Seery	203	3	2023-02-05
-- Bettyann Seery	303	3	2023-07-28
-- Bettyann Seery	305	1	2023-08-30

-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.

SELECT
	ro.RoomNumber RoomNumber,
    re.ReservationId ReservationNumber,    
	-- I'm leaving this here as a comment because I'm proud of the dumb, unnecesary math I did((rt.BasePrice + SUM(IFNULL(a.Upcharge, 0)) + (IFNULL((IF(re.Adults > rt.StandardOccupancy, (re.Adults - rt.StandardOccupancy), 0) * rt.ExtraPersonCharge), 0))) * DATEDIFF(re.EndDate, re.StartDate)) Price
	re.TotalCost TotalCost
From Room ro
LEFT JOIN Reservation re ON re.RoomNumber = ro.RoomNumber
ORDER BY ro.RoomNumber;

-- RESULTS:
-- 	201	4	199.99
-- 202	7	349.98
-- 203	2	999.95
-- 203	21	399.98
-- 204	16	184.99
-- 205	15	699.96
-- 206	12	599.96
-- 206	23	449.97
-- 207	10	174.99
-- 208	13	599.96
-- 208	20	149.99
-- 301	9	799.96
-- 301	24	599.97
-- 302	6	924.95
-- 302	25	699.96
-- 303	18	199.99
-- 304	14	184.99
-- 305	3	349.98
-- 305	19	349.98
-- 306		
-- 307	5	524.97
-- 308	1	299.98
-- 401	11	1199.97
-- 401	17	1259.97
-- 401	22	1199.97
-- 402				 

-- 5. Write a query that returns all the rooms accommodating at 
-- least three guests and that are reserved on any date in April 2023.

SELECT
	ro.RoomNumber RoomNumber,
    (re.Adults + IFNULL(re.Children, 0)) NumberOfGuests,
    re.StartDate CheckInDate,
    re.EndDate CheckOutDate
FROM Room ro
INNER JOIN Reservation re ON re.RoomNumber = ro.RoomNumber
GROUP BY ro.RoomNumber
HAVING NumberOfGuests > 2 AND (re.StartDate BETWEEN '2023-04-01' AND '2023-04-30' OR re.EndDate BETWEEN '2023-04-01' AND '2023-04-30');

-- RESULTS:

-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's name.

SELECT
	CONCAT(g.FirstName, ' ', g.LastName) Name,
    COUNT(re.ReservationId) NumberOfReservations
FROM Guest g
JOIN Reservation re ON g.GuestId = re.GuestId
GROUP BY g.GuestId, g.FirstName, g.LastName
ORDER BY NumberOfReservations DESC, g.LastName;

-- RESULTS:
-- Bettyann Seery	3
-- Mack Simmer	3
-- Duane Cullison	2
-- Walter Holaway	2
-- Maxwell Lafontant	2
-- Aurore Lipton	2
-- Maritza Tilton	2
-- Wilfred Vise	2
-- Karie Yang	2
-- Zachery Luechtefeld	1
-- Joleen Tison	1

-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)

SELECT
	CONCAT(FirstName, ' ', LastName) Name, 
    CONCAT(Address, ', ', City, ', ', State, ' ', Zip) Address,
    Phone
FROM Guest
WHERE Phone = '(834) 727-1001';

-- RESULTS:
-- Wilfred Vise	77 West Surrey Street, Oswego, NY 13126	(834) 727-1001 