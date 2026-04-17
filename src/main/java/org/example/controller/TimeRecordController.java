package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.CreateTimeRecordRequest;
import org.example.dto.TimeRecordResponse;
import org.example.service.TimeRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Time Records", description = "Time tracking API")
@RestController
@RequestMapping("/time-records")
public class TimeRecordController {

	private final TimeRecordService service;

	public TimeRecordController(TimeRecordService service) {
		this.service = service;
	}

	@Operation(summary = "Create time record for employee and task")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TimeRecordResponse create(@Valid @RequestBody CreateTimeRecordRequest request) {
		return service.create(request);
	}
	@Operation(summary = "Get time records by employee and date range")
	@GetMapping
	public List<TimeRecordResponse> getByPeriod(
			@RequestParam Long employeeId,

			@RequestParam
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime start,
			@RequestParam

			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime end
	) {
		return service.getByPeriod(employeeId, start, end);
	}
}
