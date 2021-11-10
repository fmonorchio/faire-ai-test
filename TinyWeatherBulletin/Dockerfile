FROM openjdk:11.0-jdk-oracle

COPY TinyWeatherBulletin.jar application.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "-noverify", "/application.jar" ]
