package dev.danvega.runnerz.run;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

	private List<Run> runs = new ArrayList<>();
	
	List<Run> findAll() {
		return runs;
	}
	
	Run findById(Integer id) {
		return runs.stream()id.filter(run -> run.id() = id).findFirst().get();
	}
	
	@PostConstruct
	private void init() {
		runs.add(new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
		runs.add(new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.INDOOR));

	}
}
