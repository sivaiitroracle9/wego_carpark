FROM mysql:5.7.21

ADD data_folder/transformed-hdb-carpark-information.csv /docker-entrypoint-initdb.d/transformed-hdb-carpark-information.csv
ADD data_folder/mysql-wegodb.sql /docker-entrypoint-initdb.d/mysql-wegodb.sql
