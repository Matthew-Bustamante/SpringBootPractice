package dev.danvega.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run (
		Integer id,
		@NotEmpty
		String title, 
		LocalDateTime startedOn, 
		LocalDateTime completedOn,
		@Positive
		Integer Miles, 
		Location loaction
) {
	public Run {
		if (!completedOn.isAfter(startedOn)) {
			throw new IllegalArgumentException("Completed On Must be After Started On");
		}
	}
}
