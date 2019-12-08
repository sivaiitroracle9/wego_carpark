FROM mysql:5.7.21

ADD transformed-hdb-carpark-information.csv /docker-entrypoint-initdb.d/

ADD mysql-wegodb.sql /docker-entrypoint-initdb.d/
