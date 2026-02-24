package dev.danvega.runnerz.run;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryRunRepositoryTest {
	InMemoryRunRepository repository;
	
	@BeforeEach
	void setUp() {
		repository = new InMemoryRunRepository();
		repository.create(new Run(1, 
				"Monday Morning Run", 
				LocalDateTime.now(), 
				LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 
				3, 
				Location.INDOOR, null));
		
		
		repository.create(new Run(1, 
				"Wednesday Evening Run", 
				LocalDateTime.now(), 
				LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 
				6, 
				Location.INDOOR, null));
	}
	
	@Test
	void shouldFindAllRuns() {
		List<Run> runs = repository.findAll();
		assertEquals(2, runs.size(), "Should Have Returned 2 runs");
	}
}
