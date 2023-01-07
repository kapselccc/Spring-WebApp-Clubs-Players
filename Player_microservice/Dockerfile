FROM maven:3-openjdk-18

WORKDIR /app

COPY  pom.xml /app/

RUN mvn dependency:resolve

COPY ./src /app/src

EXPOSE 8082

CMD ["mvn", "spring-boot:run"]