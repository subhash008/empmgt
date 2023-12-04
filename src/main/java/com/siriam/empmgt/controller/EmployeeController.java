package com.siriam.empmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.siriam.empmgt.model.Employee;
import com.siriam.empmgt.services.EmployeeService;

@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService empServ;
	
	@GetMapping("emplist")
	public String viewEmployees(Model model) {
		model.addAttribute("listEmp",empServ.getAllEmployees());
		return "emplist";
		
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
}
