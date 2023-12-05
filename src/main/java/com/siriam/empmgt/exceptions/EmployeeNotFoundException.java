package com.siriam.empmgt.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	
	private String message;
	public EmployeeNotFoundException() {
		super();
	}
	public EmployeeNotFoundException(String message) {
		super(message);
		this.message=message;
	}

}
