FROM openjdk:11-jre-slim

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN echo 'Asia/Shanghai' >/etc/timezone

ADD target/violin-calendar-*.jar /violin-calendar.jar

ENTRYPOINT ["java","-jar","violin-calendar.jar"]
