# EPQAPI
> This is the Spring Boot Persistence API for my EPQ backend.
> This project is intended for use and presentation in (stvnliu)[https://github.com/stvnliu]'s A Level Edexcel Level 3 Extended Project Qualification as an Artifact.  
> It should not be considered a commercial-grade application.
## Deploying this IRC application
1. Ensure that `mariadb` is deployed and running. In Linux environments this can be done with `systemd` through the following command: 
```systemctl start mariadb```
or if you wish to have `mariadb` start each time on startup, use:
```systemctl enable mariadb```
2. Run the packaged application in JAR archive format with the following command:
```java -jar <PATH_TO_JAR> <JVM_PARAMETERS>```
3. Test the web frontend by logging onto `localhost:8080` and test the functionality of the application.