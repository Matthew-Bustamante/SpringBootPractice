package dev.danvega.runnerz.run;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(RunRepository.class);
	private final JdbcClient jdbcClient;
	
	public RunRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}
	
	public List<Run> findAll(){
		return jdbcClient.sql("select * from run")
				.query(Run.class)
				.list();
	}
	
}
