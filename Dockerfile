FROM tomcat:8.0

MAINTAINER mitch_goshorn

COPY target/servlet-cookies.war /usr/local/tomcat/webapps/