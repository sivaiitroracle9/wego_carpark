CREATE SCHEMA IF NOT EXISTS wego_carpark_system default character set utf8;
USE wego_carpark_system;

DROP TABLE IF EXISTS CARPARK;
CREATE table CARPARK (
  carpark_number varchar(255) not null,
  address varchar(255),
  carpark_type varchar(255),
  carpark_basement varchar(255),
  carpark_decks varchar(255),
  free_parking varchar(255),
  gantry_height double precision,
  latitude double precision not null,
  longitude double precision not null,
  night_parking varchar(255),
  short_term_parking varchar(255),
  type_of_parking_system varchar(255),
  x_coordinate double precision,
  y_coordinate double precision,
  primary key (carpark_number));

LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/transformed-hdb-carpark-information.csv' INTO TABLE CARPARK
FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(carpark_number, address, x_coordinate, y_coordinate, latitude, longitude, carpark_type, type_of_parking_system,
  short_term_parking, free_parking, night_parking, carpark_decks, gantry_height, carpark_basement);

DROP TABLE IF EXISTS CARPARK_AVAILABILITY;
CREATE table CARPARK_AVAILABILITY (
  carpark_number varchar(255) not null,
  lot_type varchar(255),
  lot_available int,
  timestamp datetime,
  total_lots int,
  update_time datetime,
  primary key (carpark_number));

DROP PROCEDURE IF EXISTS GetNearestCarparks;

DELIMITER //
CREATE PROCEDURE GetNearestCarparks (IN input_latitude double, IN input_longitude double, IN input_pagenumber INT, IN input_pagesize INT)
BEGIN
SET @inputLat = input_latitude;
SET @inputLong = input_longitude;
SET @offset = (input_pagenumber - 1) * input_pagesize;
SET @pagesize = input_pagesize ;

PREPARE cp_query FROM 'SELECT carpark.carpark_number as carpark_number, carpark.address as address, carpark.latitude as latitude, carpark.longitude as longitude,
availability.total_lots as total_lots, availability.lot_available as lot_available,
(ACOS( COS(RADIANS(?)) * COS(RADIANS(carpark.latitude)) * COS(RADIANS(carpark.longitude) - RADIANS(?) ) + SIN(RADIANS(?))* SIN(RADIANS(carpark.latitude))) * 6371) AS distance_in_km
FROM CARPARK carpark INNER JOIN CARPARK_AVAILABILITY availability
ON carpark.carpark_number=availability.carpark_number WHERE availability.lot_available > 0
ORDER BY distance_in_km ASC LIMIT ?, ?';

EXECUTE cp_query USING @inputLat, @inputLong, @inputLat, @offset, @pagesize;

END //
DELIMITER ;

GRANT execute on PROCEDURE GetNearestCarparks TO 'wego'@'%';
