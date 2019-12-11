#!/bin/bash
sudo mkdir -p /home/docker/mysql_data
sudo rm -rf /home/docker/mysql_data/*
docker-compose up --force-recreate
