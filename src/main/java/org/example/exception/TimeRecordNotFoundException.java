package org.example.exception;

public class TimeRecordNotFoundException extends RuntimeException {
	public TimeRecordNotFoundException(Long id) {
		super("TimeRecord not found with id: " + id);
	}
}
