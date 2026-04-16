package org.example.service;

import org.example.dto.CreateTaskRequest;
import org.example.dto.TaskResponse;
import org.example.entity.Task;
import org.example.entity.TaskStatus;
import org.example.exception.TaskNotFoundException;
import org.example.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	private TaskMapper taskMapper;

	@InjectMocks
	private TaskService taskService;

	@Test
	void shouldCreateTask() {

		CreateTaskRequest request = new CreateTaskRequest();
		request.setTitle("Test");
		request.setDescription("Desc");

		doAnswer(invocation -> {
			Task t = invocation.getArgument(0);
			t.setId(1L);
			return null;
		}).when(taskMapper).insert(any(Task.class));

		TaskResponse response = taskService.create(request);

		assertEquals("Test", response.getTitle());
		assertEquals("NEW", response.getStatus());
	}

	@Test
	void shouldReturnTaskById() {

		Task task = new Task();
		task.setId(1L);
		task.setTitle("Test");
		task.setStatus(TaskStatus.NEW);

		when(taskMapper.findById(1L)).thenReturn(task);

		TaskResponse response = taskService.getById(1L);

		assertEquals(1L, response.getId());
	}

	@Test
	void shouldThrowExceptionWhenTaskNotFound() {

		when(taskMapper.findById(1L)).thenReturn(null);

		assertThrows(TaskNotFoundException.class,
				() -> taskService.getById(1L));
	}
}
