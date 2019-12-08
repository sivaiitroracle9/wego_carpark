CREATE SCHEMA IF NOT EXISTS wego_carpark_system default character set utf8;
USE wego_carpark_system;

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

  -- LOAD DATA LOCAL INFILE 'transformed-hdb-carpark-information.csv' INTO TABLE CARPARK
  -- FIELDS TERMINATED BY ','
  -- ENCLOSED BY '"'
  -- LINES TERMINATED BY '\r\n'
  -- IGNORE 1 LINES
  -- (carpark_number, address, x_coordinate, y_coordinate, latitude, longitude,
  --   carpark_type, type_of_parking_system, short_term_parking, free_parking,
  --   night_parking, carpark_decks, gantry_height, carpark_basement);
