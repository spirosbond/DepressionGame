#ex. 	sudo docker build . --build-arg file=/path/to/file -t coolengineer-me:latest
FROM tomcat:jdk8

# ARG tag
ARG file

#RUN rm -rf /usr/local/tomcat/webapps/*
#ADD ${file} /usr/local/tomcat/webapps/ROOT.war
ADD ${file} /root/ROOT.war
ADD run-war.sh /usr/bin/
ADD core /root/core

#ENTRYPOINT ["catalina.sh", "run"]
ENTRYPOINT ["run-war.sh"]
