#
# Build stage
#
FROM maven:3.6-jdk-8 as maven_build
WORKDIR /app

COPY controller/src ./controller/src
COPY entity/src ./entity/src
COPY repository/src ./repository/src
COPY security/src ./security/src
COPY service/src ./service/src

COPY controller/pom.xml ./controller
COPY entity/pom.xml ./entity
COPY repository/pom.xml ./repository
COPY security/pom.xml ./security
COPY service/pom.xml ./service
COPY pom.xml .

RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip


RUN mvn -P clean package -Dmaven.test.skip


FROM openjdk:8-jre

WORKDIR /app

COPY --from=maven_build /app/controller/target/controller-1.0.0.jar .
COPY --from=maven_build /app/entity/target/entity-1.0.0.jar .
COPY --from=maven_build /app/repository/target/repository-1.0.0.jar .
COPY --from=maven_build /app/security/target/security-1.0.0.jar .
COPY --from=maven_build /app/service/target/service-1.0.0.jar .

CMD [ "java", "-jar", "/app/controller/target/controller-1.0.0.jar" ]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8080/ || exit 1

EXPOSE 8080:8080