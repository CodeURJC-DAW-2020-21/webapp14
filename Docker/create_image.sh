#!/bin/bash

if [ -d "../Backend/src/main/resources/static/new" ]
then
	rm -f ../Backend/src/main/resources/static/new/*
else
	mkdir ../Backend/src/main/resources/static/new
fi

cd ../Frontend

sudo npm install
ng build --prod --base-href="/new/"

cp -r dist/Frontend-Angular/* ../Backend/src/main/resources/static/new

cd ../Backend || exit
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package

cp target/ayuntamiento-0.0.1-SNAPSHOT.jar ../Docker

cd ../Docker || exit

sudo docker build -t adrim173/ayuntamiento .

rm -f ayuntamiento-0.0.1-SNAPSHOT.jar

sudo docker-compose up