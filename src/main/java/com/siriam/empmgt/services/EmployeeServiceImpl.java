package com.siriam.empmgt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.siriam.empmgt.exceptions.EmployeeAlreadyExistsException;
import com.siriam.empmgt.exceptions.EmployeeNotFoundException;
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
		
		if(empRepo.existsById(employee.getId())) {
			throw new EmployeeAlreadyExistsException();
		}
		
		empRepo.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional =empRepo.findById(id);
		Employee emp=null;
		if(optional.isPresent()) {
			emp=optional.get();
		}else {
			throw new EmployeeNotFoundException ("Employee not found with id : " + id);
		}
		return emp;
	}

	@Override
	public void deleteEmployeeById(long id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);
		
	}

	@Override
	public Page<Employee> findPagiated(int pageNo, int pageSize, String sortField, String sortDirection) {
		
		//Sort sort= sortDirection.equalsIgnoreCase(Sort.direaction.ASC.name());
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		Pageable pageable=PageRequest.of(pageNo-1,pageSize,sort);
		
		return empRepo.findAll(pageable);
	}

	// without sorting
	/*@Override
	public Page<Employee> findPagiated(int pageNo, int pageSize) {

		Pageable pageable=PageRequest.of(pageNo-1,pageSize);
		
		return empRepo.findAll(pageable);
	}*/
	

}
