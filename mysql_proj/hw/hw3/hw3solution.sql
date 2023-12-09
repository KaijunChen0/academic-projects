# Student: Kaijun Chen
# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS ReviewApplication;
USE ReviewApplication;

DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS FoodCartRestaurant;
DROP TABLE IF EXISTS TakeOutRestaurant;
DROP TABLE IF EXISTS SitDownRestaurant;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Companies;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
  UserName VARCHAR(255),
  Password VARCHAR(255) NOT NULL,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Phone VARCHAR(255),
  CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);

CREATE TABLE CreditCards (
  CardNumber BIGINT,
  Expiration DATE NOT NULL,
  UserName VARCHAR(255) NOT NULL,
  CONSTRAINT pk_CreditCards_CardNumber PRIMARY KEY (CardNumber),
  CONSTRAINT fk_CreditCards_Username FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Companies (
  CompanyName VARCHAR(255),
  About VARCHAR(1024),
  CONSTRAINT pk_Companies_CompanyName PRIMARY KEY (CompanyName)
);

CREATE TABLE Restaurants (
  RestaurantId INT AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Description VARCHAR(1024) NOT NULL,
  Menu VARCHAR(1024) NOT NULL,
  Hours VARCHAR(1024) NOT NULL,
  Active BOOLEAN NOT NULL,
  CuisineType ENUM('AFRICAN', 'AMERICAN', 'ASIAN', 'EUROPEAN', 'HISPANIC'),
  Street1 VARCHAR(255) NOT NULL,
  Street2 VARCHAR(255),
  City VARCHAR(255) NOT NULL,
  State VARCHAR(255) NOT NULL,
  Zip INT NOT NULL,  # NOTE: VARCHAR is acceptable too.
  CompanyName VARCHAR(255),
  CONSTRAINT pk_Restaurants_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_Restaurants_CompanyName FOREIGN KEY (CompanyName)
    REFERENCES Companies(CompanyName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE SitDownRestaurant (
  RestaurantId INT,
  Capacity INT NOT NULL,
  CONSTRAINT pk_SitDownRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_SitDownRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants(RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TakeOutRestaurant (
  RestaurantId INT,
  MaxWaitTime INT NOT NULL,
  CONSTRAINT pk_TakeOutRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_TakeOutRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants(RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE FoodCartRestaurant (
  RestaurantId INT,
  Licensed BOOLEAN NOT NULL,
  CONSTRAINT pk_FoodCartRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_FoodCartRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants(RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reviews (
  ReviewId INT AUTO_INCREMENT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  Content VARCHAR(1024) NOT NULL,
  Rating DECIMAL(2,1) NOT NULL,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT uq_Reviews_Review UNIQUE (UserName,RestaurantId),
  CONSTRAINT fk_Reviews_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants(RestaurantId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Recommendations (
  RecommendationId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
  CONSTRAINT fk_Recommendations_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Recommendations_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants(RestaurantId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Reservations (
  ReservationId INT AUTO_INCREMENT,
  Start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  End TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  Size INT NOT NULL,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Reservations_ReservationId PRIMARY KEY (ReservationId),
  CONSTRAINT fk_Reservations_UserName FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Reservations_RestaurantId FOREIGN KEY (RestaurantId)
    REFERENCES SitDownRestaurant(RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

USE ReviewApplication;

INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)
  VALUES('Bruce','password','Bruce','C','bruce@mail.com','5555555');
INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)
  VALUES('TT','password','Tony','D','tony@mail.com','5555555');
INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)
  VALUES('DK','password','Daniel','K','dan@mail.com','5555555');
INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)
  VALUES('James','password','James','M','james@mail.com','5555555');
INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)
  VALUES('Steve','password','Steve','N','steve@mail.com','5555555');


INSERT INTO Companies(CompanyName,About)
  VALUES('company1','about company1');
INSERT INTO Companies(CompanyName,About)
  VALUES('company2','about company2');
INSERT INTO Companies(CompanyName,About)
  VALUES('company3','about company3');


INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant1','about restaurant','menu','hours',TRUE,'AFRICAN','street1','street2','seattle','wa',98195,'company1');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant2','about restaurant','menu','hours',TRUE,'AMERICAN','street1','street2','seattle','wa',98195,'company1');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant3','about restaurant','menu','hours',TRUE,'ASIAN','street1','street2','seattle','wa',98195,'company1');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant4','about restaurant','menu','hours',TRUE,'EUROPEAN','street1','street2','seattle','wa',98195,'company1');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant5','about restaurant','menu','hours',TRUE,'HISPANIC','street1','street2','seattle','wa',98195,'company1');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant6','about restaurant','menu','hours',TRUE,'HISPANIC','street1','street2','bellevue','wa',98008,'company2');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant7','about restaurant','menu','hours',TRUE,'HISPANIC','street1','street2','bellevue','wa',98008,'company2');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant8','about restaurant','menu','hours',TRUE,'HISPANIC','street1','street2','bellevue','wa',98008,'company2');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant9','about restaurant','menu','hours',FALSE,'AMERICAN','street1','street2','bellevue','wa',98008,'company2');
INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName)
  VALUES('restaurant10','about restaurant','menu','hours',TRUE,'AMERICAN','street1','street2','bellevue','wa',98008,'company3');


INSERT INTO SitDownRestaurant(RestaurantId,Capacity)
  VALUES(1,100);
INSERT INTO SitDownRestaurant(RestaurantId,Capacity)
  VALUES(2,200);
INSERT INTO SitDownRestaurant(RestaurantId,Capacity)
  VALUES(3,200);

INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime)
  VALUES(4,60);
INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime)
  VALUES(5,90);

INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)
  VALUES(6,TRUE);
INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)
  VALUES(7,TRUE);
INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)
  VALUES(8,TRUE);
INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)
  VALUES(9,TRUE);
INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)
  VALUES(10,FALSE);


INSERT INTO Reviews(Content,Rating,UserName,RestaurantId)
  VALUES('Delightful!',5.0,'Bruce',1);
INSERT INTO Reviews(Content,Rating,UserName,RestaurantId)
  VALUES('Superb!',5.0,'Bruce',2);
INSERT INTO Reviews(Content,Rating,UserName,RestaurantId)
  VALUES('Superb!',5.0,'Bruce',9);
INSERT INTO Reviews(Content,Rating,UserName,RestaurantId)
  VALUES('Not good',1.0,'James',9);
INSERT INTO Reviews(Content,Rating,UserName,RestaurantId)
  VALUES('Not good at all',1.0,'James',10);


INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('Bruce',1);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('Bruce',2);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('Bruce',3);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('Bruce',4);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('Bruce',5);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('DK',2);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('DK',3);
INSERT INTO Recommendations(UserName,RestaurantId)
  VALUES('TT',3);


INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-09-05 18:30:00','2015-09-05 20:00:00',2,'Bruce',1);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-09-12 18:30:00','2015-09-12 20:00:00',2,'Bruce',1);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-09-19 18:30:00','2015-09-19 20:00:00',2,'Bruce',1);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-09-20 18:30:00','2015-09-20 20:00:00',2,'Bruce',2);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-09-21 18:30:00','2015-09-21 20:00:00',2,'Bruce',2);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-10-01 18:30:00','2015-10-01 20:00:00',2,'TT',3);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-10-02 18:30:00','2015-10-02 20:00:00',2,'TT',1);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-10-03 18:30:00','2015-10-03 20:00:00',2,'TT',3);
INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId)
  VALUES('2015-10-04 18:30:00','2015-10-04 20:00:00',2,'TT',3);
  
LOAD DATA INFILE '/tmp/creditcards.csv' 
INTO TABLE CreditCards
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

