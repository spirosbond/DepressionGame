#!/bin/sh
echo "Starting war"
# java -server -Xmx768M -Dgrails.env=prod -Dmyserver.url='localhost:8080' -jar ./CoolEngineer-me-0.2-emb.war
java -server -Xmx768M -Dgrails.env=prod -Dserver.port=8080 -jar /root/ROOT.war
 
