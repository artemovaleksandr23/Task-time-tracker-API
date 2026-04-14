package org.example.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.Entity.TimeRecord;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimeRecordMapper {
	void insert(TimeRecord timeRecord);
	List<TimeRecord> findByEmployeeAndPeriod(@Param("employeeId") Long employeeId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
