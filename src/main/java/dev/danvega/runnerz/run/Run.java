package dev.danvega.runnerz.run;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run (
		@Id
		Integer id,
		@NotEmpty
		String title, 
		LocalDateTime startedOn, 
		LocalDateTime completedOn,
		@Positive
		Integer miles, 
		Location location,
		@Version
		Integer version
) {
	public Run {
		if (!completedOn.isAfter(startedOn)) {
			throw new IllegalArgumentException("Completed On Must be After Started On");
		}
	}
}
