package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Employee;

public class EmployeeDaoTests {

    private static GenericRepo<Employee> empDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        empDao = new GenericRepoImpl<>(Employee.class);
    }

//    @Test
//    public void addSuccess() {
//        Employee employee = new Employee("Super", "Saiyan",
//                "kamehameha@gohan.com", "3j1o5ino1!", null, null, null, null);
//        int id = empDao.add(employee);
//        assertNotEquals(0, id);
//        assertNotEquals(-1, id);
//    }

    @Test
    public void getAllSuccess() {
        List<Employee> employees = empDao.getAll();
        assertTrue(27 < employees.size());
    }

    @Test
    public void getByIdSuccess() {
        Employee employee = empDao.getById(2);
        String expected = "Employee [id=2, firstName=Pippa, lastName=Tasseler, "
                + "email=ptasseler1@technorati.com, password=nIPt3pNB, supervis"
                + "orEmpId=1, depHeadEmpId=1, benCoEmpId=1, terminationTime=null]";
        assertEquals(expected, employee.toString());
    }

    @Test
    public void updateSuccess() {
        Employee employeeToUpdate = empDao.getById(23);
        employeeToUpdate.setTerminationTime(1627686024000L);
        Employee updatedEmployee = empDao.update(employeeToUpdate);
        assertEquals(employeeToUpdate.toString(), updatedEmployee.toString());
    }

//    @Test
//    public void deleteSuccess() {
//        Employee empToDelete = empDao.getById(1);
//        Employee nulledEmployee = new Employee(1, empToDelete.getFirstName(),
//                empToDelete.getLastName(), empToDelete.getEmail(),
//                empToDelete.getPassword(), null, null, null, null);
//        Employee updatedEmployee = empDao.update(nulledEmployee);
//        Employee deletedEmployee = empDao.delete(empToDelete);
//        assertEquals(empToDelete.toString(), deletedEmployee.toString());
//    }

}
