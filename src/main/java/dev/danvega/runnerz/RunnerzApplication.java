package dev.danvega.runnerz;

import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import dev.danvega.runnerz.run.Location;
import dev.danvega.runnerz.run.Run;
import dev.danvega.runnerz.user.User;
import dev.danvega.runnerz.user.UserHttpClient;
import dev.danvega.runnerz.user.UserRestClient;
import dev.danvega.runnerz.run.JdbcClientRunRepository;

@SpringBootApplication
public class RunnerzApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		log.info("Application started successfully!");
	}
	
		@Bean
		UserHttpClient userHttpClient() {
			RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
			HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
			return factory.createClient(UserHttpClient.class);
		}
	
		@Bean
		CommandLineRunner runner(UserHttpClient client) {
			return args -> {
				List<User> users = client.findAll();
				System.out.println(users);
			};
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
