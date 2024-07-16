# Use Amazon Corretto 17 as the base image
FROM amazoncorretto:17

# Install dependencies
RUN yum update -y && \
    yum install -y unzip curl && \
    yum clean all

# Verify the JRE is available
RUN java -version

# Install AWS CLI
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" && \
    unzip awscliv2.zip && \
    ./aws/install && \
    rm -rf awscliv2.zip aws

# Copy the Spring Boot application's jar to the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","/app.jar"]
