# Student Name: Kaijun Chen
USE RestaurantReviewsApplication;

INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) 
VALUES ('Alice01', 'password1', 'Alice', 'Johnson', 'alice.johnson@email.com', '123-456-7890');
INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) 
VALUES ('Bob02', 'password2', 'Bob', 'Smith', 'bob.smith@email.com', '987-654-3210');
INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) 
VALUES ('Carol03', 'password3', 'Carol', 'Williams', 'carol.williams@email.com', '456-789-0123');
INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) 
VALUES ('Dave04', 'password4', 'Dave', 'Brown', 'dave.brown@email.com', '789-012-3456');
INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) 
VALUES ('Eve05', 'password5', 'Eve', 'Miller', 'eve.miller@email.com', '012-345-6789');

INSERT INTO Companies (CompanyName, About) VALUES 
('McDonald''s', 'A fast-food company known for burgers and fries'),
('Starbucks', 'A coffee company and coffeehouse chain');

-- 5 Restaurants records to cover all cuisine type enum values
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('African Safari', 'Authentic African Dishes', 'Menu1', '9 AM - 9 PM', TRUE, 'AFRICAN', 'Street1', NULL, 'City1', 'State1', 10001, 'McDonald''s');
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('American Diner', 'Classic American Cuisine', 'Menu2', '8 AM - 8 PM', TRUE, 'AMERICAN', 'Street2', NULL, 'City2', 'State2', 20002, 'McDonald''s');
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('Asian Garden', 'Delicious Asian Flavors', 'Menu3', '10 AM - 10 PM', TRUE, 'ASIAN', 'Street3', NULL, 'City3', 'State3', 30003, 'McDonald''s');
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('European Bistro', 'Fine European Dining', 'Menu4', '11 AM - 11 PM', TRUE, 'EUROPEAN', 'Street4', NULL, 'City4', 'State4', 40004, 'Starbucks');
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('Hispanic Haven', 'Rich Hispanic Tastes', 'Menu5', '12 PM - 12 AM', TRUE, 'HISPANIC', 'Street5', NULL, 'City5', 'State5', 50005, 'Starbucks');
INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, Cuisine, Street1, Street2, City, State, Zip, CompanyName)
VALUES ('Chops', 'Rich Korean Tastes', 'Menu6', '12 PM - 12 AM', TRUE, 'ASIAN', 'Street6', NULL, 'City6', 'State6', 60006, 'Starbucks');


INSERT INTO SitDownRestaurant (RestaurantId, Capacity) VALUES 
(1, 50),
(2, 75);

INSERT INTO TakeOutRestaurant (RestaurantId, MaxWaitTime) VALUES 
(3, 15),
(4, 20);

INSERT INTO FoodCartRestaurant (RestaurantId, Licensed) VALUES 
(5, TRUE),
(6, FALSE);

-- Alice leaves a review for the restaurant with RestaurantId 1
INSERT INTO Reviews(Content, Rating, UserName, RestaurantId)
VALUES ('Great food and atmosphere!', 4.5, 'Alice01', 1);
-- Bob leaves a review for the restaurant with RestaurantId 2
INSERT INTO Reviews(Content, Rating, UserName, RestaurantId)
VALUES ('Average service, but the food was good.', 3.5, 'Bob02', 2);

INSERT INTO Recommendations (UserName, RestaurantId) VALUES ('Alice01', 1);
INSERT INTO Recommendations (UserName, RestaurantId) VALUES ('Bob02', 2);

INSERT INTO Reservations (Start, End, Size, UserName, RestaurantId) VALUES 
('2023-10-03 19:00:00', '2023-10-03 21:00:00', 5, 'Alice01', 1),
('2023-10-04 20:00:00', '2023-10-04 22:00:00', 4, 'Bob02', 2);

-- load csv file into creditcards table
LOAD DATA INFILE '/tmp/creditcards.csv' 
INTO TABLE CreditCards
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

/**
Contents of /tmp/creditcards.csv:
CardNumber,Expiration,UserName
"1234567890123456","2024-05-20","Alice01"
"2345678901234567","2025-06-25","Alice01"
"4828349327584357","2026-08-09","Bob02"
"4729458397458385","2027-10-01","Bob02"
*/