/**
Contents of creditcards.csv:
CardNumber,Expiration,UserName
3499432187650987,2018-10-01,Bruce
3488432187650987,2018-10-01,Bruce
3799432187650987,2018-10-01,Bruce
6011432187650987,2018-10-01,Bruce
6011432187650988,2018-10-01,Bruce
6441432187650987,2018-10-01,Bruce
6451432187650987,2018-10-01,Bruce
5199432187650987,2018-10-01,Bruce
5499432187650987,2018-10-01,Bruce
5499432187650988,2018-10-01,Bruce
5499432187650989,2018-10-01,Bruce
4499432187650987,2018-10-01,Bruce
4499432187650989,2018-10-01,Bruce
*/

# 1. What are the top 10 average review ratings for restaurants? Include the restaurant ID, restaurant name, and average rating in the result set.

# Note the usage of fully qualified column names. This isn't necessary in MySQL
# if the column names are unique across the referenced tables.
SELECT Restaurants.RestaurantId, Restaurants.Name, AVG(Reviews.Rating) AS AvgRtg
FROM Restaurants
  INNER JOIN Reviews
  ON Restaurants.RestaurantId = Reviews.RestaurantId
GROUP BY Restaurants.RestaurantId, Restaurants.Name
ORDER BY AvgRtg DESC, Restaurants.RestaurantId ASC, Restaurants.Name ASC
LIMIT 10;


# 2. How many users have created more than one review?
# Both answers below are acceptable.

# Count the distinct UserNames.
SELECT COUNT(*)
FROM (
  # Get the UserNames and their review count (with more than one review).
  SELECT UserName, COUNT(*) AS CNT
  FROM Reviews
  GROUP BY UserName
  # Note that MySQL allows reference to the alias.
  # So this works too:
  # HAVING CNT > 1
  HAVING COUNT(*) > 1) AS ReviewCnt;

# Alternatively (not preferred), filtering at the outer query.
SELECT COUNT(UserName)
FROM (
  # Number of reviews per UserName. This will result
  # in unique UserNames.
  SELECT UserName, COUNT(*) AS CNT
  FROM Reviews
  GROUP BY UserName) AS ReviewCnt
WHERE CNT > 1;


# 3. What day of the week is most popular for making a reservation?
# Use the DAYOFWEEK() function on the ‘Start’ column.

# Preferred (MySQL): Single-level SELECT statement.
SELECT DAYOFWEEK(Start) AS Day_Of_Week, COUNT(*) AS Cnt
FROM Reservations
# MySQL allows grouping by the alias, Day_Of_Week.
GROUP BY DAYOFWEEK(Start)
ORDER BY Cnt DESC
LIMIT 1;

# Alternative (Standard SQL): Nested SELECT statement.
SELECT Day_Of_Week, COUNT(*) AS Cnt
FROM (
  # Day of week for each reservation.
  SELECT ReservationId, DAYOFWEEK(Start) AS Day_Of_Week
  FROM Reservations) AS ReservationsByDay
GROUP BY Day_Of_Week
ORDER BY Cnt DESC
LIMIT 1;


# 4. Which usernames have made multiple reservations at the same SitDownRestaurant?
# Note that one UserName can make multiple reservations
# at multiple restaurants. The outer "GROUP BY UserName" removes duplicate usernames.
# Alternatively, an outer SELECT DISTINCT UserName without the
# GROUP BY will yield the same results. I don't expect familiarity with this advanced keyword.
# It's essentially a GROUP BY anyway, so conceptually is equivalent to our answer.
# Similarly: 
# SELECT DISTINCT UserName FROM Reservations GROUP BY UserName, RestaurantId HAVING COUNT(*) > 1;
SELECT UserName
FROM (
  # UserNames and RestaurantIds where the user has made more
  # than one reservation at the restaurant.
  SELECT UserName, RestaurantId, COUNT(*) AS Cnt
  FROM Reservations
  GROUP BY UserName, RestaurantId
  HAVING Cnt > 1) AS MultipleReservations
GROUP BY UserName;


# 5. Identify the number of credit cards per provider.
# The credit card provider is determined by the leading digit(s)
# of the CardNumber. Use the provided mapping for card providers.

