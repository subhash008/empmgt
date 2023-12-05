package com.siriam.empmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siriam.empmgt.model.Employee;
import com.siriam.empmgt.services.EmployeeService;

@RestController
@RequestMapping("/api/v01/Employees")
public class EmployeeRestController {
	
	@Autowired
	EmployeeService empSrv;
	
	@GetMapping("/{id}")
	public Employee getEmp(@PathVariable (value="id")long id) {
		
		return empSrv.getEmployeeById(id);
	}
	@GetMapping
	public List<Employee> getAllEmp(){
		
		return empSrv.getAllEmployees();
		
	}
	
	@PostMapping
	public Employee addEmp(@RequestBody Employee emp ) {
		empSrv.saveEmployee(emp);
		return emp;
	}
	
	@PutMapping
	public Employee updateEmp(@RequestBody Employee emp) {
		
		empSrv.saveEmployee(emp);
		return emp;
		
	}
	
	@DeleteMapping("/{id}")
	public Employee deleteEmp(@PathVariable (value="id")long id) {
		Employee emp= empSrv.getEmployeeById(id);
		empSrv.deleteEmployeeById(id);
		return emp;
	}

}
