package dev.danvega.runnerz.run;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest
public class RunControllerTest {
	
	@LocalServerPort
	int randomServerPort;
	
	RestClient restClient;
	
	@Autowired
	MockMvc mvc;
	
	@Autowired 
	ObjectMapper objectMapper;
	
	@MockitoBean
	RunRepository repository;
	
	private final List<Run> runs = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		runs.add(new Run(1, 
				"Monday Morning Run", 
				LocalDateTime.now(), 
				LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 
				3, 
				Location.INDOOR, null));
	}
	
	@Test
	void shouldFindAllRuns() {
		when(repository.findAll()).thenReturn(runs);
		mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(runs.size())));
	}
	
	@Test
    void shouldFindRunById() {
        Run run = restClient.get()
                .uri("/api/runs/1")
                .retrieve()
                .body(Run.class);

        assertAll(
                () -> assertEquals(1, run.id()),
                () -> assertEquals("Noon Run", run.title()),
                () -> assertEquals("2024-02-20T06:05", run.startedOn().toString()),
                () -> assertEquals("2024-02-20T10:27", run.completedOn().toString()),
                () -> assertEquals(24, run.miles()),
                () -> assertEquals(Location.INDOOR, run.location()));
    }
	
	 @Test
	    void shouldCreateNewRun() {
	        Run run = new Run(11, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10, Location.OUTDOOR, null);

	        ResponseEntity<Void> newRun = restClient.post()
	                .uri("/api/runs")
	                .body(run)
	                .retrieve()
	                .toBodilessEntity();

	        assertEquals(201, newRun.getStatusCode());
	    }
	  @Test
	    void shouldUpdateExistingRun() {
	        Run run = restClient.get().uri("/api/runs/1").retrieve().body(Run.class);

	        ResponseEntity<Void> updatedRun = restClient.put()
	                .uri("/api/runs/1")
	                .body(run)
	                .retrieve()
	                .toBodilessEntity();

	        assertEquals(204, updatedRun.getStatusCode());
	    }

	    @Test
	    void shouldDeleteRun() {
	        ResponseEntity<Void> run = restClient.delete()
	                .uri("/api/runs/1")
	                .retrieve()
	                .toBodilessEntity();

	        assertEquals(204, run.getStatusCode());
	    }
}
