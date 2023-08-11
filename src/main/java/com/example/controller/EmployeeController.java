package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok(empService.createEmployee(employee));
		
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = empService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee, String firstName, String lastName, String email) {
        Employee updatedEmployee = empService.updateEmployee(id, employee, firstName, lastName, email);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        empService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
	
	
	
}
