package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Department;
import com.kevin_leader.models.Employee;

public interface EmployeeService {
	
	public int add(String firstName, String lastName, String email,
			String password, Employee supervisor, Department department,
			Employee benefitsCoordinator, Long terminationTime);

	public List<Employee> getAll();
	
	public Employee getById(int id);
	
	public Employee update(int id, String firstName, String lastName,
			String email, String password, Employee supervisor,
			Department department, Employee benefitsCoordinator,
			Long terminationTime);
	
	public Employee deleteById(int id);
	
}
