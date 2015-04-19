#!/bin/bash

API_KEY=$1
ARCHIVE=$2
VERSION=$3

curl -X PUT -T $ARCHIVE -ugayanper:$API_KEY https://api.bintray.com/content/gayanper/p2/eclipse-themes/$VERSION/eclipse-themes/$VERSION/archive-site.zip?explode=1
