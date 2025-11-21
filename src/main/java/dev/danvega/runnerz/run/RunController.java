package dev.danvega.runnerz.run;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/runs")
public class RunController {
	
	private final RunRepository runRepository;
	
	public RunController(RunRepository runRepository) {
		this.runRepository = runRepository;
	}
	
	@GetMapping("/api/runs")
	List<Run> findAll(){
		return runRepository.findAll();
	}
	
	@GetMapping("/1")
	Run findById() {
		return runRepository.findById(1);
	}
}
