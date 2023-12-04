package com.siriam.empmgt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siriam.empmgt.model.Employee;
import com.siriam.empmgt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return empRepo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		
		empRepo.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional =empRepo.findById(id);
		Employee emp=null;
		if(optional.isPresent()) {
			emp=optional.get();
		}else {
			throw new RuntimeException ("Employee not found with id : " + id);
		}
		return emp;
	}

	@Override
	public void deleteEmployeeById(long id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);
		
	}

}
