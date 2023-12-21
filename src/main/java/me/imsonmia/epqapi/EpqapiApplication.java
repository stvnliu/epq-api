package me.imsonmia.epqapi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpqapiApplication extends Thread {
	private static Logger logger = LoggerFactory.getLogger(EpqapiApplication.class);
	public static void main(String[] args) {
		logger.info("Main Spring Boot process running in thread!");
		SpringApplication.run(EpqapiApplication.class, args);
	}
	

}
