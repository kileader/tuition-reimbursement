package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Department;
import com.kevin_leader.models.Employee;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log =
			Logger.getLogger(EmployeeServiceImpl.class);
	private GenericRepo<Employee> empDao;
	
	public EmployeeServiceImpl() {
		log.info("Instantiate EmployeeServiceImpl");
		empDao = new GenericRepoImpl<>(Employee.class);
	}

	@Override
	public int add(String firstName, String lastName, String email, String password, Employee supervisor,
			Department department, Employee benefitsCoordinator, Long terminationTime) {
		Employee employee = new Employee(firstName, lastName, email, password,
				supervisor, department, benefitsCoordinator, terminationTime);
		return empDao.add(employee);
	}

	@Override
	public List<Employee> getAll() {
		return empDao.getAll();
	}

	@Override
	public Employee getById(int id) {
		return empDao.getById(id);
	}

	@Override
	public Employee update(int id, String firstName, String lastName, String email, String password,
			Employee supervisor, Department department, Employee benefitsCoordinator, Long terminationTime) {
		Employee employee = new Employee(id, firstName, lastName, email, password,
			supervisor, department, benefitsCoordinator, terminationTime);
		return empDao.update(employee);
	}

	@Override
	public Employee deleteById(int id) {
		Employee employee = empDao.getById(id);
		return empDao.delete(employee);
	}

}
