package com.siriam.empmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siriam.empmgt.model.Employee;
import com.siriam.empmgt.services.EmployeeService;

@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService empServ;
	
	@GetMapping
	public String getHome() {
		return "index";
	}
	
	//without pagination
	/*@GetMapping("emplist")
	public String viewEmployees(Model model) {
		model.addAttribute("listEmp",empServ.getAllEmployees());
		return "emplist";
		
	}
	*/
	//with pagination without sorting
	/*@GetMapping("emplist")
	public String viewEmployees(Model model) {
		return findPaginated(1,model);
		
	}
	*/
	
	//with sorting
	@GetMapping("emplist")
	public String viewEmployees(Model model) {
		return findPaginated(1,"firstName","asc",model);
		
	}
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		
		Employee emp=new Employee();
		model.addAttribute("employee",emp);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		empServ.saveEmployee(employee);
		return "redirect:/emplist";
		
	}
	
	@GetMapping("showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value="id")long id, Model model) {
		
		Employee employee= empServ.getEmployeeById(id);
		
		model.addAttribute("employee",employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value="id")long id, Model model){
		
		empServ.deleteEmployeeById(id);
		return "redirect:/emplist";
	}
	
	//page/1?sortField=name & sortDir=ASC // with sorting
	@GetMapping("/page/{pageNo}")
	public String findPaginated( @PathVariable (value="pageNo") int pageNo, 
			@RequestParam ("sortField") String sortField,
			@RequestParam ("sortDir") String sortDir,
			Model model) {
		int paageSize=5;
		Page<Employee> page = empServ.findPagiated(pageNo, paageSize,sortField,sortDir);
		List<Employee> listEmp=page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
		
		return "emplist";
	}
	
	/*
	@GetMapping("/page/{pageNo}")
	public String findPaginated( @PathVariable (value="pageNo") int pageNo, Model model) {
		int paageSize=5;
		Page<Employee> page = empServ.findPagiated(pageNo, paageSize);
		List<Employee> listEmp=page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmp", listEmp);
		return "emplist";
	}
	*/
}
