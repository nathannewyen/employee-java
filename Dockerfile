FROM openjdk:21

LABEL repository="JPMCEmployees Spring Boot Application"

CMD ["java", "-jar", "employees.jar"]
