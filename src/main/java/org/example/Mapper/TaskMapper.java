package org.example.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.Entity.Task;
import org.example.Entity.TaskStatus;

@Mapper
public interface TaskMapper {
	void insert(Task task);
	Task findById(Long id);
	void updateStatus(@Param("id") Long id, @Param("status") TaskStatus status);
}
