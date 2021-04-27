#!/bin/bash

BASE=`dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"`


cd "$BASE/Backend"



apt-get update
apt-get install maven docker.io docker-compose -y


mvn clean install

BUILD_FILENAME="$BASE/Backend/target/ayuntamiento-0.0.1-SNAPSHOT.jar"
DEST_FILENAME="$BASE/Docker/ayuntamiento.jar"


rm -rf $DEST_FILENAME
cp $BUILD_FILENAME $DEST_FILENAME


cd "$BASE/Docker"


docker build -t adrim173/ayuntamiento .


docker-compose up -d
