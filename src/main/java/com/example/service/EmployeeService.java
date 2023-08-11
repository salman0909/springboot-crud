package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	public List<Employee> getAllEmployees(){
		return repo.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Long id){
		return repo.findById(id);
	}
	
	public Employee createEmployee(Employee employee) {
		return repo.save(employee);
	}
	
	public Employee updateEmployee(Long id, Employee employee,String firstName, String lastName, String email) {
		if(repo.existsById(id)) {
			employee.setId(id);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
			return repo.save(employee);
		}
		return null;
		
	}
	public void deleteEmployee(Long id) {
		repo.deleteById(id);
	}
	
	
}
