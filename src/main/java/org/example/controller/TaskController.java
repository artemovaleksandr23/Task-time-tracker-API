package org.example.controller;

import org.example.dto.CreateTaskRequest;
import org.example.dto.TaskResponse;
import org.example.entity.TaskStatus;
import org.example.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TaskResponse create(@RequestBody CreateTaskRequest request) {
		return taskService.create(request);
	}

	@GetMapping("/{id}")
	public TaskResponse getById(@PathVariable Long id) {
		return taskService.getById(id);
	}

	@PutMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
		taskService.updateStatus(id, status);
	}
}
