package org.example.service;

import org.example.dto.CreateTimeRecordRequest;
import org.example.dto.TimeRecordResponse;
import org.example.entity.Task;
import org.example.entity.TimeRecord;
import org.example.exception.InvalidTimeRangeException;
import org.example.exception.TaskNotFoundException;
import org.example.mapper.TaskMapper;
import org.example.mapper.TimeRecordMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceTest {

	@Mock
	private TimeRecordMapper timeRecordMapper;

	@Mock
	private TaskMapper taskMapper;

	@InjectMocks
	private TimeRecordService service;

	@Test
	void shouldCreateTimeRecord() {

		CreateTimeRecordRequest request = new CreateTimeRecordRequest();
		request.setTaskId(1L);
		request.setEmployeeId(10L);
		request.setStartTime(LocalDateTime.now());
		request.setEndTime(LocalDateTime.now().plusHours(1));

		Task task = new Task();
		task.setId(1L);

		when(taskMapper.findById(1L)).thenReturn(task);

		doAnswer(invocation -> {
			TimeRecord r = invocation.getArgument(0);
			r.setId(1L);
			return null;
		}).when(timeRecordMapper).insert(any(TimeRecord.class));

		TimeRecordResponse response = service.create(request);

		assertEquals(1L, response.getTaskId());
	}

	@Test
	void shouldThrowWhenInvalidTimeRange() {

		CreateTimeRecordRequest request = new CreateTimeRecordRequest();

		request.setTaskId(1L);
		request.setEmployeeId(1L);

		request.setStartTime(LocalDateTime.now());
		request.setEndTime(LocalDateTime.now().minusHours(1));

		Task task = new Task();
		task.setId(1L);

		when(taskMapper.findById(1L)).thenReturn(task);

		assertThrows(InvalidTimeRangeException.class,
				() -> service.create(request));
	}

	@Test
	void shouldThrowWhenTaskNotFound() {

		CreateTimeRecordRequest request = new CreateTimeRecordRequest();
		request.setTaskId(999L);

		when(taskMapper.findById(999L)).thenReturn(null);

		assertThrows(TaskNotFoundException.class,
				() -> service.create(request));
	}
}