FROM java:8
MAINTAINER urs.zysset@zuehlke.com
EXPOSE 8080
ADD never-forget.jar /data/never-forget.jar
CMD java -jar /data/never-forget.jar