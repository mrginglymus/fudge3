FROM tomcat:8.0.33-jre8
MAINTAINER "Bill Collins <bill@bill.works">

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY /target/fudge3-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]