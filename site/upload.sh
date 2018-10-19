#!/bin/bash

API_KEY=$1
ARCHIVE=$2
VERSION=$3

curl -X PUT -T $ARCHIVE -u gayanper:$API_KEY \
        -H "X-Bintray-Package:eclipse-themes" \
        -H "X-Bintray-Version:$VERSION" \
        -H "X-Bintray-Explode:1" \
        https://api.bintray.com/content/gayanper/p2/eclipse-themes/$VERSION

curl -X POST -ugayanper:$API_KEY https://api.bintray.com/content/gayanper/p2/eclipse-themes/$VERSION/publish

