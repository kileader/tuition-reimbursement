package com.kevin_leader.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Employee;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class LogInServiceImplTests {
    
    private static GenericRepo<Employee> empDao;
    private static LogInService liServ;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        empDao = new GenericRepoImpl<>(Employee.class);
        liServ = new LogInServiceImpl(empDao);
    }
    
    @Test
    public void getAllEmployeesPass() {
        List<Employee> allEmployees = liServ.getAllEmployees();
        assertTrue(allEmployees.size() > 28);
    }
    
    @Test
    public void checkForEmployeePass() {
        Employee logInAttempt = new Employee("bbarnewell5@yandex.ru", "aA8vR7c");
        Employee expected = empDao.getById(6);
        Employee actual = liServ.checkForEmployee(logInAttempt);
        assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void checkForEmployeeFail() {
        Employee logInAttempt = new Employee("divinereflection@adam.va", "eveDaBest");
        Employee employee = liServ.checkForEmployee(logInAttempt);
        assertNull(employee);
    }
    
}
