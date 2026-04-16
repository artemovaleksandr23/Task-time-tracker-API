package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Task;
import org.example.entity.TaskStatus;

@Mapper
public interface TaskMapper {
	void insert(Task task);
	Task findById(Long id);
	void updateStatus(@Param("id") Long id, @Param("status") TaskStatus status);
}
