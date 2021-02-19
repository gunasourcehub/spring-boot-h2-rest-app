/**
 * 
 */
package com.example.databaseh2memoryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.example.databaseh2memoryapp.exception.ResourceNotFoundException;
import com.example.databaseh2memoryapp.model.Employee;
import com.example.databaseh2memoryapp.model.EmployeeMongo;
import com.example.databaseh2memoryapp.repository.EmployeeMongoRepository;
import com.example.databaseh2memoryapp.repository.EmployeeRepository;

/**
 * @author Guna Palani
 *
 */
@RestController
@RequestMapping("/api/services")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMongoRepository employeeMongoRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/mongoemployees")
	public List<EmployeeMongo> getAllEmployeesFromMongoDB(){
		return employeeMongoRepo.findAll();
	}
	@GetMapping("/mergeMongo2H2")
	public void mergeMongoToH2(){
		List<EmployeeMongo> lstEmployeeMongo =  employeeMongoRepo.findAll();
		Employee employee = null;
		for(EmployeeMongo employeeMongo : lstEmployeeMongo){
			employee = new Employee();
			employee.setId(employeeMongo.getId());
			employee.setFirstName(employeeMongo.getFirst_name());
			employee.setLastName(employeeMongo.getLast_name());
			employee.setEmailId(employeeMongo.getEmail_id());
			employeeRepository.save(employee);
		}
	}
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: "+employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: "+employeeId));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		final Employee updatedEmployee = employeeRepository.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: "+employeeId));
				
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}

}
