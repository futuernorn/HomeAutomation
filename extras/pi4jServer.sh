#!/bin/sh
#
# pi4jServer.sh - launches the pi4jServer
#
#
#

CLASSPATH=./:/home/pi/workspace/HomeAutomation/extras/pi4jServer.jar:./lib/*

java -Dspring.context=applicationContext.xml \
-classpath "$CLASSPATH" \
com.pi4j.testserver.App $1





