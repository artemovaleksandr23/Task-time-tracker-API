package org.example.exception;

public class InvalidTimeRangeException extends RuntimeException {
	public InvalidTimeRangeException() {
		super("startTime must be before endTime");
	}
}
