FROM openjdk:21
 
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
 
COPY src ./src
COPY jobeaseDB.db jobeaseDB.db

RUN ./mvnw package
 
CMD ["./mvnw", "spring-boot:run"]