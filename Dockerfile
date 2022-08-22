FROM gradle
WORKDIR /owt
COPY . /owt
RUN gradle clean build -x test
EXPOSE 8080
RUN mkdir /app
RUN cp build/libs/*.jar /app/spring-boot-application.jar
CMD java -jar /app/spring-boot-application.jar