# A nested IF(expr1,expr2,expr3) block would work, too,
# but the CASE block is easier to read.

# Note: can you express this without a nested SELECT statement?
# Try SUM(IF()).
SELECT Provider, COUNT(*) AS Provider_Cnt
FROM (
	SELECT
	  CardNumber,
      # CASE statement for mapping CardNumber to provider.
	  CASE
		WHEN
		  LEFT(CAST(CardNumber AS CHAR),2) IN ('34','37') 
		THEN 'American Express'
		WHEN
		  LEFT(CAST(CardNumber AS CHAR),4) IN ('6011') OR 
		  LEFT(CAST(CardNumber AS CHAR),3) IN ('644','645','646','647','648','649') OR
          LEFT(CAST(CardNumber AS CHAR),2) IN ('65')
		THEN 'Discover'
		WHEN
		  LEFT(CAST(CardNumber AS CHAR),2) IN ('51','52','53','54','55') 
		THEN 'Master Card'
		WHEN
		  LEFT(CAST(CardNumber AS CHAR),1) IN ('4')
		THEN 'Visa'
		ELSE 'NA'
	  END AS Provider
	FROM CreditCards) AS T
GROUP BY Provider;


# 6. What is the total number of active restaurants for each type of
# restaurant (SitDownRestaurant, TakeOutRestaurant, FoodCartRestaurant)?
SELECT 'SitDown' AS RestaurantType, COUNT(SitDownRestaurant.RestaurantId) AS ActiveRestaurants
FROM SitDownRestaurant
  INNER JOIN Restaurants
  ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId
WHERE Restaurants.Active = true
UNION
SELECT 'TakeOut' AS RestaurantType, COUNT(TakeOutRestaurant.RestaurantId) AS ActiveRestaurants
FROM TakeOutRestaurant
  INNER JOIN Restaurants
  ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId
WHERE Restaurants.Active = true
UNION
SELECT 'FoodCart' AS RestaurantType, COUNT(FoodCartRestaurant.RestaurantId) AS ActiveRestaurants
FROM FoodCartRestaurant
  INNER JOIN Restaurants
  ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId
WHERE Restaurants.Active = true;


# 7. Which UserNames have not created a review,
# nor created a recommendation, nor created a reservation?

SELECT Users.UserName
FROM Users
  LEFT OUTER JOIN
    Reviews
  ON Users.UserName = Reviews.UserName
  LEFT OUTER JOIN
    Recommendations
  ON Users.UserName = Recommendations.UserName
  LEFT OUTER JOIN
    Reservations
  ON Users.UserName = Reservations.UserName
# Filter on whether the PK of the right-hand side table references are NULL.
# Filtering on the FKs would yield the same result. For example:
# WHERE Reviews.UserName IS NULL
#   AND Recommendations.UserName IS NULL
#   AND Reservations.UserName IS NULL
WHERE Reviews.ReviewId IS NULL
  AND Recommendations.RecommendationId IS NULL
  AND Reservations.ReservationId IS NULL;


# 8. What is the ratio of the total number of recommendations to the total number of reviews?

# CROSS JOIN is the simplest way to operate on a single value
# result from two incompatible table references.
SELECT (1.0* RecommendationCount / ReviewCount) AS Recommendation_Review_Ratio
FROM
    # Total number of recommendations.
    (SELECT COUNT(*) AS RecommendationCount
    FROM Recommendations) AS Rec
  CROSS JOIN
    # Total number of reviews.
    (SELECT COUNT(*) AS ReviewCount
    FROM Reviews) AS Rvw;

# Alternative (not preferred): INNER JOIN can be used as well, using an pseudo join key.
# Operationally, this is equivalent to the CROSS JOIN, but the
# SELECT statement is more complex.
SELECT (1.0* RecommendationCount / ReviewCount) AS Recommendation_Review_Ratio
FROM
    # Total number of recommendations.
    (SELECT COUNT(*) AS RecommendationCount, 1 AS JoinKey
    FROM Recommendations) AS Rec
  INNER JOIN
    # Total number of reviews.
    (SELECT COUNT(*) AS ReviewCount, 1 AS JoinKey
    FROM Reviews) AS Rvw
  ON Rec.JoinKey = Rvw.JoinKey;

