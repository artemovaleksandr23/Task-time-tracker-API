package org.example.exception;

import org.example.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException ex) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(response);
	}

	@ExceptionHandler(InvalidTimeRangeException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(InvalidTimeRangeException ex) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOther(Exception ex) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getClass().getSimpleName());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(response);
	}
}
