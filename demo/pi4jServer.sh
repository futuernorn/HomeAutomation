#!/bin/sh
#
# pi4jServer.sh - launches the pi4jServer
#
#
#

CLASSPATH=./:./pi4jServer.jar:./lib/*

java -Dspring.context=applicationContext.xml \
-classpath "$CLASSPATH" \
com.pi4j.testserver.App $1





