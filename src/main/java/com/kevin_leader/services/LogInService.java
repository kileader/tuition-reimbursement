package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Employee;

public interface LogInService {
    
    public List<Employee> getAllEmployees();
    
    public Employee checkForEmployee(Employee employee);
    
}
