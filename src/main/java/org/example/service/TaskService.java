package org.example.service;

import org.example.dto.CreateTaskRequest;
import org.example.dto.TaskResponse;
import org.example.entity.Task;
import org.example.entity.TaskStatus;
import org.example.exception.TaskNotFoundException;
import org.example.mapper.TaskMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	private final TaskMapper mapper;

	public TaskService(TaskMapper mapper) {
		this.mapper = mapper;
	}

	private TaskResponse toResponse(Task task) {
		TaskResponse response = new TaskResponse();
		response.setId(task.getId());
		response.setTitle(task.getTitle());
		response.setDescription(task.getDescription());
		response.setStatus(task.getStatus().name());
		return response;
	}

	public TaskResponse create(CreateTaskRequest request) {
		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setStatus(TaskStatus.NEW);
		mapper.insert(task);
		return toResponse(task);
	}

	public TaskResponse getById(Long id) {
		Task task = mapper.findById(id);
		if (task == null) {
			throw new TaskNotFoundException(id);
		}
		return toResponse(task);
	}

	public void updateStatus(Long id, TaskStatus status) {
		Task task = mapper.findById(id);
		if (task == null) {
			throw new TaskNotFoundException(id);
		}
		mapper.updateStatus(id, status);
	}
}
