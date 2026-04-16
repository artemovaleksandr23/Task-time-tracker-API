package org.example.service;

import org.example.dto.CreateTimeRecordRequest;
import org.example.dto.TimeRecordResponse;
import org.example.entity.Task;
import org.example.entity.TimeRecord;
import org.example.exception.InvalidTimeRangeException;
import org.example.exception.TaskNotFoundException;
import org.example.mapper.TaskMapper;
import org.example.mapper.TimeRecordMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeRecordService {
	private final TimeRecordMapper mapper;
	private final TaskMapper taskMapper;

	public TimeRecordService(TimeRecordMapper mapper, TaskMapper taskMapper) {
		this.mapper = mapper;
		this.taskMapper = taskMapper;
	}

	private TimeRecordResponse toResponse(TimeRecord record) {
		TimeRecordResponse response = new TimeRecordResponse();
		response.setId(record.getId());
		response.setEmployeeId(record.getEmployeeId());
		response.setTaskId(record.getTaskId());
		response.setDescription(record.getDescription());
		response.setStartTime(record.getStartTime());
		response.setEndTime(record.getEndTime());
		return response;
	}

	public TimeRecordResponse create(CreateTimeRecordRequest request) {
		Task task = taskMapper.findById(request.getTaskId());
		if(task == null){
			throw new TaskNotFoundException(request.getTaskId());
		}

		if(request.getStartTime().isAfter(request.getEndTime())){
			throw new InvalidTimeRangeException();
		}
		TimeRecord record = new TimeRecord();
		record.setEmployeeId(request.getEmployeeId());
		record.setTaskId(request.getTaskId());
		record.setStartTime(request.getStartTime());
		record.setEndTime(request.getEndTime());
		record.setDescription(request.getDescription());
		mapper.insert(record);
		return toResponse(record);
	}

	public List<TimeRecordResponse> getByPeriod(Long employeeId, LocalDateTime start, LocalDateTime end) {
		if (start.isAfter(end)) {
			throw new InvalidTimeRangeException();
		}
		List<TimeRecord> records = mapper.findByEmployeeAndPeriod(employeeId, start, end);
		List<TimeRecordResponse> result = new ArrayList<>();
		for (TimeRecord record : records) {
			result.add(toResponse(record));
		}
		return result;
	}
}
