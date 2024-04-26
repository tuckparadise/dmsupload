FROM eclipse-temurin:17-jre-alpine 
EXPOSE 8080
ENV TZ=Asia/Singapore
COPY target/DMSFileUploadService-1.0.0.jar /apps/sb/app.jar
COPY /src/main/resources/application.properties /apps/sb/application.properties
RUN chown -R guest:users /apps
CMD ["java", "-jar", "/apps/sb/app.jar", "--spring.config.location=file:/apps/sb/application.properties"]
