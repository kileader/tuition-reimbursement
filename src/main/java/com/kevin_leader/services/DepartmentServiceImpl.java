package com.kevin_leader.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Department;
import com.kevin_leader.models.Employee;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class DepartmentServiceImpl implements DepartmentService {
	
	private static final Logger log =
			Logger.getLogger(DepartmentServiceImpl.class);
	private GenericRepo<Department> dDao;
	
	public DepartmentServiceImpl() {
		log.info("Instantiate DepartmentSericeImpl");
		dDao = new GenericRepoImpl<>(Department.class);
	}
	
	@Override
	public int add(String name, String description, Employee departmentHead) {
		Department department = new Department(name, description,
				departmentHead);
		return dDao.add(department);
	}

	@Override
	public List<Department> getAll() {
		return dDao.getAll();
	}

	@Override
	public Department getById(int id) {
		return dDao.getById(id);
	}

	@Override
	public Department update(int id, String name, String description,
			Employee departmentHead) {
		Department department = new Department(id, name, description,
				departmentHead);
		return dDao.update(department);
	}

	@Override
	public Department deleteById(int id) {
		Department department = dDao.getById(id);
		return dDao.delete(department);
	}

}
