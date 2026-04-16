package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeRecordResponse {
	private Long id;
	private Long employeeId;
	private Long taskId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String description;
}