# Alternative (not preferred): both inner SELECT statements return a single numeric
# value, so we can divide the results inline. This is not standard SQL, but MySQL
# recognizes the single numeric value returned by each inner SELECT.
SELECT
  (SELECT COUNT(*) FROM Recommendations) / 
  (SELECT COUNT(*) FROM Reviews);


# 9. Of the users that have created a reservation at a SitDownRestaurant,
# what is the percentage that the user has recommended that SitDownRestaurant?

# When there is no GROUP BY clause, the aggregation functions operate
# on the entire intermediate result set of the FROM-WHERE clauses.
SELECT 100.0 * SUM(IF(RecommendationUsers.UserName IS NOT NULL, 1, 0)) 
    / COUNT(*) AS Percent_Reservation_Recommendation
# To see the user reservations vs user recommendations, use:
# SELECT ReservationUsers.UserName, ReservationUsers.RestaurantId,
#  RecommendationUsers.UserName, RecommendationUsers.RestaurantId
FROM 
    # Distinct UserNames and RestaurantIds for reservations on the left-hand side.
    (SELECT UserName, RestaurantId
    FROM Reservations
    GROUP BY UserName, RestaurantId) AS ReservationUsers
  LEFT OUTER JOIN
    # Distinct UserNames AND RestaurantIds for recommended SitDownRestaurants on the right-hand side.
    (SELECT UserName, Recommendations.RestaurantId AS RestaurantId 
    FROM Recommendations
      INNER JOIN SitDownRestaurant
      ON Recommendations.RestaurantId = SitDownRestaurant.RestaurantId
    GROUP BY UserName, Recommendations.RestaurantId) AS RecommendationUsers
  # Join on both the UserName and RestaurantId since we want to know
  # the user recommended restaurants.
  ON ReservationUsers.UserName = RecommendationUsers.UserName
    AND ReservationUsers.RestaurantId = RecommendationUsers.RestaurantId;


# Note that Users can only make reservations at SitDownRestaurants.
# So "Recommendations INNER JOIN SitDownRestaurant" in not necessary, and
# just Recommendations is sufficient. This is more optimal and both solutions are acceptable.
 
SELECT 100.0 * SUM(IF(RecommendationUsers.UserName IS NOT NULL, 1, 0)) 
    / COUNT(*) AS Percent_Reservation_Recommendation
FROM 
    (SELECT UserName, RestaurantId
	FROM Reservations
    GROUP BY UserName, RestaurantId) AS ReservationUsers
  LEFT OUTER JOIN
    (SELECT UserName, Recommendations.RestaurantId AS RestaurantId 
    FROM Recommendations
    GROUP BY UserName, Recommendations.RestaurantId) AS RecommendationUsers
  ON ReservationUsers.UserName = RecommendationUsers.UserName
    AND ReservationUsers.RestaurantId = RecommendationUsers.RestaurantId;


# 10. Which UserNames have created more than twice the number of
# recommendations than number of reviews?
# 1. Identify all users.
SELECT * FROM Users;
# 2. Recommendations per user.
SELECT UserName, COUNT(*) AS Recommendation_Cnt
FROM Recommendations
GROUP BY UserName;
# 3. Reviews per user.
SELECT UserName, COUNT(*) AS Review_Cnt
FROM Reviews
GROUP BY UserName;
# 4. View of the LEFT OUTER JOIN, which contains all users,
# even if they have zero recommendations or zero reviews.
SELECT Users.UserName,
  UserRecommendations.UserName, UserRecommendations.Recommendation_Cnt,
  UserReviews.UserName, UserReviews.Review_Cnt
