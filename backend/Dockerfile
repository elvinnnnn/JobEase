FROM openjdk:21
 
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src

RUN ./mvnw package
 
CMD ["./mvnw", "spring-boot:run"]