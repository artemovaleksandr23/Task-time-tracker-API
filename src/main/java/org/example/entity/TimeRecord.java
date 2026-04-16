package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeRecord {
	private Long id;
	private Long employeeId;
	private Long taskId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String description;
}
