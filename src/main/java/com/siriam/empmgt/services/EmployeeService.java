package com.siriam.empmgt.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.siriam.empmgt.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	//Page<Employee> findPagiated(int pageNo, int pageSize);// without sorting
	Page<Employee> findPagiated(int pageNo, int pageSize, String sortField,String sortDirection);
}
