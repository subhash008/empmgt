package com.siriam.empmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siriam.empmgt.exceptions.EmployeeAlreadyExistsException;
import com.siriam.empmgt.exceptions.EmployeeNotFoundException;
import com.siriam.empmgt.model.Employee;
import com.siriam.empmgt.services.EmployeeService;

@RestController
@RequestMapping("/api/v01/Employees")
public class EmployeeRestController {
	
	@Autowired
	EmployeeService empSrv;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmp(@PathVariable (value="id")long id) {
		Employee emp;
		try {
			emp=empSrv.getEmployeeById(id);
		}
		catch(EmployeeNotFoundException ex) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
	@GetMapping
	public List<Employee> getAllEmp(){
		
		return empSrv.getAllEmployees();
		
	}
	
	@PostMapping
	public ResponseEntity<?> addEmp(@RequestBody Employee emp ) {
		try{
			empSrv.saveEmployee(emp);
		}
		catch(EmployeeAlreadyExistsException ex) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
	
	@PutMapping
	public Employee updateEmp(@RequestBody Employee emp) {
		
		empSrv.saveEmployee(emp);
		return emp;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  deleteEmp(@PathVariable (value="id")long id) {
		Employee emp;
		try {
			emp= empSrv.getEmployeeById(id);
			empSrv.deleteEmployeeById(id);
			
		}
		catch (EmployeeNotFoundException ex) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(emp);
	}

}
