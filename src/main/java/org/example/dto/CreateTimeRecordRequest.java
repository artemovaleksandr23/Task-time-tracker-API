package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTimeRecordRequest {
	@NotNull
	private Long employeeId;

	@NotNull
	private Long taskId;

	@NotNull
	private LocalDateTime startTime;

	@NotNull
	private LocalDateTime endTime;
	private String description;
}
