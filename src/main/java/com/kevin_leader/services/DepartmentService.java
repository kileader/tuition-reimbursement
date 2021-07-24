package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Department;
import com.kevin_leader.models.Employee;

public interface DepartmentService {
	
	public int add(String name, String description, Employee departmentHead);

	public List<Department> getAll();
	
	public Department getById(int id);
	
	public Department update(int id, String name, String description,
			Employee departmentHead);
	
	public Department deleteById(int id);
	
}
