package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Employee;
import com.kevin_leader.repositories.GenericRepo;

public class LogInServiceImpl implements LogInService {

    private static final Logger log = Logger.getLogger(LogInServiceImpl.class);
    private GenericRepo<Employee> empDao;
    private static List<Employee> allEmployees;

    public LogInServiceImpl(GenericRepo<Employee> empDao) {
        log.info("Instantiate LogInServiceImpl");
        this.empDao = empDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        if (!allEmployees.isEmpty()) {
            allEmployees = empDao.getAll();
        }
        return allEmployees;
    }

    @Override
    public Employee checkForEmployee(Employee logInAttempt) {
        log.info("Run checkForEmployee");
        
        if (allEmployees == null) {
            allEmployees = empDao.getAll();
        }
        
        for (Employee employee : allEmployees) {
            
            try {
                if (employee.getEmail().equals(logInAttempt.getEmail()) && 
                        employee.getPassword().equals(logInAttempt.getPassword())) {
                    log.info("found one");
                    
                    employee.setPassword(null);
                    return employee;
                }
            } catch (Exception e) {
                log.warn(e);
            }
            
        }
        return null;
    }

}
