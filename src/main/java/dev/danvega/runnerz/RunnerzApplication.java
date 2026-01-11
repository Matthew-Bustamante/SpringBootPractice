package dev.danvega.runnerz;

import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.danvega.runnerz.run.Location;
import dev.danvega.runnerz.run.Run;
import dev.danvega.runnerz.run.JdbcClientRunRepository;

@SpringBootApplication
public class RunnerzApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		log.info("Application started successfully!");
	}
	
	//@Bean
	//CommandLineRunner runner(RunRepository runRepository) {
		//return args -> {
			//Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
			//log.info("Run: " + run);
			//runRepository.create(run);
		//};
	//}

}
