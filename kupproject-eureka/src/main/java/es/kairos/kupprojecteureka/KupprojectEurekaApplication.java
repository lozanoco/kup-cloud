package es.kairos.kupprojecteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KupprojectEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KupprojectEurekaApplication.class, args);
	}

}