FROM Users
  LEFT OUTER JOIN
	# Recommendations per user.
    (SELECT UserName, COUNT(*) AS Recommendation_Cnt
    FROM Recommendations
    GROUP BY UserName) AS UserRecommendations
  ON Users.UserName = UserRecommendations.UserName
  LEFT OUTER JOIN
    # Reviews per user.
    (SELECT UserName, COUNT(*) AS Review_Cnt
    FROM Reviews
    GROUP BY UserName) AS UserReviews
  ON Users.UserName = UserReviews.UserName;
# 5. Handle NULLs.
SELECT Users.UserName,
  UserRecommendations.UserName,
  IF(UserRecommendations.UserName IS NULL, 0, UserRecommendations.Recommendation_Cnt) AS Recommendation_Subtotal,
  UserReviews.UserName,
  IF(UserReviews.UserName IS NULL, 0, UserReviews.Review_Cnt) AS Review_Subtotal
FROM Users
  LEFT OUTER JOIN
	# Recommendations per user.
    (SELECT UserName, COUNT(*) AS Recommendation_Cnt
    FROM Recommendations
    GROUP BY UserName) AS UserRecommendations
  ON Users.UserName = UserRecommendations.UserName
  LEFT OUTER JOIN
    # Reviews per user.
    (SELECT UserName, COUNT(*) AS Review_Cnt
    FROM Reviews
    GROUP BY UserName) AS UserReviews
  ON Users.UserName = UserReviews.UserName;
# 6. Row filtering (selection) for outer-most query.
# A. The SELECT clause has a metric that is calculated after WHERE filtering.
#    So try another outer query.
SELECT T.UserName, T.Recommendation_Subtotal, T.Review_Subtotal
FROM
	(SELECT Users.UserName,
	  IF(UserRecommendations.UserName IS NULL, 0, UserRecommendations.Recommendation_Cnt) AS Recommendation_Subtotal,
	  IF(UserReviews.UserName IS NULL, 0, UserReviews.Review_Cnt) AS Review_Subtotal
	FROM Users
	  LEFT OUTER JOIN
		# Recommendations per user.
		(SELECT UserName, COUNT(*) AS Recommendation_Cnt
		FROM Recommendations
		GROUP BY UserName) AS UserRecommendations
	  ON Users.UserName = UserRecommendations.UserName
	  LEFT OUTER JOIN
		# Reviews per user.
		(SELECT UserName, COUNT(*) AS Review_Cnt
		FROM Reviews
		GROUP BY UserName) AS UserReviews
	  ON Users.UserName = UserReviews.UserName) AS T
# Note in this WHERE clause, we only need users with recommendations.
# So the "Users LEFT OUTER JOIN" is not needed since we exclude users with zero recommendations.
# However, we include it in the solution example for the "additional data analysis" follow-up below.
WHERE T.Recommendation_Subtotal > 2 * T.Review_Subtotal;
# B. The SELECT aliases cannot be referenced in the WHERE clause.
#    Recall WHERE filtering happens first.
#    So duplicate the SELECT expr down into WHERE filtering.
SELECT Users.UserName,
  IF(UserRecommendations.UserName IS NULL, 0, UserRecommendations.Recommendation_Cnt) AS Recommendation_Subtotal,
  IF(UserReviews.UserName IS NULL, 0, UserReviews.Review_Cnt) AS Review_Subtotal
FROM Users
  LEFT OUTER JOIN
	# Recommendations per user.
    (SELECT UserName, COUNT(*) AS Recommendation_Cnt
    FROM Recommendations
    GROUP BY UserName) AS UserRecommendations
  ON Users.UserName = UserRecommendations.UserName
  LEFT OUTER JOIN
    # Reviews per user.
    (SELECT UserName, COUNT(*) AS Review_Cnt
    FROM Reviews
    GROUP BY UserName) AS UserReviews
  ON Users.UserName = UserReviews.UserName
WHERE IF(UserRecommendations.UserName IS NULL, 0, UserRecommendations.Recommendation_Cnt)
  > 2 * IF(UserReviews.UserName IS NULL, 0, UserReviews.Review_Cnt);


# Question: what if we flipped the comparison? How would this query change if we asked:
# created more than twice the number of reviews than number of recommendations?

