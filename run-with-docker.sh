#!/bin/bash

./gradlew clean build dockerTag &&
  docker container run --rm -p 8080:8080 faireai/tinyweatherbulletin
