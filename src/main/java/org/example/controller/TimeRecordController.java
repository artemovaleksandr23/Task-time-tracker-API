package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CreateTimeRecordRequest;
import org.example.dto.TimeRecordResponse;
import org.example.service.TimeRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/time-records")
public class TimeRecordController {

	private final TimeRecordService service;

	public TimeRecordController(TimeRecordService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TimeRecordResponse create(@Valid @RequestBody CreateTimeRecordRequest request) {
		return service.create(request);
	}

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
